package grassrootunitedapi.grassrootunitedapi.service.backoffice;

import grassrootunitedapi.grassrootunitedapi.entity.UserInformation;
import grassrootunitedapi.grassrootunitedapi.helper.JwtProvider;
import grassrootunitedapi.grassrootunitedapi.model.general.TokenPayload;
import grassrootunitedapi.grassrootunitedapi.model.backoffice.request.LoginRequest;
import grassrootunitedapi.grassrootunitedapi.repository.UserInformationRepository;
import grassrootunitedapi.grassrootunitedapi.service.general.ValidatorService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserInformationRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private ValidatorService validatorService;

    @Override
    public TokenPayload login(LoginRequest request) {
        validatorService.validateRequest(request);

        UserInformation userInformation = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.BAD_REQUEST, "You have entered an invalid username/password")
                );

        if (BCrypt.checkpw(request.getPassword(), userInformation.getPassword())) {
            String token = jwtProvider.generateToken(userInformation);

            return jwtProvider.validateBackOfficeToken(token);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You have entered an invalid username/password");
        }
    }
}
