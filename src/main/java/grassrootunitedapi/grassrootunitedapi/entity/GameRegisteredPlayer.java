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
@Table(name = "game_registered_player")
public class GameRegisteredPlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grp_id")
    private Long id;

    @Column(name = "grp_gd_id")
    private Long gameDataId;

    @Column(name = "grp_ui_id")
    private Long userId;

    @Column(name = "grp_is_outfield")
    private Boolean isOutfield;

    @Column(name = "grp_amount_paid")
    private Double amountPaid;

    @Column(name = "grp_paid_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paidAt;

    @Column(name = "grp_transaction_number")
    private String transactionNumber;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "grp_created_at")
    private Date createdAt;

    @Column(name = "grp_created_by")
    private Long createdBy;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "grp_updated_at")
    private Date updatedAt;

    @Column(name = "grp_updated_by")
    private Long updatedBy;
}
