package com.biblioteca.auth.core.service;


import com.biblioteca.auth.core.dto.AuthUserResponse;
import com.biblioteca.auth.infra.exception.AuthenticationException;
import com.biblioteca.auth.infra.exception.ValidationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class JwtService {

    private static final Integer TOKEN_INDEX = 1;
    private static final String EMPTY = " ";

    // @Value("${app.token.secret-key}")
    private String secretKey = "Y3Vyc28tYXV0ZW50aWNhY2FvLXN0YXRlZnVsLXN0YXRlbGVzcy1taWNyb3NzZXJ2aWNvcw==";

    public AuthUserResponse getAuthenticatedUser(String token){
        var tokenClaims = getClaims(token);
        var userId = Integer.valueOf((String) tokenClaims.get("id"));
        return new AuthUserResponse(userId, (String) tokenClaims.get("username"));
    }

    public void validateAcessToken(String token){
        getClaims(token);
    }

    private Claims getClaims(String token) {
        String accessToken = extractToken(token);

        try {
            SecretKey key = generateSign();

            JwtParser parser = Jwts.parser()
                    .setSigningKey(key)
                    .build();

            return parser.parseClaimsJws(accessToken).getBody(); // Retorna os claims do token

        } catch (Exception ex) {
            throw new AuthenticationException("Invalid access token: " + ex.getMessage());
        }
    }

    private SecretKey generateSign(){
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    private String extractToken(String token){
        if(isEmpty(token)){
            throw new ValidationException("The acess token was not informed");
        }

        if(token.contains(EMPTY)){
            return token.split(EMPTY)[TOKEN_INDEX];
        }
        return token;
    }


}
