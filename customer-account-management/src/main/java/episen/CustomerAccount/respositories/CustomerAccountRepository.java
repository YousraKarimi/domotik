package episen.CustomerAccount.respositories;

import back.models.CustomerAccountModel.CustomerAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerAccountRepository extends JpaRepository<CustomerAccount, Long> {
    Optional<CustomerAccount> findByUserNameAndPassword(String userName, String password);

}
