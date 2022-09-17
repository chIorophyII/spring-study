package com.example.jwtspringsecurity.login.security.provider;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.jwtspringsecurity.login.security.jwt.JwtTokenUtils;
import com.example.jwtspringsecurity.login.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;

import static com.example.jwtspringsecurity.exception.ExceptionMessage.ILLEGAL_INVALID_TOKEN;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final UserDetailsServiceImpl userDetailsService;
    private static final String TOKEN_PREFIX = "Bearer ";
    @Value("${jwt.secretkey}")
    String JWT_SECRET;

    // header에서 토큰 추출
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

    // 토큰으로 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(decodeUsername(token));
        return new UsernamePasswordAuthenticationToken(
                // The credentials that prove the principal is correct.
                userDetails, "", userDetails.getAuthorities());
    }

    // 토큰에서 회원 정보 추출
    public String decodeUsername(String token) {
        DecodedJWT decodedJWT = isValidToken(token)
                .orElseThrow(() -> new IllegalArgumentException(ILLEGAL_INVALID_TOKEN));

        Date now = new Date();
        if (decodedJWT.getExpiresAt().before(now)) {
            throw new IllegalArgumentException(ILLEGAL_INVALID_TOKEN);
        }

        String username = decodedJWT
                .getClaim(JwtTokenUtils.CLAIM_USER_NAME)
                .asString();

        return username;
    }

    public Optional<DecodedJWT> isValidToken(String token) {
        DecodedJWT jwt = null;

        try {
            JWTVerifier verifier = JWT
                    .require(generateAlgorithm(JWT_SECRET))
                    .build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return Optional.ofNullable(jwt);
    }

    private static Algorithm generateAlgorithm(String secretKey) {
        return Algorithm.HMAC256(secretKey.getBytes());
    }
}
