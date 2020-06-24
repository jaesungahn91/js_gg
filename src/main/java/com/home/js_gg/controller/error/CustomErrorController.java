package com.home.js_gg.controller.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;


// TODO: controller 에서의 처리가아닌 컨포넌트로 핸들러를 만들어 사용하는 방법으로 전환
/**
 * 커스텀 에러 컨트롤러
 */
@Controller
public class CustomErrorController implements ErrorController {
    private final String PATH = "/error";
    private final String VIEW_PATH = "/error/";
    /**
     * 에러페이지 핸들러
     *
     * @param request the request
     * @return the string
     */
    @RequestMapping(value = PATH)
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if(status != null){
            String statusCode = String.valueOf(status);
            if(statusCode.equalsIgnoreCase(String.valueOf(HttpStatus.NOT_FOUND.value()))){
                return VIEW_PATH + "404";
            }
            if(statusCode.equalsIgnoreCase(String.valueOf(HttpStatus.FORBIDDEN.value()))){
                return VIEW_PATH + "500";
            }
        }
        return "error";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}