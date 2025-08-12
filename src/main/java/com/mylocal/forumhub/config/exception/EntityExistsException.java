package com.mylocal.forumhub.config.exception;


public class EntityExistsException extends RuntimeException {
    public EntityExistsException(String message) {
        super(message);
    }
}
