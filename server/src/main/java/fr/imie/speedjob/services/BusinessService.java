/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.imie.speedjob.services;

import commons.Regex;
import fr.imie.speedjob.models.Business;
import fr.imie.speedjob.repositories.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class BusinessService {
  @Autowired
  private BusinessRepository businessRepository;
  private String logoPath;

  public BusinessService(BusinessRepository businessRepository) {
    this.businessRepository = businessRepository;
  }

  /**
   *
   * @param name
   * @param description
   * @param activityArea
   * @param phone
   * @param siret
   * @param websiteUrl
   * @return
   */
  public Business addOne(
    String name,
    String phone,
    String description,
    String activityArea,
    String siret,
    String websiteUrl
  ) {
    // Checks every attribute validity
    if (!name.isEmpty() &&
      Regex.isPhoneValid(phone) &&
      (Regex.isSiretValid(siret) || siret.isEmpty()) &&
      (Regex.isWebsiteUrlValid(websiteUrl) || websiteUrl.isEmpty())
      ) {
      Business business = new Business();
      business.setName(name);
      business.setDescription(description);
      business.setActivityArea(activityArea);
      business.setPhone(phone);
      business.setSiret(siret);
      business.setWebsiteUrl(websiteUrl);
      this.businessRepository.save(business);

      return business;
    } else return null;
  }
}
