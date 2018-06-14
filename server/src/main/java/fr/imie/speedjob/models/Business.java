package fr.imie.speedjob.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
public class Business {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  private boolean validationStatus;

  @Lob
  @Column(length = 500)
  private String description;

  private String activityArea;

  private String websiteUrl;

  @Column(length = 20)
  private String phone;

  @Column(length = 20)
  private String siret;

  @Column
  private String profileImageUrl;

  @OneToMany(mappedBy = "business")
  @JsonIgnoreProperties("business")
  private List<AgencyBusiness> agenciesBusiness;

  public Business() {}

  public Business(String name, boolean validationStatus, String description, String activityArea, String websiteUrl, String phone, String siret) {
    this.name = name;
    this.validationStatus = validationStatus;
    this.description = description;
    this.activityArea = activityArea;
    this.websiteUrl = websiteUrl;
    this.phone = phone;
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

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
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
    this.agenciesBusiness = agenciesBusiness;
  }

  @Override
  public String toString() {
    return "Business{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", validationStatus=" + validationStatus +
            ", description='" + description + '\'' +
            ", activityArea='" + activityArea + '\'' +
            ", websiteUrl='" + websiteUrl + '\'' +
            ", phone='" + phone + '\'' +
            ", siret='" + siret + '\'' +
            ", agenciesBusiness=" + agenciesBusiness +
            '}';
  }
}
