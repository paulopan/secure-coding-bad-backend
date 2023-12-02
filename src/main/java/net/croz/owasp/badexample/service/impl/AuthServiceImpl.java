package net.croz.owasp.badexample.service.impl;

import net.croz.owasp.badexample.entity.AuthUser;
import net.croz.owasp.badexample.entity.Session;
import net.croz.owasp.badexample.entity.UserType;
import net.croz.owasp.badexample.exception.AuthInvalidCredentialException;
import net.croz.owasp.badexample.exception.AuthUnAuthorizedException;
import net.croz.owasp.badexample.repository.AuthUserRepository;
import net.croz.owasp.badexample.repository.UserBuyerRepository;
import net.croz.owasp.badexample.repository.UserSellerRepository;
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

    private final UserSellerRepository userSellerRepository;

    private final UserBuyerRepository userBuyerRepository;

    private final SessionService sessionService;

    @Autowired
    public AuthServiceImpl(AuthUserRepository authUserRepository, UserSellerRepository userSellerRepository,
        UserBuyerRepository userBuyerRepository, SessionService sessionService) {
        this.authUserRepository = authUserRepository;
        this.userSellerRepository = userSellerRepository;
        this.userBuyerRepository = userBuyerRepository;
        this.sessionService = sessionService;
    }

    @Override
    public Session login(LoginUserCommand loginUserCommand) throws AuthInvalidCredentialException {
        final AuthUser existingUser = authUserRepository.getAuthUserByUsername(loginUserCommand.getUsername())
            .orElseThrow(() -> new AuthInvalidCredentialException(loginUserCommand, "LoginUserCommand", "username",
                "Username does not exist."));

        final String md5Hex = DigestUtils.md5Hex(loginUserCommand.getPassword());
        if (!Objects.equals(existingUser.getPassword(), md5Hex)) {
            throw new AuthInvalidCredentialException(loginUserCommand, "LoginUserCommand", "password",
                "Password is not correct.");
        }

        final long validDays = loginUserCommand.getRememberMe() ? 30 : 10;
        final LocalDateTime validUntil = LocalDateTime.now().plusDays(validDays);

        return sessionService.createSession(existingUser, validUntil);
    }

    @Override
    public void resetPassword(ResetPasswordCommand resetPasswordCommand) throws AuthInvalidCredentialException {
        final AuthUser existingUser = authUserRepository.getAuthUserByUsername(resetPasswordCommand.getUsername())
            .orElseThrow(() -> new AuthInvalidCredentialException(resetPasswordCommand, "ResetPasswordCommand", "username",
                    "Username does not exist."));

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

    @Override
    public AuthUser getUserByType(AuthUser authUser) {
        if (authUser.getUserType() == UserType.BUYER) {
            return userBuyerRepository.findById(authUser.getId()).get();
        }

        return userSellerRepository.findById(authUser.getId()).get();
    }

}
