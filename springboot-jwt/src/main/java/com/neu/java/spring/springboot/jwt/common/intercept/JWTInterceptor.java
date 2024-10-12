package com.neu.java.spring.springboot.jwt.common.intercept;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neu.java.spring.springboot.jwt.common.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JWTInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //OPTIONS请求不做拦截操作：进行跨域请求的时候，并且请求头中有额外参数，比图token，客户端会先发送一个OPTIONS请求
        if(RequestMethod.OPTIONS.name().equals(request.getMethod())) {
            return true;
        }

        //Authorization为与前端程序商定的字段
        String token = request.getHeader("Authorization");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        if(StringUtils.isBlank(token)) {
            responseMessage(response, "token不能为空");
            return false;
        }

        String msg = "";
        try {
            boolean verify = JwtUtil.verify(token);
            if(verify) {
                response.setStatus(HttpServletResponse.SC_OK);
                Claims claims = JwtUtil.getClaim(token);
                String username = (String)claims.get("username");
                //TODO log.debug username
                return true;
            }
        } catch (SignatureException e) {
            msg = "无效签名";
        } catch (UnsupportedJwtException e) {
            msg = "不支持的签名";
        } catch (ExpiredJwtException e) {
            msg = "token过期";
        } catch (MalformedJwtException e) {
            msg = "不支持的签名格式";
        } catch (Exception e) {
            msg = "token无效";
        }

        Map<String, Object> map = new HashMap<>();
        map.put("msg", msg);
        map.put("status", false);
        String json = new ObjectMapper().writeValueAsString(map);
        responseMessage(response, json);

        return false;
    }

    /**
     * 返回消息给前端
     * @param response
     * @param message
     * @throws IOException
     */
    private void responseMessage(HttpServletResponse response, String message) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(message);
    }
}
