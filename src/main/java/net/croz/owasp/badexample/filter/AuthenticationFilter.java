package net.croz.owasp.badexample.filter;

import net.croz.owasp.badexample.entity.AuthUser;
import net.croz.owasp.badexample.service.SessionService;
import org.springframework.web.util.WebUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// TODO: Stavi Usera u context (app_user)
public class AuthenticationFilter implements Filter {

    private final static String SESSION_COOKIE = "sessionId";

    private final SessionService sessionService;

    public AuthenticationFilter(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
        throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse res = (HttpServletResponse) servletResponse;

        final Cookie sessionCookie = WebUtils.getCookie(req, SESSION_COOKIE);

        try {
            final AuthUser authUser = sessionService.checkSessionId(Long.parseLong(sessionCookie.getValue()));
            req.setAttribute("authUser", authUser);
            filterChain.doFilter(req, res);
        } catch (Exception e) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.setContentType("application/json");
        }
    }

}
