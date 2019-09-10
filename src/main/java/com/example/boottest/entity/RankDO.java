package com.example.boottest.entity;

public class RankDO {
    private Long rank;
    private Float score;
    private Long userId;

    public RankDO(Long rank, Float score, Long userId) {
        this.rank = rank;
        this.score = score;
        this.userId = userId;
    }

    public RankDO() {
    }

    public Long getRank() {
        return rank;
    }

    public RankDO setRank(Long rank) {
        this.rank = rank;
        return this;
    }

    public Float getScore() {
        return score;
    }

    public RankDO setScore(Float score) {
        this.score = score;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public RankDO setUserId(Long userId) {
        this.userId = userId;
        return this;
    }
}
