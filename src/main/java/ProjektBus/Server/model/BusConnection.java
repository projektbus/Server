package ProjektBus.Server.model;

import ProjektBus.Server.validation.validationInterfaces.ExistBusLine;
import ProjektBus.Server.validation.validationInterfaces.ExistBusStop;
import ProjektBus.Server.validation.validationInterfaces.ExistCarrier;
import lombok.*;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BusConnection {

    @Id
    private String id;
    @NotNull(message = "{field.required}")
    @ExistBusLine(message = "{busLine.exists}")
    private String busLineId;
    @NotNull(message = "{field.required}")
    @ExistCarrier(message = "{carrier.exists}")
    private String carrierId;
    @NotNull(message = "{field.required}")
    @ExistBusStop (message = "{busStop.exists}")
    private String busStopId;
    @NotNull(message = "{field.required}")
    private LocalTime departureTime;

    public BusConnection(String busLineId, String carrierId, String busStopId, LocalTime departureTime) {
        this.busLineId = busLineId;
        this.carrierId = carrierId;
        this.busStopId = busStopId;
        this.departureTime = departureTime;
    }
}
