package mk.ukim.finki.culturecanvasmk.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@Table(name = "site_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String password;

    private String confirmationToken;

    private LocalDate confirmationTokenExpiration;

    private boolean registered;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    public User() {}
    public User(String username, String password, String email, Role role, String confirmationToken) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.confirmationToken = confirmationToken;
        this.confirmationTokenExpiration = LocalDate.now().plusDays(2);
        this.registered = false;
    }

}
