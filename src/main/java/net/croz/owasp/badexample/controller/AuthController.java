package net.croz.owasp.badexample.controller;

import net.croz.owasp.badexample.entity.Session;
import net.croz.owasp.badexample.exception.AuthInvalidCredentialException;
import net.croz.owasp.badexample.service.AuthService;
import net.croz.owasp.badexample.service.command.LoginUserCommand;
import net.croz.owasp.badexample.service.command.ResetPasswordCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/login")
    public ResponseEntity<Void> loginUser(@RequestBody LoginUserCommand loginUserCommand)
        throws AuthInvalidCredentialException {
        final Session session = authService.login(loginUserCommand);

        final String sessionCookie =
            String.format("sessionId=%d; Domain=owasp-guidelines-bad.com; Path=/;", session.getId());

        final HttpHeaders headers = new HttpHeaders();
        headers.add("Set-Cookie", sessionCookie);
        headers.add("Access-Control-Allow-Credentials", "true");

        return ResponseEntity.ok().headers(headers).build();
    }

    @PostMapping("/password-reset")
    public void resetPassword(@RequestBody ResetPasswordCommand resetPasswordCommand)
        throws AuthInvalidCredentialException {
        authService.resetPassword(resetPasswordCommand);
    }
}
