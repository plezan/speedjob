package fr.imie.speedjob;

import fr.imie.speedjob.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.transaction.Transactional;

@SpringBootApplication
public class SpeedjobApplication implements CommandLineRunner {
		public static void main(String[] args) {
		SpringApplication.run(SpeedjobApplication.class, args);
	}

		private static final Logger log = LoggerFactory.getLogger(SpeedjobApplication.class);

  @Autowired
  private UserRepository userRepository;

		@Bean
		public BCryptPasswordEncoder bCryptPasswordEncoder() {
				return new BCryptPasswordEncoder();
		}

  @Override
  @Transactional
  public void run(String... strings) throws Exception {
    // Save a contact with couple of agencies
    /*Address address1 = new Address("Rue du test", "13B", "35999");
    Address address2 = new Address("Impasse de la sérialisation", "67", "35000");
    Business business = new Business(
            "APIzTestz",
            false,
            "A company description",
            "Domaine des tests",
            "apiztestz.com",
            "0231456780",
            "12345678954321");
    AgencyBusiness a1 = new AgencyBusiness("Rennes API", address1, business);
    AgencyBusiness a2 = new AgencyBusiness("Bruz API", address2, business);
    List<AgencyBusiness> listA = new ArrayList<>();
    listA.add(a1);
    listA.add(a2);
    ContactBusiness cb = new ContactBusiness("Testeur d'APIs", listA);

    User user1 = new User(
            "admin",
            "inistrateur",
            bCryptPasswordEncoder().encode("root"),
            "admin@imie.fr",
            cb);

    userRepository.save(user1);*/

    // fetch all books
    /*for (User user: userRepository.findAll()) {
      log.info(user.toString());
    }*/
  }
}
