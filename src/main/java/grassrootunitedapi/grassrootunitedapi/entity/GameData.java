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
@Table(name = "game_data")
public class GameData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gd_id")
    private Long id;

    @Column(name = "gd_game_number")
    private String gameNumber;

    @Column(name = "gd_venue_name")
    private String venueName;

    @Column(name = "gd_venue_address")
    private String venueAddress;

    @Column(name = "gd_map_url")
    private String mapUrl;

    @Column(name = "gd_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date gameTime;

    @Column(name = "gd_goalkeeper_quota")
    private Integer goalkeeperQuota;

    @Column(name = "gd_outfield_quota")
    private Integer outfieldQuota;

    @Column(name = "gd_goalkeeper_price")
    private Double goalkeeperPrice;

    @Column(name = "gd_outfield_price")
    private Double outfieldPrice;

    @Column(name = "gd_total_cost")
    private Double totalCost;

    @Column(name = "gd_notes")
    private String notes;

    @Column(name = "gd_status")
    private Boolean status;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "gd_created_at")
    private Date createdAt;

    @Column(name = "gd_created_by")
    private Long createdBy;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "gd_updated_at")
    private Date updatedAt;

    @Column(name = "gd_updated_by")
    private Long updatedBy;
}
