package net.croz.owasp.badexample.exception;

public class AuthInvalidUsernameException extends RuntimeException {

    private static final String INVALID_USERNAME_MESSAGE = "Username %s not found";

    public AuthInvalidUsernameException(String username) {
        super(String.format(INVALID_USERNAME_MESSAGE, username));
    }

}
