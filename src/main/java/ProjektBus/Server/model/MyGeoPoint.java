package ProjektBus.Server.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MyGeoPoint {

    @NotNull(message = "Latitude {coordinates.notnull}")
    @Min(value = -90, message = "{latitude.value}")
    @Max(value = 90, message = "{latitude.value}")
    private Double latitude;

    @NotNull(message = "Longitude {coordinates.notnull}")
    @Min(value = -180, message = "{longitude.value}")
    @Max(value = 180, message = "{longitude.value}")
    private Double longitude;

}
