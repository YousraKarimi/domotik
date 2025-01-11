package episen.back.Config.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "password")
    private String password;

    @Column(name = "login")
    private String login;

    @JsonIgnore
    @OneToMany(mappedBy = "user_id", cascade = CascadeType.ALL)
    private List<Device> devices;

    public User(String fullName, String emailAddress, String password, String login) {
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.login = login;
    }

    public User() {

    }
}
