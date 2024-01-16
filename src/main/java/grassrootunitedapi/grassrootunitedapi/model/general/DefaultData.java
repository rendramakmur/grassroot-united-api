package grassrootunitedapi.grassrootunitedapi.model.general;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DefaultData {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;
}
