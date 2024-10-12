package com.neu.java.spring.springboot.jwt.common.config;

import com.neu.java.spring.springboot.jwt.common.intercept.Auth0Interceptor;
import com.neu.java.spring.springboot.jwt.common.intercept.JWTInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 跨域设置
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 拦截所有的请求
                .allowedOrigins("*") // 可跨域的域名，可以为 *
                .allowCredentials(true) //是否允许发送Cookie(存储)信息（为true就是允许跨域，为false就是不允许跨域）
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "TRACE", "OPTIONS", "PATCH") // 允许跨域的方法，可以单独配置
                .allowedHeaders("*") // 允许跨域的请求头，可以单独配置
                .maxAge(3600); //1小时内不需要再预检（发OPTIONS请求） 跨域允许时间
    }

    /**
     * 登录认证
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(jwtInterceptor())
        registry.addInterceptor(auth0Interceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login/*");
    }

    @Bean
    public Auth0Interceptor auth0Interceptor() {
        return new Auth0Interceptor();
    }

    @Bean
    public JWTInterceptor jwtInterceptor() {
        return new JWTInterceptor();
    }
}
