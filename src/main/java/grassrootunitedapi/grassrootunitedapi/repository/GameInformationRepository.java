package grassrootunitedapi.grassrootunitedapi.repository;

import grassrootunitedapi.grassrootunitedapi.entity.GameInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameInformationRepository extends JpaRepository<GameInformation, Long> {
}
