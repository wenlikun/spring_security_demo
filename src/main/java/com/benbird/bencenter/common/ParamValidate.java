package com.benbird.bencenter.common;

import com.benbird.bencenter.exception.BenbirdErrorCode;
import com.benbird.bencenter.exception.BenbirdException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * 项目名: bencenter
 * 创建者: Admin
 * 创建时间: 2021/1/17
 * 描述: 参数校验服务
 * @author Admin
 */
public class ParamValidate {

    private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    /**
     * 请求参数基本校验
     *
     * @param validateModel 待校验的 model
     */
    public static <T> void validateParams(T validateModel) {

        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(validateModel);
        if (violations.isEmpty()) {
            return;
        }
        throw new BenbirdException(BenbirdErrorCode.PARAMETER_VALID_NOT_PASS, violations.iterator().next().getMessage());
    }


}
