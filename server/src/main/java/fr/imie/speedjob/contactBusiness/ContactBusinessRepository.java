package fr.imie.speedjob.contactBusiness;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactBusinessRepository extends JpaRepository<ContactBusiness, Long> {
  List<ContactBusiness> findAll();
  ContactBusiness findById(Long id);
  ContactBusiness countById(Long id);
}