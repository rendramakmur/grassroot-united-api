package grassrootunitedapi.grassrootunitedapi.repository;

import grassrootunitedapi.grassrootunitedapi.entity.GameRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRegistrationRepository extends JpaRepository<GameRegistration, Long> {
}
