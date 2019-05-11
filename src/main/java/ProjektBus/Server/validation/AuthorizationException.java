package ProjektBus.Server.validation;

public class AuthorizationException extends Exception {

    public AuthorizationException(String message){
        super(message);
    }
}
