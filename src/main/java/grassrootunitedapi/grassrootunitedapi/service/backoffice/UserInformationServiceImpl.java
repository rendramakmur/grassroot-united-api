package grassrootunitedapi.grassrootunitedapi.service.backoffice;

import grassrootunitedapi.grassrootunitedapi.entity.UserInformation;
import grassrootunitedapi.grassrootunitedapi.helper.CustomerNumberGenerator;
import grassrootunitedapi.grassrootunitedapi.model.backoffice.request.CreateUserRequest;
import grassrootunitedapi.grassrootunitedapi.model.backoffice.response.UserInformationResponse;
import grassrootunitedapi.grassrootunitedapi.repository.UserInformationRepository;
import grassrootunitedapi.grassrootunitedapi.service.general.ValidatorService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.net.http.HttpResponse;
import java.util.UUID;

@Service
public class UserInformationServiceImpl implements UserInformationService {
    @Autowired
    private UserInformationRepository userRepository;

    @Autowired
    private ValidatorService validatorService;

    @Autowired
    private CustomerNumberGenerator customerNumberGenerator;

    @Autowired
    private UserInformationBuilder userInformationBuilder;

    @Override
    public UserInformationResponse createUser(CreateUserRequest request, Long actorId) {
        validatorService.validateRequest(request);
        validatorService.validateDefaultData(request.getUserType(), "user type");
        validatorService.validateDefaultData(request.getGender(), "gender");

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email already exist");
        }

        UserInformation user = new UserInformation();
        user.setCustomerNumber(customerNumberGenerator.generateCustomerNumber());
        user.setUserType(request.getUserType().getId());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setMobileNumber(request.getMobileNumber());
        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        user.setDateOfBirth(request.getDateOfBirth());
        user.setGender(request.getGender().getId());
        user.setPhotoProfile(request.getPhotoProfile());
        user.setAddress(request.getAddress());
        user.setCity(request.getCity());
        user.setPostalCode(request.getPostalCode());
        user.setBodySize(request.getBodySize().getId());
        user.setEmailStatus(request.getEmailStatus());
        user.setActivationCode(UUID.randomUUID().toString());
        user.setCreatedBy(actorId);

        UserInformation savedUser = userRepository.save(user);

        return userInformationBuilder.build(savedUser);
    }
}
