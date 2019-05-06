package pl.sdacademy.sdafinalprojectrest.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import pl.sdacademy.sdafinalprojectrest.model.user.User;
import pl.sdacademy.sdafinalprojectrest.repository.UserRepository;

import java.util.Arrays;
import java.util.List;

@Component
public class UserInit implements ApplicationRunner {

    private UserRepository userRepository;

    @Autowired
    public UserInit(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String adminAdmin = BCrypt.hashpw("adminadmin", BCrypt.gensalt());
        String userUser = BCrypt.hashpw("useruser", BCrypt.gensalt());

        List<User> users = Arrays.asList(
                new User("admin", adminAdmin, "admin@admin.com", User.Role.ADMIN),
                new User("user", userUser, "user@user.com", User.Role.USER));
        users.forEach(user -> {
            if(userRepository.findByUsername(user.getUsername()).isPresent()) {

            } else {
                users.forEach(userRepository::save);
            }
        });

    }
    
    
}
