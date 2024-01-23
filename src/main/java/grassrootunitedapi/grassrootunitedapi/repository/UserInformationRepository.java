package grassrootunitedapi.grassrootunitedapi.repository;

import grassrootunitedapi.grassrootunitedapi.entity.UserInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@Repository
public interface UserInformationRepository extends JpaRepository<UserInformation, Long> {
    public Optional<UserInformation> findByEmail(String email);

    public Optional<UserInformation> findByCustomerNumber(String customerNumber);

    @Query(
            value = "SELECT * FROM user_information " +
                    "WHERE (:customerNumber IS NULL OR ui_customer_number LIKE %:customerNumber%) " +
                    "AND (:userType IS NULL OR ui_user_type = :userType) " +
                    "AND (CAST(:createdAtStart AS TIMESTAMP) IS NULL OR ui_created_at >= CAST(:createdAtStart AS TIMESTAMP)) " +
                    "AND (CAST(:createdAtEnd AS TIMESTAMP) IS NULL OR ui_created_at <= CAST(:createdAtEnd AS TIMESTAMP)) ",
            countQuery = "SELECT COUNT(*) FROM user_information " +
                    "WHERE (:customerNumber IS NULL OR ui_customer_number LIKE %:customerNumber%) " +
                    "AND (:userType IS NULL OR ui_user_type = :userType) " +
                    "AND (CAST(:createdAtStart AS TIMESTAMP) IS NULL OR ui_created_at >= CAST(:createdAtStart AS TIMESTAMP)) " +
                    "AND (CAST(:createdAtEnd AS TIMESTAMP) IS NULL OR ui_created_at <= CAST(:createdAtEnd AS TIMESTAMP)) ",
            nativeQuery = true
    )
    public Page<UserInformation> getAllUserWithFilter(
            @Param("customerNumber") String customerNumber,
            @Param("userType") Integer userType,
            @Param("createdAtStart") Date createdAtStart,
            @Param("createdAtEnd") Date createdAtEnd,
            Pageable pageable
    );

    public Boolean existsByEmail(String email);

    public Boolean existsByEmailAndIdNot(String email, Long id);

    public Boolean existsByCustomerNumber(String customerNumber);
}
