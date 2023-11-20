package net.croz.owasp.badexample.exception;

import net.croz.owasp.badexample.service.command.LoginUserCommand;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class AuthInvalidCredentialException extends BindException {

    public AuthInvalidCredentialException(Object command, String commandName, String field, String message) {
        super(createBindingResult(command, commandName, field, message));
    }

    public AuthInvalidCredentialException(BindingResult bindingResult) {
        super(bindingResult);
    }

    static BindingResult createBindingResult(Object command, String commandName, String field, String message) {
        final FieldError fieldError = new FieldError(commandName, field, message);

        final BindingResult bindingResult = new BeanPropertyBindingResult(command, commandName);
        bindingResult.addError(fieldError);

        return bindingResult;
    }

}
