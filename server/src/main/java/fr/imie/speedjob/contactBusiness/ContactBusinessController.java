package fr.imie.speedjob.contactBusiness;

import fr.imie.speedjob.user.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contactsBusiness")
public class ContactBusinessController {
  private ContactBusinessRepository contactBusinessRepository;
  private UserRepository userRepository;

  public ContactBusinessController(ContactBusinessRepository contactBusinessRepository) {
    this.contactBusinessRepository = contactBusinessRepository;
  }
}
