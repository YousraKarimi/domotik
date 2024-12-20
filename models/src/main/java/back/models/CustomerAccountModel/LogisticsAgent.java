package back.models.CustomerAccountModel;

import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Data
@Table(name = "logisticsagent")
public class LogisticsAgent extends Utilisateur {
}
