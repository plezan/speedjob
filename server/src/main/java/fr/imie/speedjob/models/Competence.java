package fr.imie.speedjob.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
public class Competence {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private Integer level;

  @ManyToMany(mappedBy = "competences", targetEntity = Student.class)
  @JsonIgnoreProperties("competences")
  private List<User> userCompetences;

  public void setId(Long id) { this.id = id; }

  public Long getId() { return id; }

  public void setName(String name) { this.name = name; }

  public String getName() { return name; }

  public void setLevel(Integer level) { this.level = level; }

  public Integer getLevel() { return level; }

  public void setUserCompetences(List<User> userCompetences) { this.userCompetences = userCompetences; }

  public List<User> getUserCompetences() { return userCompetences; }

}
