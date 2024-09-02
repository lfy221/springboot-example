package com.neu.java.spring.springboot.jwt.common.intercept;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Auth0Interceptor implements HandlerInterceptor {

    @Value("${jwt.key}")
    private String jwtKey;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // OPTIONS请求不做拦截操作:进行跨域请求的时候，并且请求头中有额外参数，比如token，客户端会先发送一个OPTIONS请求
        if(RequestMethod.OPTIONS.name().equals(request.getMethod())) {
            return true;
        }

        //Authorization为与前端程序商定的字段
        String token = request.getHeader("Authorization");
        response.setStatus(401);

        if (StringUtils.isBlank(jwtKey)) {
            responseMessage(response, "token值不能为空");
            return false;
        }

        String msg = "";
//        boolean verify = JwtA

        return false;
    }

    /**
     * 向前端输出消息方法
     *
     * @param response
     * @param message
     * @throws IOException
     */
    private void responseMessage(HttpServletResponse response, String message) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(message);
    }
}
