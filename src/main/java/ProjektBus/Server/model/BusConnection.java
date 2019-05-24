package ProjektBus.Server.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BusConnection {

    @Id
    private String id;

    private String busLineId;
    private String carrierId;
    private String busStopId;
    private LocalTime departureTime;

    public BusConnection(String busLineId, String carrierId, String busStopId, LocalTime departureTime) {
        this.busLineId = busLineId;
        this.carrierId = carrierId;
        this.busStopId = busStopId;
        this.departureTime = departureTime;
    }
}
