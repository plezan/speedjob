package fr.imie.speedjob.repositories;

import fr.imie.speedjob.models.ContactBusiness;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactBusinessRepository extends JpaRepository<ContactBusiness, Long> {
  List<ContactBusiness> findAll();
  ContactBusiness findById(Long id);
  Long countById(Long id);
}