package episen.back.Config.repositories;

import episen.back.Config.models.Configuration;
import episen.back.Config.models.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {
    Optional<Configuration> findByDevice(Device device);


}
