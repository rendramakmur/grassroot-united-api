package grassrootunitedapi.grassrootunitedapi.controller.backoffice;

import grassrootunitedapi.grassrootunitedapi.model.BaseResponse;
import grassrootunitedapi.grassrootunitedapi.model.general.TokenPayload;
import grassrootunitedapi.grassrootunitedapi.model.backoffice.request.LoginRequest;
import grassrootunitedapi.grassrootunitedapi.service.backoffice.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/backoffice/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping(value = "login")
    public BaseResponse<TokenPayload> login(@RequestBody LoginRequest request) {
        return BaseResponse
                .<TokenPayload>builder()
                .data(authService.login(request))
                .code(HttpStatus.OK.value())
                .build();
    }
}
