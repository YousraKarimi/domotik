package episen.Debt.repositories;

import back.models.DebtModel.DunningDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DunningDocumentRepository extends JpaRepository<DunningDocument, Long> {
}
