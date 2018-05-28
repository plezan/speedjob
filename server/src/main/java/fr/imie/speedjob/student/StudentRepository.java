package fr.imie.speedjob.student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
  List<Student> findAll();
  Student findById(Long id);
  Long countById(Long id);
}
