package fr.imie.speedjob.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fr.imie.speedjob.contactBusiness.ContactBusiness;

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

		@Column(unique = true)
  private String profileImageUrl;

		@OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name =  "contactBusiness_id")
  @JsonIgnoreProperties("user")
		private ContactBusiness contactBusiness;

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

		public String getProfileImageUrl() {
				return profileImageUrl;
		}

		public void setProfileImageUrl(String profileImageUrl) {
				this.profileImageUrl = profileImageUrl;
		}

  public ContactBusiness getContactBusiness() { return contactBusiness; }

  public void setContactBusiness(ContactBusiness contactBusiness) { this.contactBusiness = contactBusiness; }

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", password='" + password + '\'' +
            ", mail='" + mail + '\'' +
            ", phone='" + phone + '\'' +
            ", profileImageUrl='" + profileImageUrl + '\'' +
            ", contactBusiness=" + contactBusiness +
            '}';
  }
}
