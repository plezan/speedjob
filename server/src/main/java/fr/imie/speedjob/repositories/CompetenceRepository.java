package fr.imie.speedjob.repositories;

import fr.imie.speedjob.models.Competence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompetenceRepository extends JpaRepository<Competence, Long> {
  List<Competence> findAll();
  Competence findById(Long id);
  Long countById(Long id);
}
