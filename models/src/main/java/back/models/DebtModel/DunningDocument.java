package back.models.DebtModel;

import lombok.Data;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "dunningdocument")
public class DunningDocument {
    @Id
    @Column(name="id_document")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDocument;

    @OneToMany(mappedBy = "dunningDocument",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<DunningLevel> dunningLevels = new ArrayList<>();

    @OneToMany(mappedBy = "dunningDocument",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<DunningAction> dunningActions = new ArrayList<>();

//    @OneToOne
//    @JoinColumn(name="id_invoice")
//    private Invoice invoice;

    @OneToMany(mappedBy = "dunningDocument",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<DunningStatus> dunningStatuses= new ArrayList<>();

    @Column(name = "id_client_account")
    private int idClientAccount;

    @Column(name = "nextaction")
    private String NextAction;

    @Column(name = "next_action_date")
    private Date nextActionDate;

    @Column(name = "action_taken")
    private String actionTaken;

    @Column(name = "date_action_taken")
    private Date DateActionTaken;

    @Column(name = "pause_duration")
    private int pauseDuration;

    @Column(name = "dunning_level")
    private String  dunningLevel;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "penalty")
    private double penalty;

    public DunningDocument(Long idDocument, int idClientAccount, String nextAction, Date nextActionDate, String actionTaken, Date dateActionTaken, int pauseDuration, String dunningLevel, Date endDate, Date startDate, double penalty) {
        this.idDocument = idDocument;
//        this.invoice = invoice;
        this.idClientAccount = idClientAccount;
        this.NextAction = nextAction;
        this.nextActionDate = nextActionDate;
        this.actionTaken = actionTaken;
        this.DateActionTaken = dateActionTaken;
        this.pauseDuration = pauseDuration;
        this.dunningLevel = dunningLevel;
        this.endDate = endDate;
        this.startDate = startDate;
        this.penalty = penalty;
    }

    public DunningDocument() {

    }
}
