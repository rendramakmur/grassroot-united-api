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
@Table(name = "preferred_position")
public class PreferredPosition {
    @Id
    @Column(name = "pp_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pp_ui_id")
    private Long userId;

    @Column(name = "pp_position")
    private Long position;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "pp_created_at")
    private Date createdAt;

    @Column(name = "pp_created_by")
    private Long createdBy;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "pp_updated_at")
    private Date updatedAt;

    @Column(name = "pp_updated_by")
    private Long updatedBy;
}
