package ProjektBus.Server.service;

import ProjektBus.Server.model.User;
import ProjektBus.Server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.regex.Pattern;
import javax.validation.Valid;


public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        int err=userValidate(user) ;
        if (!userValidate(user).equals(0)) {
            System.out.println("Validation not passed");
            return null;
        }
        System.out.println("Validation passed");
        user = userRepository.save(user);
        return user;
    }



    @Override
    public Integer userValidate( User user) {

        if(user.getLogin().length()<5)
        {
            System.out.println(" user too short");
            return 1; //user too short
        }


        if(user.getLogin().length()>32)
        {
            System.out.println(" user too long");
            return 2; //user too long
        }


        if(userRepository.findByLogin(user.getLogin())!=null) {
            System.out.println(" user exists");
            return 3; //user exists
        }


        if(userRepository.findByEmail(user.getEmail())!=null) {
            System.out.println(" email exists");
            return 4; // email exists
        }


        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);

        if(user.getEmail().length()<5)
        {
            System.out.println(" email too short");
            return 5; //user too short
        }


        if(user.getEmail().length()>32)
        {
            System.out.println(" email too long");
            return 6; //user too long
        }

        if (user.getEmail()== null) {
            System.out.println(" wrong email");
            return 7; // wrong email
        }


        if(pat.matcher(user.getEmail()).matches()==false) {
            System.out.println(" wrong email");
            return 7; // wrong email
        }


        if(user.getPassword().length()<5)
        {
            System.out.println(" email too short");
            return 8; //user too short
        }


        if(user.getPassword().length()>32)
        {
            System.out.println(" email too long");
            return 9; //user too long
        }

        String passwordRegexLetters = ".*[a-zA-Z+].*";
        pat=Pattern.compile(passwordRegexLetters);
        if(pat.matcher(user.getPassword()).matches()==false) {
            System.out.println(user.getPassword());
            System.out.println("password no letters");
            return 10;// no letters
        }


        String passwordRegexNumbers =".*\\d+.*";
        pat=Pattern.compile(passwordRegexNumbers);
        if(pat.matcher(user.getPassword()).matches()==false) {
            System.out.println("password no numbers");
            return 11; // no numbers
        }


        String passwordRegexSpecial = ".*[^A-Za-z0-9].*";
        pat=Pattern.compile(passwordRegexSpecial);
        if(pat.matcher(user.getPassword()).matches()==false) {
            System.out.println("Password no special");
            return 12; // no special
        }
        return 0;
    }


}
