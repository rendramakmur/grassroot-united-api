package grassrootunitedapi.grassrootunitedapi.model.general;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class TokenPayload {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "userType")
    private Long userType;

    @JsonProperty(value = "firstName")
    private String firstName;

    @JsonProperty(value = "lastName")
    private String lastName;

    @JsonProperty(value = "token")
    private String token;

    @JsonProperty(value = "token_expired_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Jakarta")
    private Date expiredAt;
}
