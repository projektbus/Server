package ProjektBus.Server.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MyGeoPoint {

    @NotNull
    @Min(value = -90, message = "Wrong latitude value")
    @Max(value = 90, message = "Wrong latitude value")
    private double latitude;

    @NotNull
    @Min(value = -180, message = "Wrong longitude value")
    @Max(value = 180, message = "Wrong longitude value")
    private double longitude;

}
