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
@Table(name = "game_information")
public class GameInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gi_id")
    private Long id;

    @Column(name = "gi_gd_id")
    private Long gameDataId;

    @Column(name = "gi_type")
    private Long type;

    @Column(name = "gi_description")
    private String description;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "gi_created_at")
    private Date createdAt;

    @Column(name = "gi_created_by")
    private Long createdBy;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "gi_updated_at")
    private Date updatedAt;

    @Column(name = "gi_updated_by")
    private Long updatedBy;
}
