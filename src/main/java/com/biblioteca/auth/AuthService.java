package com.biblioteca.auth;

import com.biblioteca.auth.core.dto.AnyResponse;
import com.biblioteca.auth.core.dto.AuthUserResponse;
import com.biblioteca.auth.core.service.JwtService;
import com.biblioteca.auth.infra.exception.AuthenticationException;
import com.biblioteca.auth.infra.exception.ValidationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import static org.springframework.util.ObjectUtils.isEmpty;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@Service
public class AuthService {

    @Autowired
    JwtService jwtService;

    public AnyResponse getData(String acessToken) {

        

        jwtService.validateAcessToken(acessToken);
        var authUser = jwtService.getAuthenticatedUser(acessToken);
        var ok = HttpStatus.OK;
        return new AnyResponse(ok.name(), ok.value(), authUser);
    }

    

}

