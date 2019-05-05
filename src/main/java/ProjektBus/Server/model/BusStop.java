package ProjektBus.Server.model;

import ProjektBus.Server.validation.validationInterfaces.UniqueBusStopName;
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

    @NotNull(message = "{field.required}")
    @Size(min = 5, max = 32, message = "{name.size}")
    @UniqueBusStopName(message = "{busStop.name.unique}")
    @Pattern(regexp = "[A-Za-z]+[a-zA-Z0-9]*", message = "{busStop.name.pattern}")
    private String name;

    @NotNull(message = "{field.required}")
    @Valid
    private MyGeoPoint myGeoPoint;

}
