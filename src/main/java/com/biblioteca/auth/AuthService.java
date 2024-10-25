package com.biblioteca.auth;

import com.biblioteca.auth.core.dto.AnyResponse;
import com.biblioteca.auth.core.service.JwtService;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {

    private final JwtService jwtService;

    public AnyResponse getData(String acessToken) {

        jwtService.validateAcessToken(acessToken);
        var authUser = jwtService.getAuthenticatedUser(acessToken);
        var ok = HttpStatus.OK;
        return new AnyResponse(ok.name(), ok.value(), authUser);
    }
}