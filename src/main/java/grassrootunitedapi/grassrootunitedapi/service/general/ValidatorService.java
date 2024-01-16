package grassrootunitedapi.grassrootunitedapi.service.general;

import grassrootunitedapi.grassrootunitedapi.model.general.DefaultData;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@Component
public class ValidatorService {
    @Autowired
    private Validator validator;

    public void validateRequest(Object request) {
        Set<ConstraintViolation<Object>> validate = validator.validate(request);
        if (!validate.isEmpty()) {
            throw new ConstraintViolationException(validate);
        }
    }

    public void validateDefaultData(DefaultData data, String message) {
        if (data.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message + "is mandatory");
        }
    }
}
