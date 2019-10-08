package com.example.boottest.controller;

import com.example.boottest.component.RankListComponent;
import com.example.boottest.entity.ModifyVO;
import com.example.boottest.entity.RankDO;
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

@RestController
public class RankController {
    private static final Logger logger = LoggerFactory.getLogger(RankController.class);
    @Autowired
    private RankListComponent rankListComponent;
    @Value("${commandLine.testEnv}")
    private String testEnv;

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
}
