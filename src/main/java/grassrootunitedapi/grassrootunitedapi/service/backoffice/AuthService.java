package grassrootunitedapi.grassrootunitedapi.service.backoffice;

import grassrootunitedapi.grassrootunitedapi.model.general.TokenPayload;
import grassrootunitedapi.grassrootunitedapi.model.backoffice.request.LoginRequest;

public interface AuthService {

    public TokenPayload login(LoginRequest request);
}
