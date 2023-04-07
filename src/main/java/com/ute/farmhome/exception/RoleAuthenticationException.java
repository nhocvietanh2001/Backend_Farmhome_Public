package com.ute.farmhome.exception;

import org.springframework.security.core.AuthenticationException;

public class RoleAuthenticationException extends AuthenticationException {
    public RoleAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public RoleAuthenticationException(String msg) {
        super(msg);
    }
}
