package episen.CustomerAccount.services;



import back.models.CustomerAccountModel.Accountant;
import episen.CustomerAccount.respositories.AccountantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountantService {
    @Autowired
    private AccountantRepository accountantRepository;
    public Accountant authenticate(String userName, String password) {
        return accountantRepository.findByUserNameAndPassword(userName, password)
                .orElse(null);
    }
}
