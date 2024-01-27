package mk.ukim.finki.culturecanvasmk.service.Impl;

import mk.ukim.finki.culturecanvasmk.model.Role;
import mk.ukim.finki.culturecanvasmk.model.User;
import mk.ukim.finki.culturecanvasmk.repository.jpa.UserRepository;
import mk.ukim.finki.culturecanvasmk.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @Override
    public Boolean checkUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password) != null;
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public Boolean registerUser(String firstName, String lastName, String username, String email,  String password, String token) {
        if (findByUsername(username).isEmpty()){
            userRepository.save(new User(username, passwordEncoder.encode(password), email, Role.USER, token));
            return true;
        }
        return false;
    }

    @Override
    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities("ROLE_" + user.getRole().toString())
                .build();
    }

    public boolean confirmRegistration(String token) {
        // Validate the token and mark the user as registered
        User user = userRepository.findByConfirmationToken(token);
        if (user != null && !user.isRegistered() && isValidTokenExpiration(user.getConfirmationTokenExpiration())) {
            user.setRegistered(true);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    private boolean isValidTokenExpiration(LocalDate expirationDate) {
        // Check if the token expiration date is valid (not expired)
        return expirationDate != null && expirationDate.isAfter(LocalDate.now());
    }
}
