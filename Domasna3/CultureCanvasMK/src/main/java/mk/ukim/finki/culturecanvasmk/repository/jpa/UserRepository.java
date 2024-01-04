package mk.ukim.finki.culturecanvasmk.repository.jpa;

import mk.ukim.finki.culturecanvasmk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(String username, String password);

    void deleteById(long id);

    Optional<User> findByUsername(String username);

}
