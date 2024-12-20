package episen.Debt.services;


import back.models.DebtModel.ActionChannel;
import back.models.DebtModel.DunningAction;
import back.models.DebtModel.DunningTemplate;
import back.models.Exceptions.ResourceNotFoundException;
import episen.Debt.repositories.DunningActionRepository;
import episen.Debt.repositories.DunningTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DunningTemplateService {

    @Autowired
    private DunningTemplateRepository dunningTemplateRepository;
    @Autowired
    private DunningActionRepository dunningActionRepository;

    public DunningTemplate addDunningTemplate(DunningTemplate dunningTemplate) {
        return dunningTemplateRepository.save(dunningTemplate);
    }

    public List<DunningTemplate> findAllTemplates(){
        return dunningTemplateRepository.findAll();
    }

    public String getTemplateNameById(Long id) {
        String TemplateName = dunningTemplateRepository.findTemplateNameById(id);
        return TemplateName;
    }

    public void assignActionToTemplate(Long templateId, Long actionId) {
        DunningTemplate dunningTemplate = dunningTemplateRepository.findById(templateId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        DunningAction dunningAction = dunningActionRepository.findById(actionId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        dunningTemplate.setDunningAction(dunningAction);
        dunningTemplateRepository.save(dunningTemplate);
    }

    public DunningTemplate updateDunningTemplate(DunningTemplate dunningTemplate) {
        return dunningTemplateRepository.save(dunningTemplate);
    }
    public Optional<DunningTemplate> getTemplateById(Long id) {
        return dunningTemplateRepository.findById(id);
    }
    public ActionChannel getchannelByTemplateId(Long templateId) {
        return dunningTemplateRepository.findchannelByDunningTemplateId(templateId);
    }
    public String getlanguageByTemplateId(Long templateId) {
        return dunningTemplateRepository.findlanguageByDunningTemplateId(templateId);
    }
    public String getTextByTemplateId(Long templateId) {
        return dunningTemplateRepository.findTextByDunningTemplateId(templateId);
    }
    public String gettemplateNameByTemplateId(Long templateId) {
        return dunningTemplateRepository.findtemplateNameByDunningTemplateId(templateId);
    }
    @Transactional
    public void updateTemplateText(Long templateId, String text) {
        dunningTemplateRepository.updateTextById(templateId, text);
    }
    public String getActionNameByTemplateId(Long templateId) {
        return dunningTemplateRepository.findActionNameByTemplateId(templateId);
    }

    public String getLevelNameByTemplateId(Long templateId) {
        return dunningTemplateRepository.findLevelNameByTemplateId(templateId);
    }
}
