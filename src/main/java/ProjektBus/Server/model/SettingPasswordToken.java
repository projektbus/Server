package ProjektBus.Server.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
public class SettingPasswordToken {

    @Id
    private String tokenId;
    private String tokenCode;
    private String userId;
    private boolean active;

    public SettingPasswordToken(String userId) {
        this.userId = userId;
        this.active = true;
        tokenCode = UUID.randomUUID().toString();
    }

}

