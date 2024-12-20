package episen.CustomerAccount.services;



import back.models.CustomerAccountModel.*;
import back.models.Exceptions.ResourceNotFoundException;
import episen.CustomerAccount.respositories.CustomerAccountRepository;
import episen.CustomerAccount.respositories.IndividualClientRepository;
import episen.CustomerAccount.respositories.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerAccountService  {

    @Autowired
    private CustomerAccountRepository customerAccountRepository;
    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private IndividualClientRepository individualClientRepository;

    public CustomerAccount addCustomerAccount(CustomerAccount customerAccount) {
        return customerAccountRepository.save(customerAccount);
    }
    public void assignSubscriptionToCustomerAccount(Long customerAccountId, Long subscriptionId) {
        CustomerAccount customerAccount = customerAccountRepository.findById(customerAccountId)
                .orElseThrow(() -> new ResourceNotFoundException("CustomerAccount not found with id " + customerAccountId));

        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new ResourceNotFoundException("Subscription not found with id " + subscriptionId));

        customerAccount.setSubscription(subscription);
        customerAccountRepository.save(customerAccount);
    }
    public void assignIndividualClientToCustomerAccount(Long customerAccountId, Long IdividualClient) {
        CustomerAccount customerAccount = customerAccountRepository.findById(customerAccountId)
                .orElseThrow(() -> new ResourceNotFoundException("CustomerAccount not found with id " + customerAccountId));

        IndividualClient individualClient = individualClientRepository.findById(IdividualClient)
                .orElseThrow(() -> new ResourceNotFoundException("individualClient not found with id " + IdividualClient));

        customerAccount.setIndividualClient(individualClient);
        customerAccountRepository.save(customerAccount);
    }

    public CustomerAccount authenticate(String userName, String password) {
        return customerAccountRepository.findByUserNameAndPassword(userName, password)
                .orElse(null);
    }
}
