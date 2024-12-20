package episen.CustomerAccount.services;



import back.models.CustomerAccountModel.CustomerAccount;
import back.models.CustomerAccountModel.IndividualClient;
import back.models.Exceptions.ResourceNotFoundException;
import episen.CustomerAccount.respositories.CustomerAccountRepository;
import episen.CustomerAccount.respositories.IndividualClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndividualClientService {
    @Autowired
    private IndividualClientRepository individualClientRepository;
    @Autowired
    private CustomerAccountRepository customerAccountRepository;

    public IndividualClient addIndividualClient(IndividualClient individualClient) {
        return individualClientRepository.save(individualClient);
    }

    public void assignCustomerAccountToIndividualClient(Long customerAccountId, Long individualclientId) {
        CustomerAccount customerAccount = customerAccountRepository.findById(customerAccountId)
                .orElseThrow(() -> new ResourceNotFoundException("CustomerAccount not found with id " + customerAccountId));

        IndividualClient individualClient = individualClientRepository.findById(individualclientId)
                .orElseThrow(() -> new ResourceNotFoundException("individualclient not found with id " + individualclientId));

        individualClient.setCustomerAccount(customerAccount);
        individualClientRepository.save(individualClient);
    }

}
