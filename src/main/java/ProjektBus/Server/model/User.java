package ProjektBus.Server.model;

import ProjektBus.Server.repository.UserRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
public class User  {

    @Id
    private String id;
    private String login;
    private String email;
    private String password;

    @Autowired
    public User( String login, String email, String password) {

        this.login = login;
        this.email = email;
        this.password = password;
    }

}

