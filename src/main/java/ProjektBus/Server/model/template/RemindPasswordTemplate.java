package ProjektBus.Server.model.template;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RemindPasswordTemplate {
    String password;
    String confirmationPassword;
}
