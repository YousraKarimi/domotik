    package episen.back.Config.models;

    import com.fasterxml.jackson.annotation.JsonIgnore;
    import jakarta.persistence.*;
    import lombok.Data;

    @Entity
    @Table(name = "device")
    @Data
    public class Device {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "device_name")
        private String name;

        @Column(name = "device_type")
        private String type;

        @Column(name = "device_status")
        private String status;

        @OneToOne
        @JsonIgnore
        @JoinColumn(name = "configuration_id")
        private Configuration configuration;

        @ManyToOne
        @JsonIgnore
        @JoinColumn(name = "user_id")
        private User user;

        public Device(String name, String type, String status) {
            this.name = name;
            this.type = type;
            this.status = status;
        }

        public Device() {

        }
    }
