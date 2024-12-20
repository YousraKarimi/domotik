package episen.CustomerAccount.respositories;

import back.models.CustomerAccountModel.IndividualClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndividualClientRepository extends JpaRepository<IndividualClient, Long> {
}
