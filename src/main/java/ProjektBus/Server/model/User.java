package ProjektBus.Server.model;

import ProjektBus.Server.validation.validationInterfaces.NotExistEmail;
import ProjektBus.Server.validation.validationInterfaces.NotExistLogin;
import lombok.*;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    private String id;

    @NotNull(message = "{field.required}")
    @NotExistLogin(message = "{login.exist}")
    @Size(min = 5, max = 32, message = "{name.size}")
    private String login;

    @NotNull(message = "{field.required}")
    @NotExistEmail(message = "{email.exist}")
    @Size(min = 5, max = 32, message = "{name.size}")
    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\."+
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
            "A-Z]{2,7}$", message = "{email.regex}")
    private String email;

    @NotNull(message = "{field.required}")
    @Size(min = 5, max = 32, message = "{name.size}")
    @Pattern(regexp = ".*[a-zA-Z+].*", message = "{password.letter}")
    @Pattern(regexp = ".*\\d+.*", message = "{password.number}")
    @Pattern(regexp = ".*[^A-Za-z0-9].*", message = "{password.special}")
    private String password;

    private boolean enabled;

    public User(String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.password = password;
    }
}

