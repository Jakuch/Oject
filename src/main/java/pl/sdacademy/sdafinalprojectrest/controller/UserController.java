package pl.sdacademy.sdafinalprojectrest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.sdafinalprojectrest.model.user.User;
import pl.sdacademy.sdafinalprojectrest.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {


    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> showAll() {
        return userRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody User user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("User already exists!");
        } else {
            return userRepository.save(user);
        }
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lack of user with requested ID"));
    }

    @PutMapping("/{id}")
    public User updateUserRole(@RequestBody User user, @PathVariable Long id) {
        User userToBeUpdated = getUserById(id);
        userToBeUpdated.setRole(user.getRole());
        return userToBeUpdated;
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.findById(id)
                .ifPresent(user -> userRepository.delete(user));
    }

}
