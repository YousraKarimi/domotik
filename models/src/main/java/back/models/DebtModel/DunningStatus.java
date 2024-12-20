package back.models.DebtModel;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
@Table(name = "dunningstatus")
public class DunningStatus {

    @Id
    @Column(name="id_status")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStatus;

    @ManyToOne
    @JoinColumn(name="id_document")
    private DunningDocument dunningDocument;

    @Column(name = "description")
    private String description;

    @Column(name = "reason_status")
    private String reasonStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "dunning_status")
    private DunningState dunningStatus;

    public DunningStatus(DunningState dunningStatus,String description,String reasonStatus) {
        this.description = description;
        this.reasonStatus = reasonStatus;
        this.dunningStatus = dunningStatus;
    }

    public DunningStatus() {

    }
}
