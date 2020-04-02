package com.example.boottest.controller;

import com.example.boottest.component.RankListComponent;
import com.example.boottest.component.RedisComponent;
import com.example.boottest.entity.ModifyVO;
import com.example.boottest.entity.RankDO;
import com.example.boottest.entity.TestTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
public class RankController {
    private static final Logger logger = LoggerFactory.getLogger(RankController.class);
    @Autowired
    private RankListComponent rankListComponent;
    @Autowired
    private RedisComponent redisComponent;
    @Value("${commandLine.testEnv}")
    private String testEnv;
    private ForkJoinPool forkJoinPool = new ForkJoinPool(4);
    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);

    @GetMapping(path = "/update")
    public RankDO updateScore(long userId, float score) {
        logger.info("======userId:{}, score:{}", userId, score);
        return rankListComponent.updateRank(userId, score);
    }

    @GetMapping(value = "/rank")
    public RankDO queryRank(long userId) {
        logger.info("======userId:{}", userId);
        return rankListComponent.getRank(userId);
    }

    @GetMapping(value = "/around")
    public List<RankDO> around(long userId, int n) {
        return rankListComponent.getRankAroundUser(userId, n);
    }

    @GetMapping(value = "/topn")
    public List<RankDO> showTopN(int n) {
        return rankListComponent.getTopNRanks(n);
    }

    @PostMapping(value = "/modify")
    public Object modify(@Valid @RequestBody ModifyVO modifyVO,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> error = new HashMap<>();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                error.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return error;
        }
        return "success";
    }

    @GetMapping(value = "/testEnv")
    public String testEnv() {
        return testEnv;
    }

    @GetMapping(value = "/testForkJoin")
    public String testForkJoinPool() {
        forkJoinPool.submit(new TestTask());
        forkJoinPool.submit(new TestTask());
        forkJoinPool.submit(new TestTask());
        forkJoinPool.submit(new TestTask());
        return "testForkJoin";
    }

    @GetMapping(value = "/testScheduled")
    public String testScheduled() {
        executor.scheduleWithFixedDelay(new TestTask(), 5, 5, TimeUnit.SECONDS);
        return "success";
    }

    @GetMapping(value = "/testHash")
    public String testHash() {
        redisComponent.hashPut("zhangsan", "name", "zhang3");
        redisComponent.hashPut("zhangsan", "age", "11");
        return "true";
    }

    @GetMapping(value = "/testHashPutAll")
    public String testPutAll() {
        Map<String, String> map = new HashMap<String, String>() {{
            put("name", "lisi");
            put("age", "33");
        }};
        redisComponent.hashPutAll("lisi", map);
        return "true";
    }

    @GetMapping(value = "/hget")
    public Map<Object, Object> hget(@RequestParam("key") String key) {
        return redisComponent.hget(key);
    }
}
