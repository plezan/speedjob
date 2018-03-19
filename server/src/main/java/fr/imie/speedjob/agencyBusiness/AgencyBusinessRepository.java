package fr.imie.speedjob.agencyBusiness;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgencyBusinessRepository extends JpaRepository<AgencyBusiness, Long> {
  List<AgencyBusiness> findAll();
  AgencyBusiness findById(Long id);
  AgencyBusiness countById(Long id);
}