package fr.imie.speedjob.competence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompetenceRepository extends JpaRepository<Competence, Long> {
  List<Competence> findAll();
  Competence findById(Long id);
  Long countById(Long id);
}
