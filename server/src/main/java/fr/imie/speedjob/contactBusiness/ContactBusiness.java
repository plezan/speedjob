package fr.imie.speedjob.contactBusiness;

import fr.imie.speedjob.user.User;

import javax.persistence.*;

@Entity
@Table(name = "contactBusiness")
public class ContactBusiness {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  private String job;

  @Column(nullable = false)
  private boolean validationStatus;

  @OneToOne(mappedBy = "contactBusiness")
  private User user;

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

  @Override
  public String toString() {
    return "ContactBusiness{" +
            "id=" + id +
            ", job='" + job + '\'' +
            ", validationStatus=" + validationStatus +
            ", user=" + user +
            '}';
  }
}
