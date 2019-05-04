package ProjektBus.Server.model;

import ProjektBus.Server.validation.UniqueBusStopName;
import lombok.*;
import org.springframework.data.annotation.Id;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BusStop {

    @Id
    private String id;

    @NotNull(message = "Field is required")
    @Size(min = 5, max = 32, message = "Size must be between 5 and 32 letters")
    @UniqueBusStopName
    @Pattern(regexp = "[A-Za-z]+[a-zA-Z0-9]*")
    private String name;

    @NotNull(message = "Field is required")
    @Valid
    private MyGeoPoint myGeoPoint;

}
