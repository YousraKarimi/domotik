package episen.CustomerAccount.services;



import back.models.CustomerAccountModel.LegalEntity;
import episen.CustomerAccount.respositories.LegalEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LegalEntityService {
    @Autowired
    private LegalEntityRepository legalEntityRepository;

    public LegalEntity addLegalEntity(LegalEntity legalEntity) {
        return legalEntityRepository.save(legalEntity);
    }
}
