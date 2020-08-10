package com.home.js_gg.config.controlleradvice;

import com.home.js_gg.config.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 예외처리 클래스, @RestController 어노테이션이 붙은 클래스의 Exception울 Catch 하여 처리한다.
 * 현재는 RuntimeException -> Service에서 발생하는 에러들만 처리되어있다.
 * 추후 파라미터 매핑오류, 시큐리티 오류 등 여러가지 Exception을 추가해야함.
 */
@RestControllerAdvice
@Slf4j
public class RestControllerExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public ErrorResponse handlerRuntimeException(RuntimeException e, HttpServletRequest req){
        log.error("===================== Handler RuntimeException =====================");
        e.printStackTrace();
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    /**
     * 메소드 파라미터 valid 불통
     * @param e
     * @param req
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handlerMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest req){
        log.error("===================== Handler MethodArgumentNotValidException =====================");
        e.printStackTrace();
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    /**
     * Http 요청을 잘못하면 발생. GET인데 POST로 콜한다거나.
     * @param e
     * @param req
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ErrorResponse handlerHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletRequest req){
        log.error("===================== Handler HttpRequestMethodNotSupportedException =====================");
        e.printStackTrace();
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
}
