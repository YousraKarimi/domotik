package back.models.DebtModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
@Table(name = "dunningtemplate")
public class DunningTemplate {
    @Id
    @Column(name="id_template")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTemplate;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name="id_action")
    private DunningAction dunningAction;

    @Column(name = "template_name")
    private String templateName;

    @Column(name = "language")
    private String language;

    @Enumerated(EnumType.STRING)
    @Column(name = "channel")
    private ActionChannel channel;

    @Column(name ="Text",length = 900)
    private String Text;

    public DunningTemplate(String templateName, String language, ActionChannel channel, String text) {
        this.templateName = templateName;
        this.language = language;
        this.channel = channel;
        this.Text=text;
    }
    public DunningTemplate(String templateName, String language, ActionChannel channel) {
        this.templateName = templateName;
        this.language = language;
        this.channel = channel;
    }
    public DunningTemplate() {

    }
}
