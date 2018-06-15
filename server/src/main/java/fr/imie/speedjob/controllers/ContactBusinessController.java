package fr.imie.speedjob.controllers;

import fr.imie.speedjob.models.ContactBusiness;
import fr.imie.speedjob.models.User;
import fr.imie.speedjob.services.ContactBusinessService;
import fr.imie.speedjob.services.UserService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contactsBusiness")
public class ContactBusinessController {
  @Autowired
  private ContactBusinessService contactBusinessService;
  @Autowired
  private UserService userService;

  public ContactBusinessController(ContactBusinessService contactBusinessService, UserService userService) {
    this.contactBusinessService = contactBusinessService;
    this.userService = userService;
  }

  /*
  GET
   */

  // All contacts contactBusiness
  @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<ContactBusiness> findContactsBusiness() {
    return this.contactBusinessService.getAll();
  }

  /*
  POST
   */

  // A contact contactBusiness
  @PostMapping(value = "/addOne", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> addContactBusiness(
    @RequestParam String firstName,
    @RequestParam String lastName,
    @RequestParam String password,
    @RequestParam String mail,
    @RequestParam(required = false) String phone,
    @RequestParam String job
  ) {
    JSONObject result = new JSONObject();
    try {
      ContactBusiness contactBusiness = this.contactBusinessService.addOne(
        firstName,
        lastName,
        password,
        mail,
        job,
        phone
      );
      result.put("status", "success");
      result.put("idContactBusiness", contactBusiness.getId());
      return new ResponseEntity<>(result, HttpStatus.OK);
    } catch (Exception e) {
      result.put("status", "fail");
      return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }
  }


  @PostMapping(value = "/addOneWithBusiness", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> addContactBusinessWithBusiness(
    @RequestParam String firstName,
    @RequestParam String lastName,
    @RequestParam String mail,
    @RequestParam(required = false) String phone,
    @RequestParam String job,
    @RequestParam String password,
    @RequestParam String businessName,
    @RequestParam(required = false) String siret,
    @RequestParam(required = false) String businessWebsiteurl
  ) {
    JSONObject result = new JSONObject();
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  /*
  PUT
   */

  // Update job
  /*@PutMapping(value = "/job")
  public ResponseEntity<Object> updateName(@RequestParam Long id, @RequestParam String job) {
    return this.updateField(id, "job", job);
  }

  // Update validationStatus
  @PutMapping(value = "/validationStatus")
  public ResponseEntity<Object> updateValidationStatus(@RequestParam Long id, @RequestParam String validationStatus) {
    return this.updateField(id, "validationStatus", validationStatus);
  }*/

  /*
  Private local functions
   */

  /*private ResponseEntity<Object> updateField(Long id, String key, String value) {
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
      result.put("message", "Contact contactBusiness was not found.");
      httpStatus = HttpStatus.NOT_FOUND;
    }
    return new ResponseEntity<>(result, httpStatus);
  }*/

  /*
  DELETE
   */
}