package ProjektBus.Server.service;

import ProjektBus.Server.model.User;
import ProjektBus.Server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;


public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        user = userRepository.save(user);
        return user;
    }
    @Override
    public Integer RemindPassword(String email){//metoda wysyła na maila hasło tymczasowe
        User user=userRepository.findByEmail(email);
        if(user!=null) {
            String pass = new Random().ints(10, 48, 122).collect(StringBuilder::new,
                    StringBuilder::appendCodePoint, StringBuilder::append).toString();//generator haseł
            user.setPassword(pass);//ustawienie hasła tymczasowego
            //wysyłanie maila
            final String username = "projektbus@gmail.com";
            final String password = "nieznane";

            Properties prop = new Properties();
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", "587");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.starttls.enable", "true");
            //miałem problem z dodaniem biblioteki javax.mail w IntelliJ, ale kod powinien działać po dodaniu
            Session session = Session.getInstance(prop,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });
            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("projektbus@gmail.com"));
                message.setRecipients(
                        Message.RecipientType.TO,
                        InternetAddress.parse(email)
                );
                message.setSubject("Przypomnienie hasła");
                message.setText("Oto hasło tymczasowe" +"\n\n"+pass+"\n\n Proszęę zmienić je po zalogowaniu");

                Transport.send(message);

                System.out.println("Done");

            } catch (MessagingException e) {
                e.printStackTrace();
            }
            return 0;
        }
        else{
            System.out.println("Brak użytkownika o podanym mailu");
            return 1;
        }
    }


}
