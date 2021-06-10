package com.openclassroom.paymybuddy;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class PaymybuddyApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(PaymybuddyApplication.class, args);

//		// test de la connection JPA
//		IUserDAO userDAO = context.getBean(IUserDAO.class);
//		System.out.println("Liste des Users : " + userDAO.findAll());
//
//		// test liaison User + BankAccount
//		List<User> userList = userDAO.findAll();
//		System.out.println("Iban numéro 1 " + userList.get(0).getBankAccountList().get(0).getIban());
//
//		// test BankAccount
//		IBankAccountDAO bankAccountDAO = context.getBean(IBankAccountDAO.class);
//		System.out.println("Liste des comptes bancaires : " + bankAccountDAO.findAll());
//
//		// test ExternalTransfer
//		IExternalTransferDAO externalTransferDAO = context.getBean(IExternalTransferDAO.class);
//		System.out.println("Description du premier ExternalTransfer = " + externalTransferDAO.findAll().get(0).getDescription());
//
//		// test InternalTransfer
//		IInternalTransferDAO internalTransferDAO = context.getBean(IInternalTransferDAO.class);
//		System.out.println("Description du premier InternalTransfer = " + internalTransferDAO.findAll().get(0).getDescription());

//		// test Relation
//		System.out.println("Relation des utilsateurs = " + userDAO.findAll().get(0).getRelationList().get(0));


		// test addUSer - fonctionne ok
//		IUserService userService = context.getBean(IUserService.class);
//		UserInfoCreate userInfoCreate = new UserInfoCreate();
//		userInfoCreate.setFirstName("");
//		userInfoCreate.setLastName("Bertrand");
//		userInfoCreate.setEmail("banatole@mail.com");
//		userInfoCreate.setPassword("1234");

//
//		System.out.println("Ajout d'un utilisateur" + userService.addUser(userInfoCreate));

		// test deleteUser - fonctionne ok, erreur si email is null
//		IUserService userService = context.getBean(IUserService.class);
//
//		userDAO.delete(userDAO.findByEmail("banatole@mail.com"));

		// méthode pour ajouter une relation - fonctionne OK
//		User owner = userDAO.findByEmail("mail@gmail.com");
//		User buddy = userDAO.findByEmail("nonpresident@gmail.com");
//		userService.addRelation(owner, buddy);

		// méthode pour supprimer une relation en utilisant findByBuddy de la DAO - fonctionne OK
//		User owner = userDAO.findByEmail("mail@gmail.com");
//		User buddy = userDAO.findByEmail("nonpresident@gmail.com");
//		userService.deleteRelation(owner, buddy);

		// méthode pour ajouter un transfer - fonctionne OK
//		IPayMyBuddyServices payMyBuddyServices = context.getBean(IPayMyBuddyServices.class);
//		payMyBuddyServices.makeTransaction(new BigDecimal(500.00), "virement test");
//		System.out.println("le virement a bien été effectué");
//


	}
}
