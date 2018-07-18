package fr.imie.speedjob.offer;
import fr.imie.speedjob.user.User;
import fr.imie.speedjob.competence.Competence; 


import javax.persistence.*;
import java.util.List;
import java.util.Date;

@Entity 
@Table(name="offer")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Date startDate; 

    @Column(nullable = false)
    private Date endDate; 

    @Column(nullable = false)
    private Int status; 

    	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Int getStatus() {
		return status;
	}

	public void setStatus(Int status) {
		this.status = status;
	}
}


