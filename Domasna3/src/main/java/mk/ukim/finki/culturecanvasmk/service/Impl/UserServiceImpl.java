package mk.ukim.finki.culturecanvasmk.service.Impl;

import mk.ukim.finki.culturecanvasmk.model.User;
import mk.ukim.finki.culturecanvasmk.repository.jpa.UserRepository;
import mk.ukim.finki.culturecanvasmk.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
    public Boolean registerUser(String firstName, String lastName, String username, String password) {
        if (findByUsernameAndPassword(username, password) == null){
            userRepository.save(new User(username, password, "USER"));
            return true;
        }
        return false;
    }
}
