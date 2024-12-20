package back.models.CustomerAccountModel;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@Table(name = "utilisateur")
public class Utilisateur {

    @Id
    @Column(name="id_user")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idUser;

    @Column(name = "password")
    private String password;

    @Column(name = "user_name")
    private String userName;

    public Utilisateur(String userName, String password) {
        this.password = password;
        this.userName = userName;
    }

    public Utilisateur() {

    }
}
