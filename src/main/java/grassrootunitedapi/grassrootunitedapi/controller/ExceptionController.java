package grassrootunitedapi.grassrootunitedapi.controller;

import grassrootunitedapi.grassrootunitedapi.model.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<BaseResponse<String>> constrainViolation(ResponseStatusException error) {
        return ResponseEntity
                .status(error.getStatusCode())
                .body(
                        BaseResponse
                                .<String>builder()
                                .error(error.getReason())
                                .code(error.getStatusCode().value())
                                .build()
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<String>> constrainViolation(MethodArgumentNotValidException error) {
        Map<String, String> errors = new HashMap<>();
        error.getBindingResult().getAllErrors().forEach((e) -> {
            String fieldName = ((FieldError) e).getField();
            String message = e.getDefaultMessage();
            errors.put(fieldName, message);
        });

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        BaseResponse
                                .<String>builder()
                                .error(errors)
                                .code(HttpStatus.BAD_REQUEST.value())
                                .build()
                );
    }
}
