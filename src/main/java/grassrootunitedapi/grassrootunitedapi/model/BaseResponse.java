package grassrootunitedapi.grassrootunitedapi.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaseResponse<T> {
    private T data;

    private Integer code;

    private Object error;
}
