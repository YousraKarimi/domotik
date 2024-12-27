package episen.back.Config.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Configuration")
@Data
public class Configuration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "config_name")
    private String configurationName;

    @OneToOne(mappedBy = "configuration")
    private Device device;

    @Column(name = "energy_saving")
    private Boolean energySaving;

    @Column(name = "mode")
    private String mode;

    @Column(name = "schedule")
    private String schedule;


    public Configuration(String configurationName, Boolean energySaving, String mode, String schedule) {
        this.configurationName = configurationName;
        this.energySaving = energySaving;
        this.mode = mode;
        this.schedule = schedule;
    }

    public Configuration() {

    }
}
