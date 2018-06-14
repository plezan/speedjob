package fr.imie.speedjob.services;

import commons.Regex;
import fr.imie.speedjob.models.AgencyBusiness;
import fr.imie.speedjob.models.ContactBusiness;
import fr.imie.speedjob.models.User;
import fr.imie.speedjob.repositories.ContactBusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactBusinessService {
  @Autowired
  private ContactBusinessRepository contactBusinessRepository;
  @Autowired
  private UserService userService;
  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  public ContactBusinessService(
    ContactBusinessRepository contactBusinessRepository,
    UserService userService,
    BCryptPasswordEncoder bCryptPasswordEncoder
  ) {
    this.contactBusinessRepository = contactBusinessRepository;
    this.userService = userService;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  /**
   * Get every contacts business
   * @return
   */
  public List<ContactBusiness> getAll() {
    return this.contactBusinessRepository.findAll();
  }

  /**
   * Get a contact business with its id
   * @param id
   * @return
   */
  public ContactBusiness getOne(Long id) {
    return this.contactBusinessRepository.findById(id);
  }

  public ContactBusiness addOne(
    String firstName,
    String lastName,
    String password,
    String mail,
    String job,
    String phone
    ) throws Exception {
      User user = this.userService.addOne(firstName, lastName, password, mail);
      ContactBusiness contactBusiness = new ContactBusiness(job);
      user.setContactBusiness(contactBusiness);
      if (!phone.isEmpty() && Regex.isPhoneValid(phone))
        user.setPhone(phone);
      contactBusiness.setUser(user);
      this.contactBusinessRepository.save(contactBusiness);

      return contactBusiness;
  }

  public ContactBusiness putAgenciesBusiness(ContactBusiness contactBusiness, List<AgencyBusiness> agenciesBusiness) {
    contactBusiness.setAgenciesBusiness(agenciesBusiness);
    this.contactBusinessRepository.save(contactBusiness);

    return contactBusiness;
  }
}