package back.models.CustomerAccountModel;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends Utilisateur {
    public Admin(String userName, String password) {
        super(userName, password);
    }

    public Admin() {

    }
}
