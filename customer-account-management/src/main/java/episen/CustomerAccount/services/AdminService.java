package episen.CustomerAccount.services;


import back.models.CustomerAccountModel.Admin;
import episen.CustomerAccount.respositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
    public Admin authenticate(String userName, String password) {
        return adminRepository.findByUserNameAndPassword(userName, password)
                .orElse(null);
    }
}
