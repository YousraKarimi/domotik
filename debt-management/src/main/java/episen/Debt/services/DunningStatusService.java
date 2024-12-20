package episen.Debt.services;



import back.models.DebtModel.DunningStatus;
import episen.Debt.repositories.DunningStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DunningStatusService {

    @Autowired
    private DunningStatusRepository dunningStatusRepository;

    public DunningStatus addDunningStatus(DunningStatus dunningStatus) {
        return dunningStatusRepository.save(dunningStatus);
    }

}
