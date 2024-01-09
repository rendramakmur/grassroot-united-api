package grassrootunitedapi.grassrootunitedapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "user_information")
public class UserInformation {
    @Id
    @Column(name = "ui_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ui_user_type")
    private Long userType;

    @Column(name = "ui_customer_number")
    private String customerNumber;

    @Column(name = "ui_first_name")
    private String firstName;

    @Column(name = "ui_last_name")
    private String lastName;

    @Column(name = "ui_email")
    private String email;

    @Column(name = "ui_password")
    private String password;

    @Column(name = "ui_mobile_number")
    private String mobileNumber;

    @Column(name = "ui_occupation")
    private Long occupation;

    @Temporal(TemporalType.DATE)
    @Column(name = "ui_date_of_birth")
    private Date dateOfBirth;

    @Column(name = "ui_gender")
    private Long gender;

    @Column(name = "ui_photo_profile")
    private String photoProfile;

    @Column(name = "ui_address")
    private String address;

    @Column(name = "ui_city")
    private String city;

    @Column(name = "ui_postal_code")
    private String postalCode;

    @Column(name = "ui_body_size")
    private Long bodySize;

    @Column(name = "ui_activation_code")
    private String activationCode;

    @Column(name = "ui_email_status")
    private Boolean emailStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ui_verified_at")
    private Date verifiedAt;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ui_created_at")
    private Date createdAt;

    @Column(name = "ui_created_by")
    private Long createdBy;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ui_updated_at")
    private Date updatedAt;

    @Column(name = "ui_updated_by")
    private Long updatedBy;
}
