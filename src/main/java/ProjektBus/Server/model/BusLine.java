package ProjektBus.Server.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Getter
@Setter
public class BusLine {

    @NotEmpty
    @Size(min = 5, max = 32, message = "Size must be between 5 and 32 letters")
    private String name;

    @NotEmpty(message = "Field is required")
    private String startBusStopId;

    @NotEmpty(message = "Field is required")
    private String endBusStopId;

    public BusLine(String name, String startBusStopId, String endBusStopId) {
        this.name = name;
        this.startBusStopId = startBusStopId;
        this.endBusStopId = endBusStopId;
    }
}
