package mk.ukim.finki.culturecanvasmk.service;

import mk.ukim.finki.culturecanvasmk.model.User;

public interface UserService {
    Boolean checkUsernameAndPassword(String username, String password);

    User findByUsernameAndPassword(String username, String password);

    Boolean registerUser(String firstName, String lastName, String username, String password);


}
