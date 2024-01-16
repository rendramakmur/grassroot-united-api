package grassrootunitedapi.grassrootunitedapi.service.general;

import grassrootunitedapi.grassrootunitedapi.entity.GlobalParam;
import grassrootunitedapi.grassrootunitedapi.model.general.DefaultData;
import grassrootunitedapi.grassrootunitedapi.repository.GlobalParamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class GlobalParamService {
    @Autowired
    private GlobalParamRepository globalParamRepository;

    public DefaultData getDefaultDataBySlugAndCodeId(String slug, Long codeId) {
        Optional<GlobalParam> data = globalParamRepository.findBySlugAndCodeId(slug, codeId);

        if (data.isPresent()) {
            return DefaultData
                    .builder()
                    .id(data.get().getCodeId())
                    .name(data.get().getDescription())
                    .build();
        } else {
            return DefaultData
                    .builder()
                    .id(null)
                    .name(null)
                    .build();
        }
    }
}
