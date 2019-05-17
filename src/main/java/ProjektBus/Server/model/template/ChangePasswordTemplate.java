package ProjektBus.Server.model.template;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class ChangePasswordTemplate {
    @NotNull(message = "{field.required}")
    private String login;
    @NotNull(message = "{field.required}")
    private String password;
    @NotNull(message = "{field.required}")
    @Size(min = 5, max = 32, message = "{name.size}")
    @Pattern(regexp = ".*[a-zA-Z+].*", message = "{password.letter}")
    @Pattern(regexp = ".*\\d+.*", message = "{password.number}")
    @Pattern(regexp = ".*[^A-Za-z0-9].*", message = "{password.special}")
    private String newPassword;
}
