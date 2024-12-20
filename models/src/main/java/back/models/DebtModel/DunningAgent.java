package back.models.DebtModel;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
@Table(name = "dunningagent")
public class DunningAgent  {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_agent", nullable = false)
    private Long id_agent;

}
