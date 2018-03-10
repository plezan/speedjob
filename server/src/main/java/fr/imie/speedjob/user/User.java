package fr.imie.speedjob.user;

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

		@Override
  public String toString() {
    return String.format(
            "User[id=%d, firstName=%s, lastName=%s, password=%s, mail=%s, phone=%s, profileImageUrl=%s]",
            id, firstName, lastName, password, mail, phone, profileImageUrl);
  }

}
