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
@Table(name = "global_param")
public class GlobalParam {
    @Id
    @Column(name = "gp_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gp_slug")
    private String slug;

    @Column(name = "gp_code_id")
    private Long codeId;

    @Column(name = "gp_name")
    private Long name;

    @Column(name = "gp_description")
    private Long description;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "gp_created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "gp_updated_at")
    private Date updatedAt;
}
