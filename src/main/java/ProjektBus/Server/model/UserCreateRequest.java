package ProjektBus.Server.model;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor

public class UserCreateRequest {

    @NotNull
    @Size(min=5, max=32)
    private String login;
    @NotNull
    @Size(min=5, max=32)
    private String email;
    @NotNull
    @Size(min=5, max=32)
    private String password;

    public UserCreateRequest( String login, String email, String password)
    {
        this.login=login;
        this.email=email;
        this.password=password;
    }

    public User toUser()
    {
        return new User(getLogin(),getEmail(),getPassword());
    }

}
