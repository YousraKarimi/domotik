package episen.Debt.repositories;


import back.models.DebtModel.DunningAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DunningActionRepository extends JpaRepository<DunningAction, Long> {
}
