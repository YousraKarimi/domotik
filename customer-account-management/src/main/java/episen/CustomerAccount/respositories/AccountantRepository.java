package episen.CustomerAccount.respositories;


import back.models.CustomerAccountModel.Accountant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountantRepository extends JpaRepository<Accountant, Long> {
    Optional<Accountant> findByUserNameAndPassword(String userName, String password);

}
