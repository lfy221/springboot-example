package com.neu.java.spring.springboot.jwt.modules.auth.service;

import java.util.Map;

public interface LoginService {
    Map<String, Object> login(String username, String password);
}
