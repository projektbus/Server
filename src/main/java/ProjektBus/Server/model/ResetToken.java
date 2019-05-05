package ProjektBus.Server.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ResetToken {
    @Id
    private String tokenId;
    private String tokenCode;
    private Date createdDate;
    private String userId;

    public ResetToken(String userId) {
        this.userId = userId;
        createdDate = new Date();
        tokenCode = UUID.randomUUID().toString();
    }

}
