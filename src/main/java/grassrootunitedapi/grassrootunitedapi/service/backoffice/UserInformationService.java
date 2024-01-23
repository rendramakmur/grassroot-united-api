package grassrootunitedapi.grassrootunitedapi.service.backoffice;

import grassrootunitedapi.grassrootunitedapi.model.backoffice.request.CreateUserRequest;
import grassrootunitedapi.grassrootunitedapi.model.backoffice.request.UpdateUserRequest;
import grassrootunitedapi.grassrootunitedapi.model.backoffice.request.UserQueryParams;
import grassrootunitedapi.grassrootunitedapi.model.backoffice.response.UserInformationResponse;
import grassrootunitedapi.grassrootunitedapi.model.general.PaginationBaseResponse;

public interface UserInformationService {
    public UserInformationResponse createUser(CreateUserRequest request, Long actorId);

    public PaginationBaseResponse<UserInformationResponse> getUserList(UserQueryParams params);

    public UserInformationResponse getUser(String customerNumber);

    public UserInformationResponse updateUser(String customerNumber, UpdateUserRequest request, Long actorId);

    public void deleteUser(String customerNumber);
}
