package net.croz.owasp.badexample.service;

import net.croz.owasp.badexample.entity.AuthUser;
import net.croz.owasp.badexample.entity.Session;

import java.time.LocalDateTime;

public interface SessionService {

    Session createSession(AuthUser authUser, LocalDateTime validUntil);

    AuthUser checkSessionId(Long id);

    void invalidateSessions();

}
