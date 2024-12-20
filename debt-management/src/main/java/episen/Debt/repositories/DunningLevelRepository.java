package episen.Debt.repositories;

import back.models.DebtModel.DunningLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DunningLevelRepository extends JpaRepository<DunningLevel, Long> {
    @Query("SELECT dl.levelName FROM DunningLevel dl WHERE dl.idLevel = :id")
    String findLevelNameById(@Param("id") Long id);

}
