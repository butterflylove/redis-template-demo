package com.example.boottest.entity;

import com.example.boottest.annotation.UserIdValidation;

/**
 * Created by zhangtianlong on 19/9/14.
 */
public class ModifyVO {
    @UserIdValidation(message = "userId不存在")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
