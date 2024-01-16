package grassrootunitedapi.grassrootunitedapi.service.backoffice;

import grassrootunitedapi.grassrootunitedapi.model.backoffice.request.CreateUserRequest;
import grassrootunitedapi.grassrootunitedapi.model.backoffice.response.UserInformationResponse;

public interface UserInformationService {
    public UserInformationResponse createUser(CreateUserRequest request, Long actorId);
}
