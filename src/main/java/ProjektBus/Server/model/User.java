package ProjektBus.Server.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
public class User {

    @Id
    public String id;
    private String login;
    private String email;
    private String password;

    public User(String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.password = password;
    }

    //TODO remove, only for test
    @Override
    public String toString() {
        return String.format(
                "User[id=%s, login='%s', email='%s', password='%s']",
                id, login, email, password);
    }

}