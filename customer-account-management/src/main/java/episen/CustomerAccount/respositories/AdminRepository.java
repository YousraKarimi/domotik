package episen.CustomerAccount.respositories;


import back.models.CustomerAccountModel.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByUserNameAndPassword(String userName, String password);
}
