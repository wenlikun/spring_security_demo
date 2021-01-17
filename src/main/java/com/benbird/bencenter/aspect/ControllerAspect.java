package com.benbird.bencenter.aspect;

import com.benbird.bencenter.common.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * 项目名: bencenter
 * 创建者: Admin
 * 创建时间: 2021/1/14
 * 描述: 控制层的切面  进行权限控制和权限是否可用的校验
 * @author Admin
 */
@Aspect
@Component
public class ControllerAspect {

    /**
     * 切入点： 控制层的所有类和方法
     */
    @Pointcut("execution(* com.benbird.bencenter.controller.*.*(..))")
    public void controllerAspect() { }

    /**
     * 环绕增强方法
     * @param proceedingJoinPoint 连接点
     * @return                    连接方法返回值
     * @throws Throwable          Throwable
     */
    @Around("controllerAspect()")
    public Object beforeAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // 请求的URI
        String requestURI = attributes.getRequest().getRequestURI();
        // 白名单权限
        if("/login".equals(requestURI)){
            return proceedingJoinPoint.proceed();
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 当前登录的用户名
        String userName = null;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            userName = authentication.getName();
        }
        if(StringUtils.isEmpty(userName)){
            System.out.println("用户未登录");
            return Result.unauthorized(null);
        }
        // 验证权限
        if("/dashboard".equals(requestURI)){
            System.out.println("权限不足");
            return Result.forbidden(null);
        }
        // 验证是否可用
        if("/dashboard/delete".equals(requestURI)){
            System.out.println("资源不可用");
            return Result.notAvailable(null);
        }
        // 全部验证通过之后开始执行方法
        return proceedingJoinPoint.proceed();
    }



}
