package net.croz.owasp.badexample.controller;

import net.croz.owasp.badexample.entity.AuthUser;
import net.croz.owasp.badexample.entity.UserBuyer;
import net.croz.owasp.badexample.service.AuthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testAuth")
public class TestProtectedController {

    private final AuthService authService;

    public TestProtectedController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    private AuthUser testAuth(@RequestAttribute("authUser") AuthUser authUser) {
        UserBuyer userBuyer = (UserBuyer)authService.getUserByType(authUser);
        return userBuyer;
    }
}
