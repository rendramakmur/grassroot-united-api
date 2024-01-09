package grassrootunitedapi.grassrootunitedapi.repository;

import grassrootunitedapi.grassrootunitedapi.entity.GameCost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameCostRepository extends JpaRepository<GameCost, Long> {
}
