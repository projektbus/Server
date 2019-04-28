package ProjektBus.Server.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Getter
public class ApplicationError {

    private HttpStatus status;
    private List<String> errors;

    public ApplicationError(HttpStatus status, String error) {
        this.status = status;
        this.errors = Collections.singletonList(error);
    }
}
