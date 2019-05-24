package ProjektBus.Server.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCodes {
    /*
            USER
     */
    WRONG_LOGIN("Zły login"),
    WRONG_PASSWORD("Złe hasło"),
    ACCOUNT_ALREADY_EXIST("Konto już potwierdzone"),
    TOKEN_NOT_FOUND("Nie znaleziono tokenu"),
    MAIL_SEND("Mail został wysłany"),
    PASSWORD_CHANGE_SUCCESSFUL("Zmiana hasła zakończona pomyslnie"),
    PASSWORD_ALREADY_CHANGED("Hasło zostalo już zmienione"),
    PASSWORD_MUST_BE_DIFFERENT("Nowe hasło musi być różne od poprzedniego"),
    USER_NOT_FOUND("Nie znaleziono użytkownika"),
    /*
            BUS STOP
     */
    BUS_STOP_NOT_FOUND("Nie znalezniono przystanku"),
    BUS_STOP_DELETE_SUCCESSFUL("Przystanek został usunięty"),
    /*
            BUS LINE
     */
    BUS_LINE_NOT_FOUND("Nie znalezniono linii"),
    BUS_STOP_ALREADY_ADDED("Przystanek juz należy do tej linii"),
    BUS_STOP_ADDED_TO_BUS_LINE("Przystanek dodano do linii"),
    BUS_LINE_DELETE_SUCCESSFUL("Linia została usunięta"),
    BUS_STOP_DELETED_FROM_BUS_LINE("Przystanek poprawnie usunięty z linii"),
    BUS_STOP_NOT_ON_LIST("Linia nie zewiera tego przeystanku"),
    /*
            CARRIER
     */
    CARRIER_NOT_FOUND("Przewoźnik nie istnieje"),
    CARRIER_DELETE_SUCCESSFUL("Przewoźnik został usunięty");

    private String response;
}
