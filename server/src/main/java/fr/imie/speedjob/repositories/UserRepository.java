package fr.imie.speedjob.repositories;

import fr.imie.speedjob.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
  List<User> findAll();
  User findById(Long id);
  User findByMail(String mail);
  Long countByMail(String mail);
}
