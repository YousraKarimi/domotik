package episen.Debt;

import back.models.DebtModel.*;
import episen.Debt.services.DunningActionService;
import episen.Debt.services.DunningLevelService;
import episen.Debt.services.DunningStatusService;
import episen.Debt.services.DunningTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = {"back.models"})
@SpringBootApplication
public class DebtManagementApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DebtManagementApplication.class, args);
	}
	private static final Logger logger = LoggerFactory.getLogger(DebtManagementApplication.class);

	@Autowired
	private DunningTemplateService dunningTemplateService;

	@Autowired
	private DunningLevelService dunningLevelService;

	@Autowired
	private DunningActionService dunningActionService;

	@Autowired
	private DunningStatusService dunningStatusService;

	@Override
	public void run(String... args) {
		logger.info("Starting application...");
		generateLevelsTemplatesActions();
		generateStatus();
		logger.info("Application finished processing.");
	}
	private void generateLevelsTemplatesActions(){
		DunningTemplate template1FRB2C = new DunningTemplate("FR-B2C relance 1","FRANCAIS",ActionChannel.EMAIL,"Madame, Monsieur,\n" +
				"\n" +
				"Nous vous rappelons que vous avez un solde impayé sur votre compte. Nous vous prions de bien vouloir régulariser votre situation dans les plus brefs délais.\n" +
				"\n" +
				"En vous remerciant de votre compréhension.\n" +
				"\n" +
				"Cordialement,");
		dunningTemplateService.addDunningTemplate(template1FRB2C);
		DunningTemplate template2FRB2C = new DunningTemplate("FR-B2C relance 2","FRANCAIS",ActionChannel.EMAIL,"Madame, Monsieur,\n" +
				"\n" +
				"Malgré notre premier rappel, nous constatons que votre compte reste impayé. Nous vous prions de bien vouloir procéder au paiement dans un délai de 7 jours afin d'éviter toute interruption de service.\n" +
				"\n" +
				"Merci de votre promptitude.\n" +
				"\n" +
				"Cordialement,");
		dunningTemplateService.addDunningTemplate(template2FRB2C);
		DunningTemplate template3FRB2C = new DunningTemplate("FR-B2C relance 3","FRANCAIS",ActionChannel.CALL);
		dunningTemplateService.addDunningTemplate(template3FRB2C);
		DunningTemplate template4FRB2C = new DunningTemplate("FR-B2C relance 4","FRANCAIS",ActionChannel.LETTER,"Madame, Monsieur,\n" +
				"\n" +
				"Nous regrettons de constater que votre compte reste impayé malgré nos rappels précédents. Sans règlement de votre part sous 5 jours, nous serons contraints de procéder à la résiliation de votre ligne.\n" +
				"\n" +
				"Nous vous invitons à régulariser votre situation au plus vite pour éviter cette mesure.\n" +
				"\n" +
				"Cordialement,");
		dunningTemplateService.addDunningTemplate(template4FRB2C);
		DunningTemplate template1ANGB2C = new DunningTemplate("ANG-B2C relance 1","ANGLAIS",ActionChannel.EMAIL,"Dear Valued Customer,\n" +
				"\n" +
				"We remind you that there is an outstanding balance on your account. Please kindly settle the amount at your earliest convenience.\n" +
				"\n" +
				"Thank you for your understanding.\n" +
				"\n" +
				"Sincerely,");
		dunningTemplateService.addDunningTemplate(template1ANGB2C);
		DunningTemplate template2ANGB2C = new DunningTemplate("ANG-B2C relance 2","ANGLAIS", ActionChannel.EMAIL,"Dear Valued Customer,\n" +
				"\n" +
				"Despite our first reminder, we notice that your account remains unpaid. Please make the payment within 7 days to avoid any service interruption.\n" +
				"\n" +
				"Thank you for your prompt attention.\n" +
				"\n" +
				"Sincerely,");
		dunningTemplateService.addDunningTemplate(template2ANGB2C);
		DunningTemplate template3ANGB2C = new DunningTemplate("ANG-B2C relance 3","ANGLAIS",ActionChannel.CALL);
		dunningTemplateService.addDunningTemplate(template3ANGB2C);
		DunningTemplate template4ANGB2C = new DunningTemplate("ANG-B2C relance 4","ANGLAIS",ActionChannel.LETTER,"Dear Valued Customer,\n" +
				"\n" +
				"We regret to inform you that your account remains unpaid despite our previous reminders. If payment is not received within 5 days, we will be forced to terminate your line.\n" +
				"\n" +
				"Please settle your account promptly to avoid this action.\n" +
				"\n" +
				"Sincerely,");
		dunningTemplateService.addDunningTemplate(template4ANGB2C);
		DunningTemplate template1FRB2B = new DunningTemplate("FR-B2B relance 1","FRANCAIS",ActionChannel.EMAIL,"Madame, Monsieur,\n" +
				"\n" +
				"Nous vous rappelons que vous avez un solde impayé sur votre compte. Nous vous prions de bien vouloir régulariser votre situation dans les plus brefs délais.\n" +
				"\n" +
				"En vous remerciant de votre compréhension.\n" +
				"\n" +
				"Cordialement,");
		dunningTemplateService.addDunningTemplate(template1FRB2B);
		DunningTemplate template2FRB2B = new DunningTemplate("FR-B2B relance 2","FRANCAIS",ActionChannel.EMAIL,"Madame, Monsieur,\n" +
				"\n" +
				"Malgré notre premier rappel, nous constatons que votre compte reste impayé. Nous vous prions de bien vouloir procéder au paiement dans un délai de 7 jours afin d'éviter toute interruption de service.\n" +
				"\n" +
				"Merci de votre promptitude.\n" +
				"\n" +
				"Cordialement,");
		dunningTemplateService.addDunningTemplate(template2FRB2B);
		DunningTemplate template3FRB2B = new DunningTemplate("FR-B2B relance 3","FRANCAIS",ActionChannel.CALL);
		dunningTemplateService.addDunningTemplate(template3FRB2B);
		DunningTemplate template4FRB2B = new DunningTemplate("FR-B2B relance 4","FRANCAIS",ActionChannel.LETTER,"Madame, Monsieur,\n" +
				"\n" +
				"Nous regrettons de constater que votre compte reste impayé malgré nos rappels précédents. Sans règlement de votre part sous 5 jours, nous serons contraints de procéder à la résiliation de notre contrat.\n" +
				"\n" +
				"Nous vous invitons à régulariser votre situation au plus vite pour éviter cette mesure.\n" +
				"\n" +
				"Cordialement,");
		dunningTemplateService.addDunningTemplate(template4FRB2B);
		DunningTemplate template1ANGB2B = new DunningTemplate("ANG-B2B relance 1","ANGLAIS",ActionChannel.EMAIL,"Dear Valued Client,\n" +
				"\n" +
				"We remind you that there is an outstanding balance on your account. Please kindly settle the amount at your earliest convenience.\n" +
				"\n" +
				"Thank you for your understanding.\n" +
				"\n" +
				"Sincerely,");
		dunningTemplateService.addDunningTemplate(template1ANGB2B);
		DunningTemplate template2ANGB2B = new DunningTemplate("ANG-B2B relance 2","ANGLAIS",ActionChannel.EMAIL,"Dear Valued Client,\n" +
				"\n" +
				"Despite our first reminder, we notice that your account remains unpaid. Please make the payment within 7 days to avoid any service interruption.\n" +
				"\n" +
				"Thank you for your prompt attention.\n" +
				"\n" +
				"Sincerely,");
		dunningTemplateService.addDunningTemplate(template2ANGB2B);
		DunningTemplate template3ANGB2B = new DunningTemplate("ANG-B2B relance 3","ANGLAIS",ActionChannel.CALL);
		dunningTemplateService.addDunningTemplate(template3ANGB2B);
		DunningTemplate template4ANGB2B = new DunningTemplate("ANG-B2B relance 4","ANGLAIS",ActionChannel.LETTER,"Dear Valued Client,\n" +
				"\n" +
				"We regret to inform you that your account remains unpaid despite our previous reminders. If payment is not received within 5 days, we will be forced to terminate our contract.\n" +
				"\n" +
				"Please settle your account promptly to avoid this action.\n" +
				"\n" +
				"Sincerely,");
		dunningTemplateService.addDunningTemplate(template4ANGB2B);
		DunningLevel level1FRB2C = new DunningLevel("LVL1-B2C-FR",false,5);
		dunningLevelService.addDunningLevel(level1FRB2C);
		DunningLevel level2FRB2C = new DunningLevel("LVL2-B2C-FR",false,15);
		dunningLevelService.addDunningLevel(level2FRB2C);
		DunningLevel level3FRB2C = new DunningLevel("LVL3-B2C-FR",false,25);
		dunningLevelService.addDunningLevel(level3FRB2C);
		DunningLevel level4FRB2C = new DunningLevel("LVL4-B2C-FR",false,35);
		dunningLevelService.addDunningLevel(level4FRB2C);
		DunningLevel level1ANGB2C = new DunningLevel("LVL1-B2C-ANG",false,5);
		dunningLevelService.addDunningLevel(level1ANGB2C);
		DunningLevel level2ANGB2C = new DunningLevel("LVL2-B2C-ANG",false,15);
		dunningLevelService.addDunningLevel(level2ANGB2C);
		DunningLevel level3ANGB2C = new DunningLevel("LVL3-B2C-ANG",false,25);
		dunningLevelService.addDunningLevel(level3ANGB2C);
		DunningLevel level4ANGB2C = new DunningLevel("LVL4-B2C-ANG",false,35);
		dunningLevelService.addDunningLevel(level4ANGB2C);
		DunningLevel level1FRB2B = new DunningLevel("LVL1-B2B-FR",false,5);
		dunningLevelService.addDunningLevel(level1FRB2B);
		DunningLevel level2FRB2B = new DunningLevel("LVL2-B2B-FR",true,15);
		dunningLevelService.addDunningLevel(level2FRB2B);
		DunningLevel level3FRB2B = new DunningLevel("LVL3-B2B-FR",false,25);
		dunningLevelService.addDunningLevel(level3FRB2B);
		DunningLevel level4FRB2B = new DunningLevel("LVL4-B2B-FR",false,35);
		dunningLevelService.addDunningLevel(level4FRB2B);
		DunningLevel level1ANGB2B = new DunningLevel("LVL1-B2B-ANG",false,5);
		dunningLevelService.addDunningLevel(level1ANGB2B);
		DunningLevel level2ANGB2B = new DunningLevel("LVL2-B2B-ANG",false,15);
		dunningLevelService.addDunningLevel(level2ANGB2B);
		DunningLevel level3ANGB2B = new DunningLevel("LVL3-B2B-ANG",false,25);
		dunningLevelService.addDunningLevel(level3ANGB2B);
		DunningLevel level4ANGB2B = new DunningLevel("LVL4-B2B-ANG",false,35);
		dunningLevelService.addDunningLevel(level4ANGB2B);
		DunningAction ActionLVL1FRB2C = new DunningAction(template1FRB2C,level1FRB2C,"Email B2C LVL1-FR",ActionChannel.EMAIL);
		dunningActionService.addDunningAction(ActionLVL1FRB2C);
		DunningAction ActionLVL2FRB2C = new DunningAction(template2FRB2C,level2FRB2C,"Email B2C LVL2-FR",ActionChannel.EMAIL);
		dunningActionService.addDunningAction(ActionLVL2FRB2C);
		DunningAction ActionLVL3FRB2C = new DunningAction(template3FRB2C,level3FRB2C,"Call B2C LVL3-FR",ActionChannel.CALL);
		dunningActionService.addDunningAction(ActionLVL3FRB2C);
		DunningAction ActionLVL4FRB2C = new DunningAction(template4FRB2C,level4FRB2C,"Letter B2C LVL4-FR",ActionChannel.LETTER);
		dunningActionService.addDunningAction(ActionLVL4FRB2C);
		DunningAction ActionLVL1ANGB2C = new DunningAction(template1ANGB2C,level1ANGB2C,"Email B2C LVL1-ANG",ActionChannel.EMAIL);
		dunningActionService.addDunningAction(ActionLVL1ANGB2C);
		DunningAction ActionLVL2ANGB2C = new DunningAction(template2ANGB2C,level2ANGB2C,"Email B2C LVL2-ANG",ActionChannel.EMAIL);
		dunningActionService.addDunningAction(ActionLVL2ANGB2C);
		DunningAction ActionLVL3ANGB2C = new DunningAction(template3ANGB2C,level3ANGB2C,"Call B2C LVL3-ANG",ActionChannel.CALL);
		dunningActionService.addDunningAction(ActionLVL3ANGB2C);
		DunningAction ActionLVL4ANGB2C = new DunningAction(template4ANGB2C,level4ANGB2C,"Letter B2C LVL4-ANG",ActionChannel.LETTER);
		dunningActionService.addDunningAction(ActionLVL4ANGB2C);
		DunningAction ActionLVL1FRB2B = new DunningAction(template1FRB2B,level1FRB2B,"Email B2B LVL1-FR",ActionChannel.EMAIL);
		dunningActionService.addDunningAction(ActionLVL1FRB2B);
		DunningAction ActionLVL2FRB2B = new DunningAction(template2FRB2B,level2FRB2B,"Email B2B LVL2-FR",ActionChannel.EMAIL);
		dunningActionService.addDunningAction(ActionLVL2FRB2B);
		DunningAction ActionLVL3FRB2B = new DunningAction(template3FRB2B,level3FRB2B,"Call B2B LVL3-FR",ActionChannel.CALL);
		dunningActionService.addDunningAction(ActionLVL3FRB2B);
		DunningAction ActionLVL4FRB2B = new DunningAction(template4FRB2B,level4FRB2B,"Letter B2B LVL4-FR",ActionChannel.LETTER);
		dunningActionService.addDunningAction(ActionLVL4FRB2B);
		DunningAction ActionLVL1ANGB2B = new DunningAction(template1ANGB2B,level1ANGB2B,"Email B2B LVL1-ANG",ActionChannel.EMAIL);
		dunningActionService.addDunningAction(ActionLVL1ANGB2B);
		DunningAction ActionLVL2ANGB2B = new DunningAction(template2ANGB2B,level2ANGB2B,"Email B2B LVL2-ANG",ActionChannel.EMAIL);
		dunningActionService.addDunningAction(ActionLVL2ANGB2B);
		DunningAction ActionLVL3ANGB2B = new DunningAction(template3ANGB2B,level3ANGB2B,"Call B2B LVL3-ANG",ActionChannel.CALL);
		dunningActionService.addDunningAction(ActionLVL3ANGB2B);
		DunningAction ActionLVL4ANGB2B = new DunningAction(template4ANGB2B,level4ANGB2B,"Letter B2B LVL4-ANG",ActionChannel.LETTER);
		dunningActionService.addDunningAction(ActionLVL4ANGB2B);
		dunningLevelService.assignActionToLevel(level1FRB2C.getIdLevel(),ActionLVL1FRB2C.getIdAction());
		dunningLevelService.assignActionToLevel(level2FRB2C.getIdLevel(),ActionLVL2FRB2C.getIdAction());
		dunningLevelService.assignActionToLevel(level3FRB2C.getIdLevel(),ActionLVL3FRB2C.getIdAction());
		dunningLevelService.assignActionToLevel(level4FRB2C.getIdLevel(),ActionLVL4FRB2C.getIdAction());
		dunningLevelService.assignActionToLevel(level1ANGB2C.getIdLevel(),ActionLVL1ANGB2C.getIdAction());
		dunningLevelService.assignActionToLevel(level2ANGB2C.getIdLevel(),ActionLVL2ANGB2C.getIdAction());
		dunningLevelService.assignActionToLevel(level3ANGB2C.getIdLevel(),ActionLVL3ANGB2C.getIdAction());
		dunningLevelService.assignActionToLevel(level4ANGB2C.getIdLevel(),ActionLVL4ANGB2C.getIdAction());
		dunningLevelService.assignActionToLevel(level1FRB2B.getIdLevel(),ActionLVL1FRB2B.getIdAction());
		dunningLevelService.assignActionToLevel(level2FRB2B.getIdLevel(),ActionLVL2FRB2B.getIdAction());
		dunningLevelService.assignActionToLevel(level3FRB2B.getIdLevel(),ActionLVL3FRB2B.getIdAction());
		dunningLevelService.assignActionToLevel(level4FRB2B.getIdLevel(),ActionLVL4FRB2B.getIdAction());
		dunningLevelService.assignActionToLevel(level1ANGB2B.getIdLevel(),ActionLVL1ANGB2B.getIdAction());
		dunningLevelService.assignActionToLevel(level2ANGB2B.getIdLevel(),ActionLVL2ANGB2B.getIdAction());
		dunningLevelService.assignActionToLevel(level3ANGB2B.getIdLevel(),ActionLVL3ANGB2B.getIdAction());
		dunningLevelService.assignActionToLevel(level4ANGB2B.getIdLevel(),ActionLVL4ANGB2B.getIdAction());

		dunningTemplateService.assignActionToTemplate(template1FRB2C.getIdTemplate(), ActionLVL1FRB2C.getIdAction());
		dunningTemplateService.assignActionToTemplate(template2FRB2C.getIdTemplate(), ActionLVL2FRB2C.getIdAction());
		dunningTemplateService.assignActionToTemplate(template3FRB2C.getIdTemplate(), ActionLVL3FRB2C.getIdAction());
		dunningTemplateService.assignActionToTemplate(template4FRB2C.getIdTemplate(), ActionLVL4FRB2C.getIdAction());
		dunningTemplateService.assignActionToTemplate(template1FRB2B.getIdTemplate(), ActionLVL1FRB2B.getIdAction());
		dunningTemplateService.assignActionToTemplate(template2FRB2B.getIdTemplate(), ActionLVL2FRB2B.getIdAction());
		dunningTemplateService.assignActionToTemplate(template3FRB2B.getIdTemplate(), ActionLVL3FRB2B.getIdAction());
		dunningTemplateService.assignActionToTemplate(template4FRB2B.getIdTemplate(), ActionLVL4FRB2B.getIdAction());
		dunningTemplateService.assignActionToTemplate(template1ANGB2C.getIdTemplate(), ActionLVL1ANGB2C.getIdAction());
		dunningTemplateService.assignActionToTemplate(template2ANGB2C.getIdTemplate(), ActionLVL2ANGB2C.getIdAction());
		dunningTemplateService.assignActionToTemplate(template3ANGB2C.getIdTemplate(), ActionLVL3ANGB2C.getIdAction());
		dunningTemplateService.assignActionToTemplate(template4ANGB2C.getIdTemplate(), ActionLVL4ANGB2C.getIdAction());
		dunningTemplateService.assignActionToTemplate(template1ANGB2B.getIdTemplate(), ActionLVL1ANGB2B.getIdAction());
		dunningTemplateService.assignActionToTemplate(template2ANGB2B.getIdTemplate(), ActionLVL2ANGB2B.getIdAction());
		dunningTemplateService.assignActionToTemplate(template3ANGB2B.getIdTemplate(), ActionLVL3ANGB2B.getIdAction());
		dunningTemplateService.assignActionToTemplate(template4ANGB2B.getIdTemplate(), ActionLVL4ANGB2B.getIdAction());

	}
	private void generateStatus(){
		DunningStatus status1= new DunningStatus(DunningState.ACTIVE,"Active dunning","XXXX");
		dunningStatusService.addDunningStatus(status1);
		DunningStatus status2= new DunningStatus(DunningState.FAILED,"Failed dunning","XXXX");
		dunningStatusService.addDunningStatus(status2);
		DunningStatus status3= new DunningStatus(DunningState.SUCCESS,"Successful dunning","XXXX");
		dunningStatusService.addDunningStatus(status3);
		DunningStatus status4= new DunningStatus(DunningState.STOPPED,"Stopped dunning","XXXX");
		dunningStatusService.addDunningStatus(status4);
	}
}
