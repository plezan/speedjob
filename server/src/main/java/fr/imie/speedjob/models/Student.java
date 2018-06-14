package fr.imie.speedjob.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Student {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  private Date birthDate;

  @Column(nullable = false)
  private boolean validationStatus;

  @Column(nullable = false)
  private boolean inResearch;

  @Lob
  @Column(length = 500)
  private String description;

  private String cv;

  @OneToOne(mappedBy = "student")
  @JsonIgnoreProperties("student")
  private User user;

  public Student() {}

  public Student(Date birthDate, boolean validationStatus, boolean inResearch, String description, String cv, User user) {
    this.birthDate = birthDate;
    this.validationStatus = validationStatus;
    this.inResearch = inResearch;
    this.description = description;
    this.cv = cv;
    this.user = user;
  }

  public Student(Date birthDate, boolean validationStatus, boolean inResearch, String description, User user) {
    this.birthDate = birthDate;
    this.validationStatus = validationStatus;
    this.inResearch = inResearch;
    this.description = description;
    this.user = user;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

  public boolean isValidationStatus() {
    return validationStatus;
  }

  public void setValidationStatus(boolean validationStatus) {
    this.validationStatus = validationStatus;
  }

  public boolean isInResearch() {
    return inResearch;
  }

  public void setInResearch(boolean inResearch) {
    this.inResearch = inResearch;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCv() {
    return cv;
  }

  public void setCv(String cv) {
    this.cv = cv;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public String toString() {
    return "Student{" +
            "id=" + id +
            ", birthDate=" + birthDate +
            ", validationStatus=" + validationStatus +
            ", inResearch=" + inResearch +
            ", description='" + description + '\'' +
            ", cv='" + cv + '\'' +
            ", user=" + user +
            '}';
  }
}
