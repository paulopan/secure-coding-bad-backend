package net.croz.owasp.badexample.exception;

// TODO: Dodati exception handler
public class AuthInvalidPasswordException extends RuntimeException {

    private static final String INVALID_PASSWORD_MESSAGE = "Password %s is not correct";

    public AuthInvalidPasswordException(String password) {
        super(String.format(INVALID_PASSWORD_MESSAGE, password));
    }

}
