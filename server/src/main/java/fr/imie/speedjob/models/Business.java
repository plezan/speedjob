package fr.imie.speedjob.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.List;

@Entity
public class Business {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  @Value("${some.key:false}")
  private boolean validationStatus;

  @Column(length = 500)
  private String description;

  private String activityArea;

  private String websiteUrl;

  @Column(length = 20)
  private String siret;

  @OneToMany(
    mappedBy = "business",
    cascade = CascadeType.ALL,
    orphanRemoval = true,
    targetEntity = AgencyBusiness.class
  )
  @JsonIgnoreProperties("business")
  private List<AgencyBusiness> agenciesBusiness;

  public Business() {
    this.validationStatus = false;
  }

  public Business(String name, boolean validationStatus, String description, String activityArea, String websiteUrl, String siret) {
    this.name = name;
    this.validationStatus = validationStatus;
    this.description = description;
    this.activityArea = activityArea;
    this.websiteUrl = websiteUrl;
    this.siret = siret;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isValidationStatus() {
    return validationStatus;
  }

  public void setValidationStatus(boolean validationStatus) {
    this.validationStatus = validationStatus;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getActivityArea() {
    return activityArea;
  }

  public void setActivityArea(String activityArea) {
    this.activityArea = activityArea;
  }

  public String getWebsiteUrl() {
    return websiteUrl;
  }

  public void setWebsiteUrl(String websiteUrl) {
    this.websiteUrl = websiteUrl;
  }

  public String getSiret() {
    return siret;
  }

  public void setSiret(String siret) {
    this.siret = siret;
  }

  public List<AgencyBusiness> getAgenciesBusiness() {
    return agenciesBusiness;
  }

  public void setAgenciesBusiness(List<AgencyBusiness> agenciesBusiness) {
    if (agenciesBusiness.size() == 1)
      agenciesBusiness.get(0).setHeadOffice(true);
    this.agenciesBusiness = agenciesBusiness;
  }

  @JsonIgnore
  public AgencyBusiness getHeadOffice() {
    if (this.getAgenciesBusiness() != null) {
      for (AgencyBusiness agencyBusiness: this.getAgenciesBusiness()) {
        if (agencyBusiness.isHeadOffice())
          return agencyBusiness;
      }
      return null;
    } else return null;
  }
}
