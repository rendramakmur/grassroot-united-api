package grassrootunitedapi.grassrootunitedapi.repository;

import grassrootunitedapi.grassrootunitedapi.entity.GameGallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameGalleryRepository extends JpaRepository<GameGallery, Long> {
}
