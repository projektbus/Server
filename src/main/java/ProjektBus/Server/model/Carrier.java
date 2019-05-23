package ProjektBus.Server.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Carrier {

    @Id
    private String id;

    @NotNull(message = "{field.required}")
    private String name;

    public Carrier(String name) {
        this.name = name;
    }
}
