package com.benbird.bencenter.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.benbird.bencenter.exception.BenbirdErrorCode;
import com.benbird.bencenter.exception.BenbirdException;
import com.benbird.bencenter.models.BaseDO;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 项目名: bencenter
 * 创建者: Admin
 * 创建时间: 2021/1/17
 * 描述: 参数校验服务
 * @author Admin
 */
public class ParamValidate {

    private ParamValidate(){}

    /**
     * 校验分页结果
     * @param count 总数量
     */
    public static void validatePageCount(Integer count){
        if(0 == count){
            throw new BenbirdException(BenbirdErrorCode.QUERY_EMPTY);
        }
    }

    /**
     * 校验分页结果
     * @param page 结果信息
     */
    public static void validatePageCount(Page<?> page){
        if(0 == page.getTotal()){
            throw new BenbirdException(BenbirdErrorCode.QUERY_EMPTY);
        }
    }

    /**
     * 校验ID是否大于i0
     * @param id ID
     */
    public static void validateId(Integer id){
        if(null == id || id < 0){
            throw new BenbirdException(BenbirdErrorCode.PARAMETER_VALID_NOT_PASS);
        }
    }


    /**
     * 校验String是否为空
     * @param params 集合列表
     */
    public static void validateStringNull(String... params){
        for(String param:params){
            if(null == param){
                throw new BenbirdException(BenbirdErrorCode.PARAMETER_IS_NULL);
            }
        }
    }


    private static final ValidatorFactory FACTORY = Validation.buildDefaultValidatorFactory();

    /**
     * 请求参数基本校验
     *
     * @param validateModel 待校验的 model
     */
    public static <T> void validateParams(T validateModel) {

        Validator validator = FACTORY.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(validateModel);
        if (CollectionUtils.isEmpty(violations)) {
            return;
        }
        throw new BenbirdException(BenbirdErrorCode.PARAMETER_VALID_NOT_PASS, violations.iterator().next().getMessage());
    }

    /**
     * 验证数据库中的数据是否可用
     * @param baseDO    DO数据
     */
    public static void validateUsable(BaseDO baseDO){
        if(null == baseDO || BenBirdConstant.UN_USABLE.equals(baseDO.getUsableFlag())){
            throw new BenbirdException(BenbirdErrorCode.MENU_NOT_EXISTS);
        }
    }

    /**
     * 验证日期范围
     * @param startDate     开始日期
     * @param endDate       结束日期
     */
    public static void validateDate(Date startDate , Date endDate){
        if(startDate.getTime() > endDate.getTime()){
            throw new BenbirdException(BenbirdErrorCode.START_CANT_BEFORE_END_DATE);
        }
    }

}
