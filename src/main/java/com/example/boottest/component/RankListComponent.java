package com.example.boottest.component;

import com.example.boottest.entity.RankDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Component
public class RankListComponent {
    @Autowired
    private RedisComponent redisComponent;
    private static final String RANK_PREFIX = "global_rank";

    public RankDO updateRank(Long userId, Float score) {
        redisComponent.add(RANK_PREFIX, String.valueOf(userId), -score);
        Long rank = redisComponent.rank(RANK_PREFIX, String.valueOf(userId));
        return new RankDO(rank + 1, score, userId);
    }

    public RankDO getRank(Long userId) {
        Long rank = redisComponent.rank(RANK_PREFIX, String.valueOf(userId));
        if (rank == null) {
            return new RankDO(-1L, 0F, userId);
        }
        Double score = redisComponent.score(RANK_PREFIX, String.valueOf(userId));
        return new RankDO(rank + 1, Math.abs(score.floatValue()), userId);
    }

    /**
     * 用户是否存在
     */
    public boolean exist(Long userId) {
        return redisComponent.rank(RANK_PREFIX, String.valueOf(userId)) != null;
    }

    public List<RankDO> getRankAroundUser(Long userId, int n) {
        RankDO rank = getRank(userId);
        if (rank.getRank() <= 0) {
            return Collections.emptyList();
        }
        Set<ZSetOperations.TypedTuple<String>> result = redisComponent.rangeWithScore(RANK_PREFIX,
                Math.max(0, rank.getRank() - n - 1), rank.getRank() + n - 1);
        return buildRedisRankToBizDO(result, rank.getRank() - n);
    }

    private List<RankDO> buildRedisRankToBizDO(Set<ZSetOperations.TypedTuple<String>> result, long offset) {
        List<RankDO> rankList = new ArrayList<>();
        long rank = offset;
        for (ZSetOperations.TypedTuple<String> sub : result) {
            rankList.add(new RankDO(rank++, Math.abs(sub.getScore().floatValue()), Long.parseLong(sub.getValue())));
        }
        return rankList;
    }

    public List<RankDO> getTopNRanks(int n) {
        Set<ZSetOperations.TypedTuple<String>> result = redisComponent.rangeWithScore(RANK_PREFIX, 0, n - 1);
        return buildRedisRankToBizDO(result, 1);
    }
}





