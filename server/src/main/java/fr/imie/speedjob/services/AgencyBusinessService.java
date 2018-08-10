package fr.imie.speedjob.services;

import fr.imie.speedjob.models.AgencyBusiness;
import fr.imie.speedjob.repositories.AgencyBusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgencyBusinessService {
  @Autowired
  private AgencyBusinessRepository agencyBusinessRepository;

  public void deleteOne(AgencyBusiness agencyBusiness) { this.agencyBusinessRepository.delete(agencyBusiness.getId()); }
}