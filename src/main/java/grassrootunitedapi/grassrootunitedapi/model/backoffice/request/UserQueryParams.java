package grassrootunitedapi.grassrootunitedapi.model.backoffice.request;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
public class UserQueryParams {
    private Integer page;
    private Integer limit;
    private String customerNumber;
    private Integer userType;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAtStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAtEnd;
}
