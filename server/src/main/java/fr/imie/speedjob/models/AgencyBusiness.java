package fr.imie.speedjob.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.List;

@Entity
public class AgencyBusiness {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  private String name;

  @ManyToMany(mappedBy = "agenciesBusiness", cascade = CascadeType.ALL)
  @JsonIgnoreProperties("agenciesBusiness")
  private List<ContactBusiness> contactsBusiness;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "address_id")
  @JsonIgnoreProperties("agenciesBusiness")
  private Address address;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "business_id")
  @JsonIgnoreProperties("agenciesBusiness")
  private Business business;

  @Value("${some.key:false}")
  private boolean isHeadOffice;

  public AgencyBusiness() {
    this.isHeadOffice = false;
  }

  public AgencyBusiness(String name, Address address, Business business) {
    this.name = name;
    this.address = address;
    this.business = business;
    this.isHeadOffice = false;
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

  public List<ContactBusiness> getContactsBusiness() {
    return contactsBusiness;
  }

  public void setContactsBusiness(List<ContactBusiness> contactsBusiness) {
    this.contactsBusiness = contactsBusiness;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public Business getBusiness() {
    return business;
  }

  public void setBusiness(Business business) {
    this.business = business;
  }

  public boolean isHeadOffice() {
    return isHeadOffice;
  }

  public void setHeadOffice(boolean headOffice) {
    isHeadOffice = headOffice;
  }
}
