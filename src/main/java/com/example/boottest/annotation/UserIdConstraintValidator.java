package com.example.boottest.annotation;

import com.example.boottest.component.RankListComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserIdConstraintValidator implements ConstraintValidator<UserIdValidation, String> {
    private static final Logger logger = LoggerFactory.getLogger(UserIdConstraintValidator.class);
    @Resource
    private RankListComponent rankListComponent;

    @Override
    public void initialize(UserIdValidation userIdValidation) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isEmpty(s)) {
            return false;
        }
        Long userId = Long.parseLong(s);
        logger.info("userId:{}", userId);
        return rankListComponent.exist(userId);
    }
}
