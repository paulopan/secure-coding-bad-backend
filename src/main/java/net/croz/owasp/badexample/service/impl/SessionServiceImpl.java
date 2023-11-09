package net.croz.owasp.badexample.service.impl;

import net.croz.owasp.badexample.entity.AuthUser;
import net.croz.owasp.badexample.entity.Session;
import net.croz.owasp.badexample.exception.AuthUnAuthorizedException;
import net.croz.owasp.badexample.repository.SessionRepository;
import net.croz.owasp.badexample.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;

    @Autowired
    public SessionServiceImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public Session createSession(AuthUser authUser, LocalDateTime validUntil) {
        final Session session = new Session();
        session.setAuthUser(authUser);
        session.setValidUntil(validUntil);

        return sessionRepository.save(session);
    }

    @Override
    public AuthUser checkSessionId(Long id) {
        final Session session = sessionRepository.findValidById(id)
            .orElseThrow(AuthUnAuthorizedException::new);

        return session.getAuthUser();
    }

    @Override
    public void invalidateSessions() {
        sessionRepository.invalidateSessions();
    }

}
