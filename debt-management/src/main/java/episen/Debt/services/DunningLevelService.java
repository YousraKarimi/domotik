package episen.Debt.services;


import back.models.DebtModel.DunningAction;
import back.models.DebtModel.DunningLevel;
import back.models.Exceptions.ResourceNotFoundException;
import episen.Debt.repositories.DunningActionRepository;
import episen.Debt.repositories.DunningLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DunningLevelService {
    @Autowired
    private DunningLevelRepository dunningLevelRepository;
    @Autowired
    private DunningActionRepository dunningActionRepository;


    public DunningLevel addDunningLevel(DunningLevel dunningLevel) {
        return dunningLevelRepository.save(dunningLevel);
    }
    public void assignActionToLevel(Long levelId, Long actionId) {
        DunningLevel dunningLevel = dunningLevelRepository.findById(levelId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        DunningAction dunningAction = dunningActionRepository.findById(actionId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        dunningLevel.setDunningAction(dunningAction);
        dunningLevelRepository.save(dunningLevel);
    }
        public List<DunningLevel> findAllTLevels(){
        return dunningLevelRepository.findAll();
    }
    public String getLevelNameById(Long id) {
        String levelName = dunningLevelRepository.findLevelNameById(id);
        return levelName;
    }
}
