package fr.imie.speedjob.services;

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

  /**
   * Add a new user
   * @param firstName
   * @param lastName
   * @param password
   * @param mail
   * @return
   */
  public User addOne(
    String firstName,
    String lastName,
    String password,
    String mail
  ) throws Exception {
    if (userRepository.countByMail(mail) == 0) {
      if (
        (!firstName.isEmpty() && firstName.length() <= 20) &&
        (!lastName.isEmpty() && lastName.length() <= 40) &&
        (!password.isEmpty() && password.length() > 6) &&
        (!mail.isEmpty() && Regex.isMailValid(mail))
      ) {
        User user = new User(
          firstName,
          lastName,
          bCryptPasswordEncoder.encode(password),
          mail
        );
        userRepository.save(user);
        return user;
      } else
        throw new Exception("Fields are not valid.");
    } else
      throw new Exception("Same mail is already saved.");
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