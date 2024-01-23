package grassrootunitedapi.grassrootunitedapi.controller.backoffice;

import grassrootunitedapi.grassrootunitedapi.constant.DateFormatConstant;
import grassrootunitedapi.grassrootunitedapi.constant.GlobalConstant;
import grassrootunitedapi.grassrootunitedapi.entity.GlobalParam;
import grassrootunitedapi.grassrootunitedapi.model.BaseResponse;
import grassrootunitedapi.grassrootunitedapi.model.backoffice.request.UpdateUserRequest;
import grassrootunitedapi.grassrootunitedapi.model.backoffice.request.UserQueryParams;
import grassrootunitedapi.grassrootunitedapi.model.general.PaginationBaseResponse;
import grassrootunitedapi.grassrootunitedapi.model.general.TokenPayload;
import grassrootunitedapi.grassrootunitedapi.model.backoffice.request.CreateUserRequest;
import grassrootunitedapi.grassrootunitedapi.model.backoffice.response.UserInformationResponse;
import grassrootunitedapi.grassrootunitedapi.service.backoffice.UserInformationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping(path = "/api/backoffice/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    private UserInformationService userInformationService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<UserInformationResponse> createUser(
            @RequestBody @Valid CreateUserRequest request, TokenPayload tokenPayload
    ) {
        return BaseResponse
                .<UserInformationResponse>builder()
                .data(userInformationService.createUser(request, tokenPayload.getId()))
                .code(HttpStatus.OK.value())
                .build();
    }

    @GetMapping()
    public PaginationBaseResponse<UserInformationResponse> getUserList(
            @ModelAttribute UserQueryParams params, TokenPayload tokenPayload
    ) throws ParseException {
        if (params.getPage() == null) {
            params.setPage(1);
        }
        if (params.getLimit() == null) {
            params.setLimit(10);
        }
        if (params.getCreatedAtStart() == null) {
            params.setCreatedAtStart(new SimpleDateFormat(DateFormatConstant.YYYY_MM_DD).parse(DateFormatConstant.DEFAULT_START_DATE));
        }
        if (params.getCreatedAtEnd() == null) {
            params.setCreatedAtEnd(new SimpleDateFormat(DateFormatConstant.YYYY_MM_DD).parse(DateFormatConstant.DEFAULT_END_DATE));
        }
        if (params.getCustomerNumber() == null) {
            params.setCustomerNumber(GlobalConstant.EMPTY_STRING);
        }
        return userInformationService.getUserList(params);
    }

    @GetMapping(path = "/{customerNumber}")
    public BaseResponse<UserInformationResponse> getUser (@PathVariable String customerNumber, TokenPayload tokenPayload) {
        return BaseResponse
                .<UserInformationResponse>builder()
                .data(userInformationService.getUser(customerNumber))
                .code(HttpStatus.OK.value())
                .build();
    }

    @PutMapping(path = "/{customerNumber}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<UserInformationResponse> updateUser (
            @PathVariable String customerNumber,
            @Valid @RequestBody UpdateUserRequest request,
            TokenPayload tokenPayload
    ) {
        return BaseResponse
                .<UserInformationResponse>builder()
                .data(userInformationService.updateUser(customerNumber, request, tokenPayload.getId()))
                .code(HttpStatus.OK.value())
                .build();
    }

    @DeleteMapping(path = "/{customerNumber}")
    public BaseResponse<String> deleteUser (
            @PathVariable String customerNumber,
            TokenPayload tokenPayload
    ) {
        userInformationService.deleteUser(customerNumber);

        return BaseResponse
                .<String>builder()
                .data("User " + customerNumber + " successfully deleted")
                .code(HttpStatus.OK.value())
                .build();
    }
}
