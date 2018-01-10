package com.thoughtworks.mstorderservice.service.impl;

import com.thoughtworks.mstorderservice.Repository.TokenAuthRepository;
import com.thoughtworks.mstorderservice.configuration.security.JWTUser;
import com.thoughtworks.mstorderservice.exception.InvalidCredentialException;
import com.thoughtworks.mstorderservice.service.AuthService;
import com.thoughtworks.mstorderservice.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@Profile("!test")
public class AuthServiceImpl implements AuthService {
    private static final String PREFIX_BLACK_LIST = "SSJ-BLACKLIST-";

    @Value("${security.jwt.token-prefix:Bearer}")
    private String tokenPrefix;

    @Value("${security.jwt.header:Authorization}")
    private String header;

    @Value("${security.jwt.expiration-in-seconds}")
    private long expirationInSeconds;

    @Autowired
    private TokenAuthRepository authRepository;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public JWTUser getAuthorizedJWTUser(HttpServletRequest request) {
        String payload = authRepository.extractAuthorizedPayload(extractToken(request));
        return StringUtils.readJsonStringAsObject(payload, JWTUser.class);
    }

    @Override
    public boolean hasJWTToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(header);
        return StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith(tokenPrefix);
    }

    @Override
    public boolean isTokenInBlackList(HttpServletRequest request) {
        String token = extractToken(request);
        return StringUtils.hasText(redisTemplate.opsForValue().get(PREFIX_BLACK_LIST + token));
    }

    private String extractToken(HttpServletRequest request) {
        if (!hasJWTToken(request)) {
            throw new InvalidCredentialException();
        }
        return request.getHeader(header).substring(tokenPrefix.length() + 1);
    }
}
