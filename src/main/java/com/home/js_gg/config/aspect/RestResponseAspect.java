package com.home.js_gg.config.aspect;

import com.home.js_gg.config.response.RestResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * controller aop 사용을 위한 컨포넌트
 */
@Component
@Aspect
public class RestResponseAspect {

    /**
     * Controller 클래스 프록시
     *
     * @param proceedingJoinPoint the proceeding join point
     * @return the rest response
     * @throws Throwable the throwable
     */
    @Around("execution(* com.home.js_gg.api.*.*(..))")
    public RestResponse<Object> restResponseHandler(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return new RestResponse<>(HttpStatus.OK.value(), "성공하였습니다.", proceedingJoinPoint.proceed());
    }
}
