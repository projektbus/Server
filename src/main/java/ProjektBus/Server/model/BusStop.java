package ProjektBus.Server.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Getter
@Setter
public class BusStop {

    @Id
    private String id;

    @NotNull(message = "Field is required")
    @Size(min = 5, max = 32, message = "Size must be between 5 and 32 letters")
    private String name;

    @NotNull(message = "Field is required")
    @Valid
    private MyGeoPoint myGeoPoint;

    public BusStop(String name, MyGeoPoint myGeoPoint) {
        this.name = name;
        this.myGeoPoint = myGeoPoint;
    }
}
