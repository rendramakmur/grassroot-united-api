package grassrootunitedapi.grassrootunitedapi.controller.backoffice;

import grassrootunitedapi.grassrootunitedapi.model.BaseResponse;
import grassrootunitedapi.grassrootunitedapi.model.general.TokenPayload;
import grassrootunitedapi.grassrootunitedapi.model.backoffice.request.CreateUserRequest;
import grassrootunitedapi.grassrootunitedapi.model.backoffice.response.UserInformationResponse;
import grassrootunitedapi.grassrootunitedapi.service.backoffice.UserInformationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/backoffice/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    private UserInformationService userInformationService;

    @PostMapping()
    public BaseResponse<UserInformationResponse> createUser(@RequestBody @Valid CreateUserRequest request, TokenPayload tokenPayload) {
        return BaseResponse
                .<UserInformationResponse>builder()
                .data(userInformationService.createUser(request, tokenPayload.getId()))
                .code(HttpStatus.OK.value())
                .build();
    }
}
