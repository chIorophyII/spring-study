package com.example.jwtspringsecurity.login.security.jwt;

import com.example.jwtspringsecurity.login.dto.UserDto;
import com.example.jwtspringsecurity.login.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtils {
    private static final int SEC = 1;
    private static final int MINUTE = 60 * SEC;
    private static final int HOUR = 60 * MINUTE;
    private static final int DAY = 24 * HOUR;

    // JWT 토큰의 유효기간: 3일 (단위: seconds)
    private static final int JWT_TOKEN_VALID_SEC = 3 * DAY;
    // JWT 토큰의 유효기간: 3일 (단위: milliseconds)
    private static final int JWT_TOKEN_VALID_MILLI_SEC = JWT_TOKEN_VALID_SEC * 1000;

    public static final String CLAIM_EXPIRED_DATE = "EXPIRED_DATE";
    public static final String CLAIM_USER_NAME = "USER_NAME";
    public static final String CLAIM_ROLE = "ROLE";
    public static final String ISSUER = "princess";
    @Value("${jwt.secretkey}")
    String JWT_SECRET;

    public String generateJwtToken(User user) {
        String token = null;
        token = Jwts.builder()
                .setHeader(createHeader())
                .setIssuer(ISSUER)
                .setClaims(createClaims(user))
                // 토큰 만료 일시 = 현재 시간 + 토큰 유효기간)
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALID_MILLI_SEC))
                .signWith(generateAlgorithm(), JWT_SECRET)
                .compact();

        return token;
    }

    private Map<String, Object> createHeader() {
        Map<String, Object> header = new HashMap<>();
        header.put("alg", "HS256");
        header.put("typ", "JWT");

        return header;
    }

    private Map<String, Object> createClaims(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_USER_NAME, user.getUsername());
        claims.put(CLAIM_ROLE, user.getUserRole());

        return claims;
    }

    private static SignatureAlgorithm generateAlgorithm() {
        return SignatureAlgorithm.HS256;
    }
}
