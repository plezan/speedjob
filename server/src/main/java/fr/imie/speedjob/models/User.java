package fr.imie.speedjob.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
public class User {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String firstName;

  @Column(nullable = false)
  private String lastName;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String mail;

  @Column(length = 12)
  private String phone;

  @Column
  private Boolean hasProfileImage = false;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name =  "contactBusiness_id")
  @JsonIgnoreProperties("user")
  private ContactBusiness contactBusiness;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name =  "student_id")
  @JsonIgnoreProperties("user")
  private Student student;

  public User() {}

  public User(String firstName, String lastName, String password, String mail) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.password = password;
    this.mail = mail;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() { return lastName;	}

  public void setLastName(String lastName) { this.lastName = lastName;	}

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getMail() {	return mail;	}

  public void setMail(String mail) {	this.mail = mail; }

  public String getPhone() { return phone; }

  public void setPhone(String phone) { this.phone = phone; }

  public Boolean getHasProfileImage() {
    return hasProfileImage;
  }

  public void setHasProfileImage(Boolean hasProfileImage) {
    this.hasProfileImage = hasProfileImage;
  }

  public ContactBusiness getContactBusiness() { return contactBusiness; }

  public void setContactBusiness(ContactBusiness contactBusiness) { this.contactBusiness = contactBusiness; }

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }
}
