package ProjektBus.Server.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    private String id;

    @NotNull(message = "Field is required")
    @Size(min = 5, max = 32, message = "Size must be between 5 and 32 letters")
    private String login;

    @NotNull(message = "Field is required")
    @Size(min = 5, max = 32, message = "Size must be between 5 and 32 letters")
    private String email;

    @NotNull(message = "Field is required")
    @Size(min = 8, max = 32, message = "Size must be between 5 and 32 letters")
    private String password;

    private boolean enabled;

    public User(String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.enabled = false;
    }
}

