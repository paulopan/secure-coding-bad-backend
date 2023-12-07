package net.croz.owasp.badexample.configuration;

import net.croz.owasp.badexample.filter.AuthenticationFilter;
import net.croz.owasp.badexample.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// TODO: makni produkt iz url patterns - bbes
@Configuration
public class AuthenticationConfiguration {

    private final SessionService sessionService;

    @Autowired
    public AuthenticationConfiguration(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Bean
    public FilterRegistrationBean<AuthenticationFilter> authFilter(){
        FilterRegistrationBean<AuthenticationFilter> registrationBean
            = new FilterRegistrationBean<>();

        registrationBean.setFilter(new AuthenticationFilter(sessionService));
        registrationBean.addUrlPatterns("/testAuth/*", "/product/*", "/auth/current-user", "/bad-example-stomp");
        registrationBean.setOrder(1);

        return registrationBean;
    }

}
