package fr.imie.speedjob.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
public class AgencyBusiness {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  private String name;

  @ManyToMany(mappedBy = "agenciesBusiness")
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

  public AgencyBusiness() {}

  public AgencyBusiness(String name, Address address, Business business) {
    this.name = name;
    this.address = address;
    this.business = business;
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

  @Override
  public String toString() {
    return "AgencyBusiness{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", contactsBusiness=" + contactsBusiness +
            ", address=" + address +
            ", business=" + business +
            '}';
  }
}
