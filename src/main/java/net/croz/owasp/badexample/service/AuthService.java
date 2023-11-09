package net.croz.owasp.badexample.service;

import net.croz.owasp.badexample.entity.Session;
import net.croz.owasp.badexample.service.command.LoginUserCommand;
import net.croz.owasp.badexample.service.command.ResetPasswordCommand;

public interface AuthService {

    Session login(LoginUserCommand loginUserCommand);

    void resetPassword(ResetPasswordCommand resetPasswordCommand);

}
