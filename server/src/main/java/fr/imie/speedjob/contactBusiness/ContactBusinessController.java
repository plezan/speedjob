package fr.imie.speedjob.contactBusiness;

import fr.imie.speedjob.user.User;
import fr.imie.speedjob.user.UserRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contactsBusiness")
public class ContactBusinessController {
  @Autowired
  private ContactBusinessRepository contactBusinessRepository;
  @Autowired
  private UserRepository userRepository;

  public ContactBusinessController(ContactBusinessRepository contactBusinessRepository, UserRepository userRepository) {
    this.contactBusinessRepository = contactBusinessRepository;
    this.userRepository = userRepository;
  }

  /*
  GET
   */

  // All contacts business
  @GetMapping(value = "/", produces = "application/json")
  public List<ContactBusiness> findContactsBusiness() {
    return contactBusinessRepository.findAll();
  }

  /*
  POST
   */

  // A contact business
  @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> addContactBusiness(@RequestParam String job, @RequestParam Long userId) {
    JSONObject result = new JSONObject();
    User user = userRepository.findById(userId);
    ContactBusiness contactBusiness = new ContactBusiness(job);
    if (user != null) {
      contactBusiness.setUser(user);
      contactBusinessRepository.save(contactBusiness);
      user.setContactBusiness(contactBusiness);
      userRepository.save(user);
      result.put("status", "success");
      result.put("idContactBusiness", contactBusiness.getId());
      return new ResponseEntity<>(result, HttpStatus.OK);
    } else {
      result.put("status", "fail");
      return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }
  }

  /*
  PUT
   */

  // Update job
  @PutMapping(value = "/job")
  public ResponseEntity<Object> updateName(@RequestParam Long id, @RequestParam String job) {
    return this.updateField(id, "job", job);
  }

  // Update validationStatus
  @PutMapping(value = "/validationStatus")
  public ResponseEntity<Object> updateValidationStatus(@RequestParam Long id, @RequestParam String validationStatus) {
    return this.updateField(id, "validationStatus", validationStatus);
  }

  /*
  Private local functions
   */

  private ResponseEntity<Object> updateField(Long id, String key, String value) {
    JSONObject result = new JSONObject();
    HttpStatus httpStatus;
    ContactBusiness contactBusiness = contactBusinessRepository.findById(id);

    if (contactBusiness != null) {
      switch (key) {
        case "job": {
          if (value.length() > 0) {
            contactBusiness.setJob(value);
            result.put("status", "success");
            httpStatus = HttpStatus.OK;
          } else {
            result.put("status","fail");
            result.put("message","Job should contains characters.");
            httpStatus = HttpStatus.BAD_REQUEST;
          }
          break;
        }
        case "validationStatus": {
          if (value.equals("true") || value.equals("false")) {
            boolean validationStatus = Boolean.parseBoolean(value);
            contactBusiness.setValidationStatus(validationStatus);
            result.put("status", "success");
            httpStatus = HttpStatus.OK;
          } else {
            result.put("status","fail");
            result.put("message","Validation status should contains true or false.");
            httpStatus = HttpStatus.BAD_REQUEST;
          }
          break;
        }
        default: {
          httpStatus = HttpStatus.BAD_REQUEST;
        }
      }
    } else {
      result.put("status", "fail");
      result.put("message", "Contact business was not found.");
      httpStatus = HttpStatus.NOT_FOUND;
    }
    return new ResponseEntity<>(result, httpStatus);
  }
}
