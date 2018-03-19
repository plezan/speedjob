package fr.imie.speedjob.contactBusiness;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fr.imie.speedjob.agencyBusiness.AgencyBusiness;
import fr.imie.speedjob.user.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "contact_business")
public class ContactBusiness {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  private String job;

  @Column(nullable = false)
  private boolean validationStatus;

  @OneToOne(mappedBy = "contactBusiness")
  @JsonIgnoreProperties("contactBusiness")
  private User user;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "contactb_agencyb",
          joinColumns = @JoinColumn(
                  name = "contact_business_id",
                  referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(
                  name = "agency_business_id",
                  referencedColumnName = "id"))
  @JsonIgnoreProperties("contactsBusiness")
  private List<AgencyBusiness> agenciesBusiness;

  public ContactBusiness() {}

  public ContactBusiness(String job) {
    this.job = job;
    this.validationStatus = false;
  }

  public Long getId() { return id; }

  public void setId(Long id) { this.id = id; }

  public String getJob() { return job; }

  public void setJob(String job) { this.job = job; }

  public boolean isValidationStatus() { return validationStatus; }

  public void setValidationStatus(boolean validationStatus) { this.validationStatus = validationStatus; }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<AgencyBusiness> getAgenciesBusiness() {
    return agenciesBusiness;
  }

  public void setAgenciesBusiness(List<AgencyBusiness> agenciesBusiness) {
    this.agenciesBusiness = agenciesBusiness;
  }

  @Override
  public String toString() {
    return "ContactBusiness{" +
            "id=" + id +
            ", job='" + job + '\'' +
            ", validationStatus=" + validationStatus +
            ", agenciesBusiness=" + agenciesBusiness +
            '}';
  }
}
