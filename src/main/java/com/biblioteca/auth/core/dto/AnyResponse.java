package com.biblioteca.auth.core.dto;

public record AnyResponse(String status, Integer code, AuthUserResponse authUser) {

}
