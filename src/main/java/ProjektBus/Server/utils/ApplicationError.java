package ProjektBus.Server.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Getter
public class ApplicationError {

    private List<String> errors;

    public ApplicationError( String error) {
        this.errors = Collections.singletonList(error);
    }

    public ApplicationError(ErrorCodes errorCodes) {
        this.errors = Collections.singletonList(errorCodes.getResponse());
    }
}
