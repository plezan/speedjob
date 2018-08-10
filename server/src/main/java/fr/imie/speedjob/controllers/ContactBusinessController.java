package fr.imie.speedjob.controllers;

import fr.imie.speedjob.models.AgencyBusiness;
import fr.imie.speedjob.models.Business;
import fr.imie.speedjob.models.ContactBusiness;
import fr.imie.speedjob.models.User;
import fr.imie.speedjob.services.AgencyBusinessService;
import fr.imie.speedjob.services.BusinessService;
import fr.imie.speedjob.services.ContactBusinessService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contactsBusiness")
public class ContactBusinessController {
  @Autowired
  private ContactBusinessService contactBusinessService;
  @Autowired
  private AgencyBusinessService agencyBusinessService;
  @Autowired
  private BusinessService businessService;

  /*
  GET
   */

  // All contacts contactBusiness
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
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
      ContactBusiness contactBusiness = this.contactBusinessService.saveOne(
        null,
        firstName,
        lastName,
        password,
        mail,
        job,
        phone,
        null
      );
      result.put("status", "success");
      result.put("idContactBusiness", contactBusiness.getId());
      return new ResponseEntity<>(result, HttpStatus.OK);
    } catch (Exception e) {
      result.put("status", "fail");
      return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }
  }


  @PostMapping(value = "/withBusiness", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> addContactBusinessWithBusiness(
    @RequestParam String firstName,
    @RequestParam String lastName,
    @RequestParam String mail,
    @RequestParam String job,
    @RequestParam String password,
    @RequestParam String businessName,
    @RequestParam String activityArea,
    @RequestParam(required = false) String phone,
    @RequestParam(required = false) String siret,
    @RequestParam(required = false) String businessWebsiteUrl
  ) {
    JSONObject result = new JSONObject();
    try {
      ContactBusiness contactBusiness = new ContactBusiness();
      User user = new User();
      user.setFirstName(firstName);
      user.setLastName(lastName);
      user.setPassword(password);
      user.setMail(mail);
      contactBusiness.setJob(job);
      user.setPhone(phone);
      contactBusiness.setUser(user);

      // Creation of a blank agency business that will be the main
      Business business = new Business();
      business.setName(businessName);
      business.setSiret(siret);
      business.setWebsiteUrl(businessWebsiteUrl);
      business.setActivityArea(activityArea);

      AgencyBusiness agencyBusiness = new AgencyBusiness();
      agencyBusiness.setName(businessName + " siège social");
      agencyBusiness.setBusiness(business);
      List<AgencyBusiness> agenciesBusiness = new ArrayList<>();
      agenciesBusiness.add(agencyBusiness);
      business.setAgenciesBusiness(agenciesBusiness);
      contactBusiness.setAgenciesBusiness(agenciesBusiness);

      // Save all data
      //business = this.businessService.saveOne(business);
      contactBusiness = this.contactBusinessService.saveOne(contactBusiness);

      result.put("status", "success");
      result.put("userId", contactBusiness.getUser().getId());
      return new ResponseEntity<>(result, HttpStatus.OK);
    } catch (Exception e) {
      result.put("status", "fail");
      result.put("message", e.getMessage());
      return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping(value = "/withExistantBusiness", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> addContactBusinessWithExistantBusiness(
    @RequestParam String firstName,
    @RequestParam String lastName,
    @RequestParam String mail,
    @RequestParam String job,
    @RequestParam String password,
    @RequestParam Long businessId,
    @RequestParam(required = false) String phone
  ) {
    JSONObject result = new JSONObject();
    try {
      ContactBusiness contactBusiness = new ContactBusiness();
      User user = new User();
      user.setFirstName(firstName);
      user.setLastName(lastName);
      user.setPassword(password);
      user.setMail(mail);
      contactBusiness.setJob(job);
      user.setPhone(phone);
      contactBusiness.setUser(user);

      // Adding existant business
      Business business = this.businessService.getOne(businessId);
      if (business == null)
        throw new Exception("L'entreprise n'a pas été trouvée avec cet identifiant.");

      // Get main agency business associated with found business
      AgencyBusiness agencyBusiness = business.getHeadOffice();
      agencyBusiness.getContactsBusiness().add(contactBusiness);

      contactBusiness = this.contactBusinessService.saveOne(contactBusiness);
      result.put("status", "success");
      result.put("userId", contactBusiness.getUser().getId());
      return new ResponseEntity<>(result, HttpStatus.OK);
    } catch (Exception e) {
      result.put("status", "fail");
      result.put("message", e.getMessage());
      return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }
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
  @DeleteMapping
  public ResponseEntity<Object> deleteContactBusiness(@RequestParam Long id) {
    JSONObject result = new JSONObject();
    HttpStatus httpStatus;

    ContactBusiness contactBusiness = this.contactBusinessService.getOne(id);
    if (contactBusiness != null) {
      this.contactBusinessService.deleteOne(contactBusiness);
      result.put("status", "success");
      result.put("message", "Le contact entreprise a bien été supprimé");
      httpStatus = HttpStatus.OK;
    } else {
      result.put("status", "fail");
      result.put("message", "L'identifiant du contact entreprise est introuvable.");
      httpStatus = HttpStatus.BAD_REQUEST;
    }

    return new ResponseEntity<>(result, httpStatus);
  }
}