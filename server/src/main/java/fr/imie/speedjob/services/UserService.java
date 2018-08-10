package fr.imie.speedjob.services;

import com.sun.istack.internal.Nullable;
import commons.Regex;
import fr.imie.speedjob.models.User;
import fr.imie.speedjob.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private FileService fileService;
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  /**
   *
   * @param userRepository
   * @param bCryptPasswordEncoder
   */
  public UserService(
    UserRepository userRepository,
    FileService fileService,
    BCryptPasswordEncoder bCryptPasswordEncoder
  ) {
    this.userRepository = userRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  /**
   * Get every users
   * @return
   */
  public List<User> getAll() {
    return userRepository.findAll();
  }

  /**
   * Get a user with its id
   * @param id
   * @return
   */
  public User getOne(Long id) {
    return userRepository.findById(id);
  }

  public User saveOne(
    User user
  ) throws Exception {
    return this.saveOne(
      user.getId(),
      user.getFirstName(),
      user.getLastName(),
      user.getPassword(),
      user.getMail()
    );
  }

  public User saveOne(
    @Nullable Long id,
    String firstName,
    String lastName,
    String password,
    String mail
  ) throws Exception {
    User user = new User();
    
    if (id != null)
      user.setId(id);
    else {
      if (userRepository.countByMail(mail) == 0) {
        if (!password.isEmpty() && password.length() >= 6)
          user.setPassword(bCryptPasswordEncoder.encode(password));
        else throw new Exception("Le mot de passe n'est pas valide.");

        if (!mail.isEmpty() && Regex.isMailValid(mail))
          user.setMail(mail);
        else throw new Exception("Les attributs ne sont pas valides.");
      } else throw new Exception("L'adresse mail est déjà utilisée.");
    }
    if (!firstName.isEmpty() && firstName.length() <= 20)
      user.setFirstName(firstName);
    if (!lastName.isEmpty() && lastName.length() <= 40)
      user.setLastName(lastName);

    return userRepository.save(user);
  }

  public void updateProfileImage(MultipartFile profileImage, User user) {
    this.fileService.updateProfileImage(profileImage, user.getFirstName(), user.getLastName());
    user.setHasProfileImage(true);
    this.userRepository.save(user);
  }

  public void deleteOne(User user) {
    this.userRepository.delete(user.getId());
  }
}