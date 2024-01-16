package grassrootunitedapi.grassrootunitedapi.repository;

import grassrootunitedapi.grassrootunitedapi.entity.GlobalParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GlobalParamRepository extends JpaRepository<GlobalParam, Long> {
    public Optional<GlobalParam> findBySlugAndCodeId(String slug, Long codeId);
}
