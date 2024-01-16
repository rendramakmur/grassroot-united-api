package grassrootunitedapi.grassrootunitedapi.model.backoffice.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequest {
    @JsonProperty(value = "email")
    @NotEmpty
    private String email;

    @JsonProperty(value = "password")
    @NotEmpty
    private String password;
}
