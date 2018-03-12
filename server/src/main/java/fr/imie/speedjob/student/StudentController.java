package fr.imie.speedjob.student;

import fr.imie.speedjob.user.User;
import fr.imie.speedjob.user.UserRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
  @Autowired
  private StudentRepository studentRepository;
  @Autowired
  private UserRepository userRepository;

  public StudentController(StudentRepository studentRepository, UserRepository userRepository) {
    this.studentRepository = studentRepository;
    this.userRepository = userRepository;
  }

  /*
  GET
   */

  // All students
  @GetMapping(value = "/", produces = "application/json")
  public List<Student> findStudents() {
    return studentRepository.findAll();
  }

  /*
  POST
   */

  // A student
  @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> addStudent(@RequestParam Date birthDate, @RequestParam boolean inResearch,
                                           @RequestParam String description, @RequestParam Long userId) {
    JSONObject result = new JSONObject();
    User user = userRepository.findById(userId);
    if (user != null && user.getContactBusiness() == null) {
      Student student = new Student(birthDate, false, inResearch, description, user);
      student.setUser(user);
      studentRepository.save(student);
      user.setStudent(student);
      userRepository.save(user);
      result.put("status", "success");
      result.put("studentId", student.getId());
      return new ResponseEntity<>(result, HttpStatus.OK);
    } else {
      result.put("status", "fail");
      return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }
  }
}