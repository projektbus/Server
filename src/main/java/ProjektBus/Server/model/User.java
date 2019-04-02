package ProjektBus.Server.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
public class User {

    @Id
    private String id;
    private String login;
    private String email;
    private String password;

    public User(String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.password = password;
    }

}
