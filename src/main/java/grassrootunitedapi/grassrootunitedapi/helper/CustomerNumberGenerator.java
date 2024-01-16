package grassrootunitedapi.grassrootunitedapi.helper;

import grassrootunitedapi.grassrootunitedapi.repository.UserInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CustomerNumberGenerator {
    @Autowired
    private UserInformationRepository userRepository;

    public String generateCustomerNumber() {
        Random random = new Random();

        while (true) {
            Integer randomInts = random.nextInt(1_000_000) + 1;
            String number = String.format("%07d", randomInts);
            String customerNumber = "GRU-" + number;

            if (!userRepository.existsByCustomerNumber(customerNumber)) {
                return customerNumber;
            }
        }

    }
}
