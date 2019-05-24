package ProjektBus.Server.model;

import ProjektBus.Server.validation.validationInterfaces.ExistBusStop;
import lombok.*;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BusLine {

    @Id
    private String id;

    @NotNull(message = "{field.required}")
    @ExistBusStop(message = "{busStop.exists}")
    private String startBusStopId;

    @NotNull(message = "{field.required}")
    @ExistBusStop(message = "{busStop.exists}")
    private String endBusStopId;

    private List<BusStop> busStopList = new ArrayList<>();

    public void addBusStopToList(BusStop busStop) {
        this.busStopList.add(busStop);
    }

    public void deleteBusStopFromList(BusStop busStop) {
        this.busStopList.remove(busStop);
    }

    public boolean isBusStopOnList(String busStopId) {
        return this.busStopList.stream().anyMatch(e-> e.getId().equals(busStopId))
                || this.startBusStopId.equals(busStopId) || this.endBusStopId.equals(busStopId);
    }

}
