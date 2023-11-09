package net.croz.owasp.badexample.controller;

import net.croz.owasp.badexample.entity.AuthUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testAuth")
public class TestProtectedController {

    @GetMapping
    private AuthUser testAuth(@RequestAttribute("authUser") AuthUser authUser) {
        return authUser;
    }
}
