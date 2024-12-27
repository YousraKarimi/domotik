package episen.back.Config.repositories;


import episen.back.Config.models.Configuration;
import episen.back.Config.models.Device;
import episen.back.Config.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

    List<Device> findByUser(User user);


    @Query("SELECT d.configuration FROM Device d WHERE d.user = :user AND d.configuration IS NOT NULL")
    List<Configuration> findConfigurationsByUser(User user);



}
