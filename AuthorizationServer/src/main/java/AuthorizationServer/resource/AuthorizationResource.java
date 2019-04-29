package AuthorizationServer.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class AuthorizationResource{

    @GetMapping("/user/me")
    public Principal user(Principal principal) {
        return principal;
    }
}
