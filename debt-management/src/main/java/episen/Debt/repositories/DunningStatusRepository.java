package episen.Debt.repositories;

import back.models.DebtModel.DunningStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DunningStatusRepository extends JpaRepository<DunningStatus, Long> {
}
