package episen.Debt.services;




import back.models.DebtModel.DunningDocument;
import episen.Debt.repositories.DunningDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DunningDocumentService {

    @Autowired
    private DunningDocumentRepository dunningDocumentRepository;

    public DunningDocument addDunningDocument(DunningDocument dunningDocument) {
        return dunningDocumentRepository.save(dunningDocument);
    }
}
