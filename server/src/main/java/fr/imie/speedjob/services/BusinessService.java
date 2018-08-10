/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.imie.speedjob.services;

import com.sun.istack.internal.Nullable;
import commons.Regex;
import fr.imie.speedjob.models.AgencyBusiness;
import fr.imie.speedjob.models.Business;
import fr.imie.speedjob.repositories.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 */
@Service
public class BusinessService {
  @Autowired
  private BusinessRepository businessRepository;
  private String logoPath;

  public List<Business> getAll() {
    return businessRepository.findAll();
  }

  public Business getOne(Long businessId) {
    return businessRepository.getOne(businessId);
  }

  public Business saveOne(
    Business business
  ) throws Exception {
    return this.saveOne(
      business.getId(),
      business.getName(),
      business.getDescription(),
      business.getActivityArea(),
      business.getSiret(),
      business.getWebsiteUrl(),
      business.getAgenciesBusiness()
    );
  }

  public Business saveOne(
    @Nullable Long id,
    String name,
    @Nullable String description,
    @Nullable String activityArea,
    @Nullable String siret,
    @Nullable String websiteUrl,
    @Nullable List<AgencyBusiness> agenciesBusiness
  ) throws Exception {
    // Checks every attribute validity
    if (!name.isEmpty()) {
      Business business = id != null ? this.getOne(id) : new Business();
      business.setName(name);

      if (description != null && !description.isEmpty())
        business.setDescription(description);

      if (activityArea != null && !activityArea.isEmpty())
        business.setActivityArea(activityArea);

      if (siret != null && !siret.isEmpty() && Regex.isSiretValid(siret))
        business.setSiret(siret);
      else if (siret != null && !siret.isEmpty() && !Regex.isSiretValid(siret))
        throw new Exception("SIREN/SIRET/RCS invalide.");

      if (websiteUrl != null && !websiteUrl.isEmpty() && Regex.isWebsiteUrlValid(websiteUrl))
        business.setWebsiteUrl(websiteUrl);
      else if (websiteUrl != null && !websiteUrl.isEmpty() && !Regex.isWebsiteUrlValid(websiteUrl))
        throw new Exception("L'URL du site web entreprise est invalide.");

      if (agenciesBusiness != null && !agenciesBusiness.isEmpty())
        business.setAgenciesBusiness(agenciesBusiness);

      return this.businessRepository.save(business);
    } else {
      throw new Exception("Un nom est requis pour enregistrer une agence.");
    }
  }

  public void deleteOne(Business business) {
    this.businessRepository.delete(business.getId());
  }
}
