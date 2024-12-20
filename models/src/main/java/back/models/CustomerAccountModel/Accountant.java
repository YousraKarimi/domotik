package back.models.CustomerAccountModel;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Accountant")
public class Accountant extends Utilisateur {
    public Accountant(String userName, String password) {
        super(userName, password);
    }

    public Accountant() {

    }
}
