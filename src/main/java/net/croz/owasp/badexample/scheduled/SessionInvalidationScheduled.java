package net.croz.owasp.badexample.scheduled;

import net.croz.owasp.badexample.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SessionInvalidationScheduled {

    private final static int ONE_MINUTE = 60 * 1000;
    private final static int TEN_HOURS = 60 * ONE_MINUTE;

    private final SessionService sessionService;

    @Autowired
    public SessionInvalidationScheduled(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Scheduled(fixedDelay = TEN_HOURS, initialDelay = ONE_MINUTE)
    public void scheduleFixedDelayTask() {
        sessionService.invalidateSessions();
    }

}
