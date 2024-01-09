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
@Table(name = "game_cost")
public class GameCost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gc_id")
    private Long id;

    @Column(name = "gc_gd_id")
    private Long gameDataId;

    @Column(name = "gc_description")
    private String description;

    @Column(name = "gc_cost")
    private Double cost;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "gc_created_at")
    private Date createdAt;

    @Column(name = "gc_created_by")
    private Long createdBy;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "gc_updated_at")
    private Date updatedAt;

    @Column(name = "gc_updated_by")
    private Long updatedBy;
}
