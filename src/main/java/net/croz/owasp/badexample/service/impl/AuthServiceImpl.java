package net.croz.owasp.badexample.service.impl;

import net.croz.owasp.badexample.entity.AuthUser;
import net.croz.owasp.badexample.entity.Session;
import net.croz.owasp.badexample.exception.AuthInvalidPasswordException;
import net.croz.owasp.badexample.exception.AuthInvalidUsernameException;
import net.croz.owasp.badexample.exception.AuthUnAuthorizedException;
import net.croz.owasp.badexample.repository.AuthUserRepository;
import net.croz.owasp.badexample.service.AuthService;
import net.croz.owasp.badexample.service.SessionService;
import net.croz.owasp.badexample.service.command.LoginUserCommand;
import net.croz.owasp.badexample.service.command.ResetPasswordCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthUserRepository authUserRepository;

    private final SessionService sessionService;

    @Autowired
    public AuthServiceImpl(AuthUserRepository authUserRepository, SessionService sessionService) {
        this.authUserRepository = authUserRepository;
        this.sessionService = sessionService;
    }

    @Override
    public Session login(LoginUserCommand loginUserCommand) {
        final AuthUser existingUser = authUserRepository.getAuthUserByUsername(loginUserCommand.getUsername())
            .orElseThrow(() -> new AuthInvalidUsernameException(loginUserCommand.getUsername()));

        final String md5Hex = DigestUtils.md5Hex(loginUserCommand.getPassword());
        if (!Objects.equals(existingUser.getPassword(), md5Hex)) {
            throw new AuthInvalidPasswordException(loginUserCommand.getPassword());
        }

        final long validDays = loginUserCommand.getRememberMe() ? 30 : 10;
        final LocalDateTime validUntil = LocalDateTime.now().plusDays(validDays);

        return sessionService.createSession(existingUser, validUntil);
    }

    @Override
    public void resetPassword(ResetPasswordCommand resetPasswordCommand) {
        final AuthUser existingUser = authUserRepository.getAuthUserByUsername(resetPasswordCommand.getUsername())
            .orElseThrow(() -> new AuthInvalidUsernameException(resetPasswordCommand.getUsername()));

        final boolean questionOne =
            Objects.equals(existingUser.getSecurityQuestionOne(), resetPasswordCommand.getQuestionOneAnswer());
        final boolean questionTwo =
            Objects.equals(existingUser.getSecurityQuestionTwo(), resetPasswordCommand.getQuestionTwoAnswer());
        final boolean questionThree =
            Objects.equals(existingUser.getSecurityQuestionThree(), resetPasswordCommand.getQuestionThreeAnswer());
        final boolean allQuestionsCorrect = questionOne && questionTwo && questionThree;

        if (!allQuestionsCorrect) {
            throw new AuthUnAuthorizedException();
        }

        final String md5Hex = DigestUtils.md5Hex(resetPasswordCommand.getPassword());
        existingUser.setPassword(md5Hex);
        authUserRepository.updateAuthPassword(existingUser);
    }

}
