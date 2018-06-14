package fr.imie.speedjob.services;

import fr.imie.speedjob.repositories.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  private UserRepository userRepository;

  public UserDetailsServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
    fr.imie.speedjob.models.User user = userRepository.findByMail(mail);
    if (user == null) {
      throw new UsernameNotFoundException(mail);
    }
    return new User(user.getMail(), user.getPassword(), emptyList());
  }
}
