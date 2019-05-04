package ProjektBus.Server.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BusLine {

    @Id
    private String id;

    @NotEmpty
    @Size(min = 5, max = 32, message = "Size must be between 5 and 32 letters")
    private String name;

    @NotEmpty(message = "Field is required")
    private String startBusStopId;

    @NotEmpty(message = "Field is required")
    private String endBusStopId;

}
