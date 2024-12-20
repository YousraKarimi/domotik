package episen.Debt.repositories;



import back.models.DebtModel.ActionChannel;
import back.models.DebtModel.DunningTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DunningTemplateRepository extends JpaRepository<DunningTemplate, Long> {
    @Query("SELECT dt.templateName FROM DunningTemplate dt WHERE dt.idTemplate = :id")
    String findTemplateNameById(@Param("id") Long id);

    @Query("SELECT dt FROM DunningTemplate dt WHERE dt.idTemplate = :id")
    Optional<DunningTemplate> findById(@Param("id") Long id);
    @Query("SELECT id.channel FROM DunningTemplate id WHERE id.idTemplate = :templateId")
    ActionChannel findchannelByDunningTemplateId(@Param("templateId") Long templateId);
    @Query("SELECT id.language FROM DunningTemplate id WHERE id.idTemplate = :templateId")
    String findlanguageByDunningTemplateId(@Param("templateId") Long templateId);
    @Query("SELECT id.templateName FROM DunningTemplate id WHERE id.idTemplate = :templateId")
    String findtemplateNameByDunningTemplateId(@Param("templateId") Long templateId);
    @Query("SELECT id.Text FROM DunningTemplate id WHERE id.idTemplate = :templateId")
    String findTextByDunningTemplateId(@Param("templateId") Long templateId);
    @Modifying
    @Query("UPDATE DunningTemplate dt SET dt.Text = :text WHERE dt.idTemplate = :templateId")
    void updateTextById(@Param("templateId") Long templateId, @Param("text") String text);

    @Query("SELECT da.actionName FROM DunningAction da WHERE da.dunningTemplate.idTemplate = :templateId")
    String findActionNameByTemplateId(@Param("templateId") Long templateId);

    @Query("SELECT da.dunningLevel.levelName FROM DunningAction da WHERE da.dunningTemplate.idTemplate = :templateId")
    String findLevelNameByTemplateId(@Param("templateId") Long templateId);
}
