package ProjektBus.Server.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApplicationResponse {

    private String response;

    public ApplicationResponse(ErrorCodes errorCodes){
        this.response = errorCodes.getResponse();
    }
}
