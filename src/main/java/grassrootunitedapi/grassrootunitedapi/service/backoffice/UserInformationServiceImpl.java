package grassrootunitedapi.grassrootunitedapi.service.backoffice;

import grassrootunitedapi.grassrootunitedapi.entity.UserInformation;
import grassrootunitedapi.grassrootunitedapi.helper.CustomerNumberGenerator;
import grassrootunitedapi.grassrootunitedapi.model.backoffice.request.CreateUserRequest;
import grassrootunitedapi.grassrootunitedapi.model.backoffice.request.UpdateUserRequest;
import grassrootunitedapi.grassrootunitedapi.model.backoffice.request.UserQueryParams;
import grassrootunitedapi.grassrootunitedapi.model.backoffice.response.UserInformationResponse;
import grassrootunitedapi.grassrootunitedapi.model.general.PaginationBaseResponse;
import grassrootunitedapi.grassrootunitedapi.repository.UserInformationRepository;
import grassrootunitedapi.grassrootunitedapi.service.general.ValidatorService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.net.http.HttpResponse;
import java.util.List;
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
        user.setOccupation(request.getOccupation().getId());
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

    @Override
    public PaginationBaseResponse<UserInformationResponse> getUserList(UserQueryParams params) {
        Page<UserInformation> pagingUser = userRepository.getAllUserWithFilter(
                params.getCustomerNumber(),
                params.getUserType(),
                params.getCreatedAtStart(),
                params.getCreatedAtEnd(),
                PageRequest.of((params.getPage() - 1), params.getLimit())
        );

        List<UserInformationResponse> pagingData = pagingUser.stream().map(user ->
                userInformationBuilder.build(user)
        ).toList();

        return PaginationBaseResponse
                .<UserInformationResponse>builder()
                .currentPage(params.getPage())
                .totalData(pagingUser.getTotalElements())
                .totalPage(pagingUser.getTotalPages())
                .pagingData(pagingData)
                .build();
    }

    @Override
    public UserInformationResponse getUser(String customerNumber) {
        UserInformation user = userRepository.findByCustomerNumber(customerNumber).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "user information not found")
        );

        return userInformationBuilder.build(user);
    }

    @Override
    public UserInformationResponse updateUser(String customerNumber, UpdateUserRequest request, Long actorId) {
        validatorService.validateRequest(request);
        validatorService.validateDefaultData(request.getUserType(), "user type");
        validatorService.validateDefaultData(request.getGender(), "gender");

        UserInformation user = userRepository.findByCustomerNumber(customerNumber).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "user information not found")
        );

        if (userRepository.existsByEmailAndIdNot(request.getEmail(), user.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email already exist");
        }

        user.setUserType(request.getUserType().getId());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setMobileNumber(request.getMobileNumber());
        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        user.setDateOfBirth(request.getDateOfBirth());
        user.setGender(request.getGender().getId());
        user.setPhotoProfile(request.getPhotoProfile());
        user.setOccupation(request.getOccupation().getId());
        user.setAddress(request.getAddress());
        user.setCity(request.getCity());
        user.setPostalCode(request.getPostalCode());
        user.setBodySize(request.getBodySize().getId());
        user.setEmailStatus(request.getEmailStatus());
        user.setUpdatedBy(actorId);

        UserInformation savedUser = userRepository.save(user);

        return userInformationBuilder.build(savedUser);
    }

    @Override
    public void deleteUser(String customerNumber) {
        UserInformation user = userRepository.findByCustomerNumber(customerNumber).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "user information not found")
        );

        try {
            userRepository.deleteById(user.getId());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "user not successfully deleted");
        }
    }
}
