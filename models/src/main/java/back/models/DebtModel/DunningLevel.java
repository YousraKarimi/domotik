package back.models.DebtModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
@Table(name = "dunninglevel")
public class DunningLevel {

    @Id
    @Column(name="id_level")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLevel;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="id_document")
    private DunningDocument dunningDocument;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name="id_action")
    private DunningAction dunningAction;

    @Column(name = "level_name")
    private String levelName;

    @Column(name = "level_state")
    private boolean levelState;

    @Column(name = "days_overdue")
    private int daysOverdue;
    public DunningLevel(String levelName, boolean levelState, int daysOverdue) {
        this.levelName = levelName;
        this.levelState = levelState;
        this.daysOverdue = daysOverdue;
    }

    public DunningLevel() {

    }
}
