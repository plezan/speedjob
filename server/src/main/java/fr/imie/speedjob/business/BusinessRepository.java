package fr.imie.speedjob.business;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusinessRepository extends JpaRepository<Business, Long> {
  List<Business> findAll();
  Business findById(Long id);
  Long countById(Long id);
}
