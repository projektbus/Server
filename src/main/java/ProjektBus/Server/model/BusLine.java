package ProjektBus.Server.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class BusLine {
    private String name;
    private String startBusStopId;
    private String endBusStopId;

    public BusLine(String name, String startBusStopId, String endBusStopId) {
        this.name = name;
        this.startBusStopId = startBusStopId;
        this.endBusStopId = endBusStopId;
    }
}
