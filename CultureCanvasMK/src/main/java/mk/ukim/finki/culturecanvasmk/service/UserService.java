package mk.ukim.finki.culturecanvasmk.service;

import mk.ukim.finki.culturecanvasmk.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    List<User> listUsers();

    Boolean checkUsernameAndPassword(String username, String password);

    User findByUsernameAndPassword(String username, String password);

    Boolean registerUser(String firstName, String lastName, String username, String email, String password, String token);

    void deleteById(Long userId);

    boolean confirmRegistration(String token);
    Optional<User> findByUsername(String username);
}
