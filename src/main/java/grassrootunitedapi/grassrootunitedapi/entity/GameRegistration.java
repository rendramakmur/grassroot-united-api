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
@Table(name = "game_registration")
public class GameRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gr_id")
    private Long id;

    @Column(name = "gr_gd_id")
    private Long gameDataId;

    @Column(name = "gr_ui_id")
    private Long userId;

    @Column(name = "gr_is_outfield")
    private Boolean isOutfield;

    @Column(name = "gr_amount")
    private Double amount;

    @Column(name = "gr_transaction_number")
    private String transactionNumber;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "gr_created_at")
    private Date createdAt;

    @Column(name = "gr_created_by")
    private Long createdBy;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "gr_updated_at")
    private Date updatedAt;

    @Column(name = "gr_updated_by")
    private Long updatedBy;
}
