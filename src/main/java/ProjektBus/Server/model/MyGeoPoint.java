package ProjektBus.Server.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@Getter
@Setter
public class MyGeoPoint {

    @Min(value = -90, message = "Wrong latitude value")
    @Max(value = 90, message = "Wrong latitude value")
    private double latitude;

    @Min(value = -180, message = "Wrong longitude value")
    @Max(value = 180, message = "Wrong longitude value")
    private double longitude;

    public MyGeoPoint(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
