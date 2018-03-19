package fr.imie.speedjob.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Blob;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
  List<User> findAll();
  User findById(Long id);
  User findByMail(String mail);
  Long countByMail(String mail);
}
