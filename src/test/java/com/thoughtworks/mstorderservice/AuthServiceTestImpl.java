package com.thoughtworks.mstorderservice;

import com.thoughtworks.mstorderservice.configuration.security.JWTUser;
import com.thoughtworks.mstorderservice.service.AuthService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Service
@Profile("test")
public class AuthServiceTestImpl implements AuthService {
    @Override
    public JWTUser getAuthorizedJWTUser(HttpServletRequest request) {
        return JWTUser.builder()
                      .username("Tom")
                      .password("tompass")
                      .privileges(Arrays.asList())
                      .build();
    }

    @Override
    public boolean hasJWTToken(HttpServletRequest request) {
        return true;
    }

    @Override
    public boolean isTokenInBlackList(HttpServletRequest request) {
        return false;
    }
}
