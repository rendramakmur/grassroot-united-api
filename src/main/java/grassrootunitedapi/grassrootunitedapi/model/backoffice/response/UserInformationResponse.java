package grassrootunitedapi.grassrootunitedapi.model.backoffice.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import grassrootunitedapi.grassrootunitedapi.model.general.DefaultData;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UserInformationResponse {
    @JsonProperty(value = "id")
    private Long id;

    @Column(name = "userType")
    private DefaultData userType;

    @Column(name = "customerNumber")
    private String customerNumber;

    @JsonProperty(value = "firstName")
    private String firstName;

    @JsonProperty(value = "lastName")
    private String lastName;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "mobileNumber")
    private String mobileNumber;

    @JsonProperty(value = "occupation")
    private DefaultData occupation;

    @JsonProperty(value = "date_of_birth")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Jakarta")
    private Date dateOfBirth;

    @JsonProperty(value = "gender")
    @NotNull
    private DefaultData gender;

    @JsonProperty(value = "photoProfile")
    private String photoProfile;

    @JsonProperty(value = "address")
    private String address;

    @JsonProperty(value = "city")
    private String city;

    @JsonProperty(value = "postalCode")
    private String postalCode;

    @JsonProperty(value = "bodySize")
    private DefaultData bodySize;

    @JsonProperty(value = "ui_activation_code")
    private String activationCode;

    @JsonProperty(value = "ui_email_status")
    private Boolean emailStatus;
}
