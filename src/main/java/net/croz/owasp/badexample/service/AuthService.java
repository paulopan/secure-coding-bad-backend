package net.croz.owasp.badexample.service;

import net.croz.owasp.badexample.entity.AuthUser;
import net.croz.owasp.badexample.entity.Session;
import net.croz.owasp.badexample.exception.AuthInvalidCredentialException;
import net.croz.owasp.badexample.service.command.LoginUserCommand;
import net.croz.owasp.badexample.service.command.ResetPasswordCommand;

public interface AuthService {

    Session login(LoginUserCommand loginUserCommand) throws AuthInvalidCredentialException;

    void resetPassword(ResetPasswordCommand resetPasswordCommand) throws AuthInvalidCredentialException;

    AuthUser getUserByType(AuthUser authUser);

}
