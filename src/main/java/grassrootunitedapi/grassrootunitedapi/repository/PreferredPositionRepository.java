package grassrootunitedapi.grassrootunitedapi.repository;

import grassrootunitedapi.grassrootunitedapi.entity.PreferredPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferredPositionRepository extends JpaRepository<PreferredPosition, Long> {
}
