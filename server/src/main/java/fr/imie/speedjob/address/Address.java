package fr.imie.speedjob.address;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fr.imie.speedjob.agencyBusiness.AgencyBusiness;

import javax.persistence.*;
import java.util.List;

@Entity
public class Address {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  private String streetName;

  @Column(nullable = false)
  private String streetNumber;

  @Column(nullable = false)
  private String postalCode;

  private String additionalInfo;

  @OneToMany(mappedBy = "address")
  @JsonIgnoreProperties("address")
  private List<AgencyBusiness> agenciesBusiness;

  public Address() {}

  public Address(String streetName, String streetNumber, String postalCode) {
    this.streetName = streetName;
    this.streetNumber = streetNumber;
    this.postalCode = postalCode;
  }

  public Address(String streetName, String streetNumber, String postalCode, String additionalInfo) {
    this.streetName = streetName;
    this.streetNumber = streetNumber;
    this.postalCode = postalCode;
    this.additionalInfo = additionalInfo;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getStreetName() {
    return streetName;
  }

  public void setStreetName(String streetName) {
    this.streetName = streetName;
  }

  public String getStreetNumber() {
    return streetNumber;
  }

  public void setStreetNumber(String streetNumber) {
    this.streetNumber = streetNumber;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getAdditionalInfo() {
    return additionalInfo;
  }

  public void setAdditionalInfo(String additionalInfo) {
    this.additionalInfo = additionalInfo;
  }

  public List<AgencyBusiness> getAgenciesBusiness() {
    return agenciesBusiness;
  }

  public void setAgenciesBusiness(List<AgencyBusiness> agenciesBusiness) {
    this.agenciesBusiness = agenciesBusiness;
  }

  @Override
  public String toString() {
    return "Address{" +
            "id=" + id +
            ", streetName='" + streetName + '\'' +
            ", streetNumber='" + streetNumber + '\'' +
            ", postalCode='" + postalCode + '\'' +
            ", additionalInfo='" + additionalInfo + '\'' +
            ", agenciesBusiness=" + agenciesBusiness +
            '}';
  }
}