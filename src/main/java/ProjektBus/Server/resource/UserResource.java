package ProjektBus.Server.resource;

import ProjektBus.Server.model.User;
import ProjektBus.Server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class UserResource {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity saveUser(@RequestBody User user) throws URISyntaxException {
        //Żeby tu się dostać to musisz trzeba wysałć zapytanie na adres http://localhost:8110/user POST a w Body zmienić typ na raw -> JSON
        //i wysłać np.
        //{
        //    "login": "b",
         //           "email": "c",
        //            "password": "d"
        //}

        //Narazie nie mamy walidacji, więc zapisujemy wszystkich uzytkowników, nawet z tym samym loginem/mailem i nie szyfrujemy hasła
        //ten retrun ma zwrócić Response Code 201 (created) i URI, które jest adres, który poźniej będziemy mogli użyć do zwrócenia użytkowniaka
        //czyli to ma być: http://localhost:8110/user/"Tutaj ID Użytkownika", id użytkownika powinno się utworzyć automatycznie po
        // zapisaniu go do bazy
        userService.registerUser(user);
        return ResponseEntity.created(new URI("http://localhost:8110/user/" + user.getId())).build();
        //ten URI w Postman-ie możesz zobaczyć w zakładce Headers (na dole)
    }
}
