package back.models.DebtModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
@Table(name = "dunningaction")
public class DunningAction {

    @Id
    @Column(name="id_action")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAction;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name="id_template")
    private DunningTemplate dunningTemplate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="id_document")
    private DunningDocument dunningDocument;

    @JsonIgnore
    @OneToOne
    private DunningLevel dunningLevel;

    @Column(name = "action_name")
    private String actionName;

    @Enumerated(EnumType.STRING)
    @Column(name = "channel")
    private ActionChannel channel;

    public DunningAction(DunningTemplate dunningTemplate, DunningLevel dunningLevel, String actionName, ActionChannel channel) {
        this.dunningTemplate = dunningTemplate;
        this.dunningLevel = dunningLevel;
        this.actionName = actionName;
        this.channel = channel;
    }

    public DunningAction() {

    }
}
