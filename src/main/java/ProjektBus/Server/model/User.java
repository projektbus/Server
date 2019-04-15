package ProjektBus.Server.model;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class User {

    @Id
    private String id;
    private String login;
    private String email;
    private String password;
    @Valid
    public User( @Size(min=5,max=32) @NotNull String login,
                 @NotNull String email,
                 @Size(min=5,max=32) @NotNull String password) {

        this.login = login;
        this.email = email;
        this.password = password;
    }
    //Rzucalo wyjatkiem jak tylko probowalem cokolwiek dodac, nawet bez edycji kodu,
    // ten pusty konstruktor sprawil ze wszystko u mnie dziala
    public User() {

    }

    // SetGet? Dodalem gety gdyz dostalem error 404 w mozgu jak mam sie do tego dorwac poza userem

    // Jesli ma byc inaczej to mnie powiedzcie jak dostac sie do tych danych, albo czy tutaj validacje przeniesc cala

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public User toUser()
    {
        return new User(getLogin(),getEmail(),getPassword());
    }
}
