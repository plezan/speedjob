package fr.imie.speedjob.controllers;

import fr.imie.speedjob.models.AgencyBusiness;
import fr.imie.speedjob.models.Business;
import fr.imie.speedjob.services.AgencyBusinessService;
import fr.imie.speedjob.services.BusinessService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/businesses")
public class BusinessController {
  @Autowired
  private BusinessService businessService;
  @Autowired
  private AgencyBusinessService agencyBusinessService;

  /*
  GET
   */

  // All businesses
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Business> findContactsBusiness() {
    return businessService.getAll();
  }

  /*
  POST
   */

  // A contactBusiness
  @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> addBusiness(
    @RequestParam String name,
    @RequestParam String description,
    @RequestParam String activityArea,
    @RequestParam String websiteUrl,
    @RequestParam String siret
  ) {
    JSONObject result = new JSONObject();
    try {
      Business business = new Business(name, false, description, activityArea, websiteUrl, siret);
      business = businessService.saveOne(business);
      result.put("status", "success");
      result.put("businessId", business.getId());
      return new ResponseEntity<>(result, HttpStatus.OK);
    } catch (Exception e){
      result.put("status", "fail");
      result.put("message", e.getMessage());
      return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }
  }

  /*
  PUT
   */

  // Update name
  @PutMapping(value = "/name")
  public ResponseEntity<Object> updateName(@RequestParam Long id, @RequestParam String name) {
    return this.updateField(id, "name", name);
  }

  // Update validationStatus
  @PutMapping(value = "/validationStatus")
  public ResponseEntity<Object> updateValidationStatus(@RequestParam Long id, @RequestParam String validationStatus) {
    return this.updateField(id, "validationStatus", validationStatus);
  }

  // Update description
  @PutMapping(value = "/description")
  public ResponseEntity<Object> updateDescription(@RequestParam Long id, @RequestParam String description) {
    return this.updateField(id, "description", description);
  }

  // Update activityArea
  @PutMapping(value = "/activityArea")
  public ResponseEntity<Object> updateActivityArea(@RequestParam Long id, @RequestParam String activityArea) {
    return this.updateField(id, "activityArea", activityArea);
  }

  // Update websiteUrl
  @PutMapping(value = "/websiteUrl")
  public ResponseEntity<Object> updateWebsiteUrl(@RequestParam Long id, @RequestParam String websiteUrl) {
    return this.updateField(id, "websiteUrl", websiteUrl);
  }

  // Update siret
  @PutMapping(value = "/siret")
  public ResponseEntity<Object> updateSiret(@RequestParam Long id, @RequestParam String siret) {
    return this.updateField(id, "siret", siret);
  }

  /*
  Private local functions
   */

  private ResponseEntity<Object> updateField(Long id, String key, String value) {
    JSONObject result = new JSONObject();
    HttpStatus httpStatus;
    Business business = businessService.getOne(id);

    if (business != null) {
      switch (key) {
        case "name": {
          if (value.length() > 0) {
            business.setName(value);
            result.put("status", "success");
            httpStatus = HttpStatus.OK;
          } else {
            result.put("status","fail");
            result.put("message","Name should contains characters.");
            httpStatus = HttpStatus.BAD_REQUEST;
          }
          break;
        }
        case "validationStatus": {
          if (value.equals("true") || value.equals("false")) {
            boolean validationStatus = Boolean.parseBoolean(value);
            business.setValidationStatus(validationStatus);
            result.put("status", "success");
            httpStatus = HttpStatus.OK;
          } else {
            result.put("status","fail");
            result.put("message","Validation status should contains true or false.");
            httpStatus = HttpStatus.BAD_REQUEST;
          }
          break;
        }
        case "description": {
          business.setDescription(value);
          result.put("status", "success");
          httpStatus = HttpStatus.OK;
          break;
        }
        case "activityArea": {
          if (value.length() > 0) {
            business.setActivityArea(value);
            result.put("status", "success");
            httpStatus = HttpStatus.OK;
          } else {
            result.put("status","fail");
            result.put("message","Activity area should contains characters.");
            httpStatus = HttpStatus.BAD_REQUEST;
          }
          break;
        }
        case "websiteUrl": {
          if (value.matches("^(http:\\/\\/|https:\\/\\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$")) {
            business.setWebsiteUrl(value);
            result.put("status", "success");
            httpStatus = HttpStatus.OK;
          } else {
            result.put("status","fail");
            result.put("message","Website URL is not valid.");
            httpStatus = HttpStatus.BAD_REQUEST;
          }
          break;
        }
        case "siret": {
          if (value.length() == 14) {
            business.setSiret(value);
            result.put("status", "success");
            httpStatus = HttpStatus.OK;
          } else {
            result.put("status","fail");
            result.put("message","SIRET is not valid.");
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
      result.put("message", "Business was not found.");
      httpStatus = HttpStatus.NOT_FOUND;
    }
    return new ResponseEntity<>(result, httpStatus);
  }

  @DeleteMapping
  public ResponseEntity<Object> deleteBusiness(@RequestParam Long id) throws Exception {
    JSONObject result = new JSONObject();
    HttpStatus httpStatus;

    Business business = this.businessService.getOne(id);
    if (business != null) {
      this.businessService.deleteOne(business);
      result.put("status", "success");
      result.put("message", "L'entreprise a bien été supprimée");
      httpStatus = HttpStatus.OK;
    } else {
      result.put("status", "fail");
      result.put("message", "L'identifiant ne correspond à aucune entreprise.");
      httpStatus = HttpStatus.BAD_REQUEST;
    }
    return new ResponseEntity<>(result, httpStatus);
  }
}
