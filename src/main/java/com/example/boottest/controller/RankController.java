package com.example.boottest.controller;

import com.example.boottest.annotation.UserIdValidation;
import com.example.boottest.component.RankListComponent;
import com.example.boottest.entity.RankDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RankController {
    private static final Logger logger = LoggerFactory.getLogger(RankController.class);
    @Autowired
    private RankListComponent rankListComponent;

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

    @GetMapping(value = "/modify")
    public Object modify(@RequestParam("userId") @UserIdValidation(message = "userId不存在") String userId) {
        return "success";
    }
}
