package fr.imie.speedjob.repositories;

import fr.imie.speedjob.models.Business;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusinessRepository extends JpaRepository<Business, Long> {
  List<Business> findAll();
  Business findById(Long id);
  Long countById(Long id);
}
