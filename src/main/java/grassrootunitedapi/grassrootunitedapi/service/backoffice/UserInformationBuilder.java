package grassrootunitedapi.grassrootunitedapi.service.backoffice;

import grassrootunitedapi.grassrootunitedapi.constant.SlugConstant;
import grassrootunitedapi.grassrootunitedapi.entity.UserInformation;
import grassrootunitedapi.grassrootunitedapi.model.backoffice.response.UserInformationResponse;
import grassrootunitedapi.grassrootunitedapi.service.general.GlobalParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserInformationBuilder {
    @Autowired
    private GlobalParamService globalParamService;

    public UserInformationResponse build(UserInformation user) {
        return UserInformationResponse
                .builder()
                .id(user.getId())
                .userType(globalParamService.getDefaultDataBySlugAndCodeId(SlugConstant.USER_TYPE_SLUG, user.getUserType()))
                .customerNumber(user.getCustomerNumber())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .mobileNumber(user.getMobileNumber())
                .occupation(globalParamService.getDefaultDataBySlugAndCodeId(SlugConstant.OCCUPATION_SLUG, user.getOccupation()))
                .dateOfBirth(user.getDateOfBirth())
                .gender(globalParamService.getDefaultDataBySlugAndCodeId(SlugConstant.GENDER_SLUG, user.getGender()))
                .photoProfile(user.getPhotoProfile())
                .address(user.getAddress())
                .city(user.getCity())
                .postalCode(user.getPostalCode())
                .bodySize(globalParamService.getDefaultDataBySlugAndCodeId(SlugConstant.BODY_SIZE_SLUG, user.getBodySize()))
                .activationCode(user.getActivationCode())
                .emailStatus(user.getEmailStatus())
                .build();
    }
}
