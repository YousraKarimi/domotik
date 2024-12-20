package episen.CustomerAccount;


import back.models.CustomerAccountModel.*;
import episen.CustomerAccount.respositories.*;
import episen.CustomerAccount.services.DebtCalculationService;
import org.slf4j.Logger;
import com.github.javafaker.Faker;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableScheduling
@EntityScan(basePackages = {"back.models"})
public class CustomerAccountManagementApplication implements CommandLineRunner {


	private static final Logger logger = LoggerFactory.getLogger(CustomerAccountManagementApplication.class);

	@Autowired
	private CustomerAccountRepository customerAccountRepo;

	@Autowired
	private IndividualClientRepository individualClientRepo;

	@Autowired
	private SubscriptionRepository subscriptionRepo;

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private AdminRepository adminRepo;

	@Autowired
	private AccountantRepository accountantRepo;
	@Autowired
	private DebtCalculationService debtCalculationService;


	private final Faker faker = new Faker();
	private final Faker frenchFaker = new Faker(new Locale("fr"));

	public static void main(String[] args) {
		SpringApplication.run(CustomerAccountManagementApplication.class, args);
	}
	@Override
	public void run(String... args) {
		logger.info("Starting application...");
		generateClientsAndAccounts();
		generateSubscriptionsAndInvoices();
		initializeAdminAndAccountant();
		logger.info("Application finished processing.");
	}


	private IndividualClient generateIndividualClient() {
		String firstName = frenchFaker.name().firstName();
		String lastName = frenchFaker.name().lastName();
		String gender = faker.options().option("Male", "Female");
		Calendar minDateOfBirth = Calendar.getInstance();
		minDateOfBirth.set(Calendar.YEAR, 1970);
		Calendar maxDateOfBirth = Calendar.getInstance();
		maxDateOfBirth.set(Calendar.YEAR, 2014);
		Date dateOfBirth = faker.date().between(minDateOfBirth.getTime(), maxDateOfBirth.getTime());
		String customerAddress = frenchFaker.address().fullAddress();
		String customerPhoneNumber = frenchFaker.phoneNumber().phoneNumber();
		String customerEmail = frenchFaker.internet().emailAddress();

		return new IndividualClient(firstName, lastName, gender, dateOfBirth, customerAddress, customerPhoneNumber, customerEmail);
	}

	private CustomerAccount generateCustomerAccount(IndividualClient individualClient) {
		String username = faker.name().username();
		String password = faker.internet().password();
		Date accountCreationDate = new Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(365 * 5));
		AccountStatus accountStatus = AccountStatus.values()[faker.random().nextInt(AccountStatus.values().length)];

		return new CustomerAccount(username, password, individualClient, accountCreationDate, accountStatus);
	}
	private void generateClientsAndAccounts() {
		logger.info("Generating clients and accounts...");
		int numberOfClients = 5;
		for (int i = 0; i < numberOfClients; i++) {
			IndividualClient individualClient = generateIndividualClient();
			individualClientRepo.save(individualClient);

			CustomerAccount customerAccount = generateCustomerAccount(individualClient);
			customerAccountRepo.save(customerAccount);
		}
		logger.info("Finished generating clients and accounts.");

	}
	private Subscription generateSubscription(CustomerAccount customerAccount) {
		String codeSubscription = faker.numerify("SUB#####");
		Date accountCreationDate = customerAccount.getAccountCreationDate();
		Date startDate = new Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(365 * 5));
		if (startDate.after(accountCreationDate)) {
			startDate = accountCreationDate;
		}
		Date endDate = null;
		SubscrptionState subscriptionState = SubscrptionState.values()[faker.random().nextInt(SubscrptionState.values().length)];
		if (subscriptionState == SubscrptionState.CANCELLED) {
			endDate = faker.date().between(startDate, new Date());
		}
		double subscriptionCost = 15.0;
		SubscriptionType subscriptionType = SubscriptionType.MONTHLY;
		return new Subscription(codeSubscription, customerAccount, startDate, endDate, subscriptionCost, subscriptionType, subscriptionState);
	}
	private void generateSubscriptionsAndInvoices() {
		logger.info("Generating subscriptions and invoices...");
		List<CustomerAccount> customerAccounts = customerAccountRepo.findAll();
		for (CustomerAccount customerAccount : customerAccounts) {
			Subscription subscription = generateSubscription(customerAccount);
			subscriptionRepo.save(subscription);
			if (subscription.getSubscriptionState().equals(SubscrptionState.CANCELLED)) {
				customerAccount.setAccountStatus(AccountStatus.HANGED);
				customerAccountRepo.save(customerAccount);
			}
			generateInvoicesForSubscription(subscription);
		}
		logger.info("Finished generating subscriptions and invoices.");

	}

	private InvoiceDetails generateInvoiceDetails(Invoice invoice, Date invoiceDate) {
		String idInvoice = invoice.getInvoiceNumber();
		double totalAmount = 15.0;
		double amountPaid;
		if (invoice.getInvoiceStatus() == InvoiceStatus.RESOLVED) {
			amountPaid = 15.0;
		} else {
			amountPaid=0;
		}

		Date dueDate = invoiceDate;
		Date deadlinePaiement = new Date(dueDate.getTime() + TimeUnit.DAYS.toMillis(7));
		initializeTotalUnpaidForAllInvoices();
		return new InvoiceDetails(idInvoice, invoice, 1, amountPaid, totalAmount, dueDate, deadlinePaiement);
	}

	private Invoice generateInvoice(Subscription subscription, Date invoiceDate) {
		String subscriptionCode = subscription.getCodeSubscription();
		String invoiceNumber = faker.numerify("INV######");
		InvoiceStatus invoiceStatus = getRandomInvoiceStatus();
		return new Invoice(subscription, subscriptionCode, invoiceNumber,0 , invoiceStatus);
	}

	private void generateInvoicesForSubscription(Subscription subscription) {
		Date currentDate = new Date();
		Date startDate = subscription.getStartDate();
		Date endDate = subscription.getEndDate() != null ? subscription.getEndDate() : currentDate;
		if (subscription.getSubscriptionState() == SubscrptionState.CANCELLED) { // optimiser ce code après, end date definie deux fois
			endDate = subscription.getEndDate();
			if (endDate == null) {
				endDate = new Date();
			}
		}
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(startDate);
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(endDate);

		while (startCalendar.before(endCalendar)) {
			startCalendar.add(Calendar.MONTH, 1);
			Date invoiceDate = startCalendar.getTime();
			if (invoiceDate.after(endDate)) {
				break;
			}

			Invoice invoice = generateInvoice(subscription, invoiceDate);
			InvoiceDetails details = generateInvoiceDetails(invoice, invoiceDate);
			invoice.setInvoiceDetails(details);

			subscription.setInvoices(List.of(invoice));
			subscriptionRepo.save(subscription);


		}
	}

	private void initializeAdminAndAccountant() {
		logger.info("Initializing Admin and Accountant...");
		Admin admin = new Admin("admin", "admin");
		adminRepo.save(admin);

		Accountant accountant = new Accountant("accountant", "accountant");
		accountantRepo.save(accountant);
		logger.info("Admin and Accountant initialized.");
	}
	private InvoiceStatus getRandomInvoiceStatus() {
		double probability = faker.random().nextDouble();
		if (probability <= 0.8) {
			return InvoiceStatus.RESOLVED;
		}
		else {
			return InvoiceStatus.UNRESOLVED;
		}
	}
	public void initializeTotalUnpaidForAllInvoices() {
		List<Invoice> invoices = invoiceRepository.findAll();
		for (Invoice invoice : invoices) {
			double totalDebt = debtCalculationService.calculateTotalDebtForInvoice(invoice.getIdInvoice());
			invoice.setTotalUnpaid(totalDebt);
			invoiceRepository.save(invoice);
		}
	}

}
