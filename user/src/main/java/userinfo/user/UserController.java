package userinfo.user;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        // Generate password
        String password = generatePassword(user);
        user.setPassword(password);

        // Save to the database
        userRepository.save(user);

        return user;
    }

    private String generatePassword(User user) {
        String firstName = user.getFirstName().substring(0, 2);
        String middleName = user.getMiddleName().substring(0, 2);
        String lastName = user.getLastName().substring(0, 2);
        String randomDigits = String.format("%03d", new Random().nextInt(1000));

        return firstName + middleName + lastName + randomDigits;
    }
}
