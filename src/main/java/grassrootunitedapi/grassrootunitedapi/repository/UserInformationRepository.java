package grassrootunitedapi.grassrootunitedapi.repository;

import grassrootunitedapi.grassrootunitedapi.entity.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInformationRepository extends JpaRepository<UserInformation, Long> {
    public Optional<UserInformation> findByEmail(String email);

    public Boolean existsByEmail(String email);

    public Boolean existsByCustomerNumber(String customerNumber);
}
