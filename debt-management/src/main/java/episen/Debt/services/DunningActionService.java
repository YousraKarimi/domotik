package episen.Debt.services;



import back.models.DebtModel.DunningAction;
import episen.Debt.repositories.DunningActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DunningActionService {

    @Autowired
    private DunningActionRepository dunningActionRepository;
    public DunningAction addDunningAction(DunningAction dunningAction) {
        return dunningActionRepository.save(dunningAction);
    }
    public List<DunningAction> findAllActions(){
        return dunningActionRepository.findAll();
    }

}
