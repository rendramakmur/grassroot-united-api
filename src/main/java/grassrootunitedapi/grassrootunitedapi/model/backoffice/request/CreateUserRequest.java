package grassrootunitedapi.grassrootunitedapi.model.backoffice.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import grassrootunitedapi.grassrootunitedapi.constant.RegexConstant;
import grassrootunitedapi.grassrootunitedapi.model.general.DefaultData;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CreateUserRequest {
    @JsonProperty(value = "userType")
    @NotNull
    private DefaultData userType;

    @JsonProperty(value = "firstName")
    @NotEmpty
    @Size(min = 2, max = 30)
    private String firstName;

    @JsonProperty(value = "lastName")
    @NotEmpty
    @Size(min = 2, max = 30)
    private String lastName;

    @JsonProperty(value = "email")
    @NotEmpty
    @Size(min = 6, max = 50)
    @Email
    private String email;

    @JsonProperty(value = "password")
    @Size(min = 6, max = 30)
    @NotEmpty
    private String password;

    @JsonProperty(value = "mobileNumber")
    @Size(min = 9, max = 15)
    @NotEmpty
    private String mobileNumber;

    @JsonProperty(value = "occupation")
    private DefaultData occupation;

    @Temporal(TemporalType.DATE)
    @NotNull
    @Past
    @JsonProperty(value = "dateOfBirth")
    private Date dateOfBirth;

    @JsonProperty(value = "gender")
    @NotNull
    private DefaultData gender;

    @JsonProperty(value = "photoProfile")
    @Pattern(regexp = RegexConstant.URL_REGEX, message = "Invalid URL")
    private String photoProfile;

    @JsonProperty(value = "address")
    private String address;

    @JsonProperty(value = "city")
    private String city;

    @JsonProperty(value = "postalCode")
    private String postalCode;

    @JsonProperty(value = "bodySize")
    private DefaultData bodySize;

    @JsonProperty(value = "emailStatus")
    @NotNull
    private Boolean emailStatus;
}
