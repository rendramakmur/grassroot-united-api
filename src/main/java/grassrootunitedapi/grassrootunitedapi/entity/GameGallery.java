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
@Table(name = "game_gallery")
public class GameGallery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gg_id")
    private Long id;

    @Column(name = "gg_gd_id")
    private Long gameDataId;

    @Column(name = "gg_image_url")
    private String imageUrl;

    @Column(name = "gg_alt_image")
    private String altImage;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "gg_created_at")
    private Date createdAt;

    @Column(name = "gg_created_by")
    private Long createdBy;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "gg_updated_at")
    private Date updatedAt;

    @Column(name = "gg_updated_by")
    private Long updatedBy;
}
