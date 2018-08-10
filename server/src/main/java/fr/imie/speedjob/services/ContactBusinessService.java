package fr.imie.speedjob.services;

import com.sun.istack.internal.Nullable;
import commons.Regex;
import fr.imie.speedjob.models.AgencyBusiness;
import fr.imie.speedjob.models.ContactBusiness;
import fr.imie.speedjob.models.User;
import fr.imie.speedjob.repositories.ContactBusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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
   * Get every contacts contactBusiness
   * @return
   */
  public List<ContactBusiness> getAll() {
    return this.contactBusinessRepository.findAll();
  }

  /**
   * Get a contact contactBusiness with its id
   * @param id
   * @return
   */
  public ContactBusiness getOne(Long id) {
    return this.contactBusinessRepository.findById(id);
  }

  public ContactBusiness saveOne(
    ContactBusiness contactBusiness
    ) throws Exception {
    User user = contactBusiness.getUser();
    return this.saveOne(
      contactBusiness.getId(),
      user.getFirstName(),
      user.getLastName(),
      user.getPassword(),
      user.getMail(),
      contactBusiness.getJob(),
      user.getPhone(),
      contactBusiness.getAgenciesBusiness()
    );
  }

  public ContactBusiness saveOne(
    @Nullable Long id,
    String firstName,
    String lastName,
    String password,
    String mail,
    String job,
    @Nullable String phone,
    @Nullable List<AgencyBusiness> agenciesBusiness
    ) throws Exception {
      User user = this.userService.saveOne(id, firstName, lastName, password, mail);
      ContactBusiness contactBusiness = new ContactBusiness(job);
      user.setContactBusiness(contactBusiness);

      if (phone != null && !phone.isEmpty() && Regex.isPhoneValid(phone))
        user.setPhone(phone);

      contactBusiness.setUser(user);

      if (agenciesBusiness != null && !agenciesBusiness.isEmpty())
      contactBusiness.setAgenciesBusiness(agenciesBusiness);

      this.contactBusinessRepository.save(contactBusiness);

      return contactBusiness;
  }

  public ContactBusiness putAgenciesBusiness(ContactBusiness contactBusiness, List<AgencyBusiness> agenciesBusiness) {
    contactBusiness.setAgenciesBusiness(agenciesBusiness);
    this.contactBusinessRepository.save(contactBusiness);

    return contactBusiness;
  }

  public void deleteOne(ContactBusiness contactBusiness) {
    this.contactBusinessRepository.delete(contactBusiness.getId());
  }
}