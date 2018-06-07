package fr.imie.speedjob.controllers;

import fr.imie.speedjob.models.Address;
import fr.imie.speedjob.repositories.AddressRepository;
import fr.imie.speedjob.repositories.AgencyBusinessRepository;
import fr.imie.speedjob.models.AgencyBusiness;
import fr.imie.speedjob.models.Business;
import fr.imie.speedjob.repositories.BusinessRepository;
import fr.imie.speedjob.models.ContactBusiness;
import fr.imie.speedjob.repositories.ContactBusinessRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agenciesBusiness")
public class AgencyBusinessController {
  @Autowired
  private AgencyBusinessRepository agencyBusinessRepository;
  @Autowired
  private ContactBusinessRepository contactBusinessRepository;
  @Autowired
  private BusinessRepository businessRepository;
  @Autowired
  private AddressRepository addressRepository;

  public AgencyBusinessController(AgencyBusinessRepository agencyBusinessRepository, ContactBusinessRepository contactBusinessRepository,
                                  BusinessRepository businessRepository, AddressRepository addressRepository) {
    this.agencyBusinessRepository = agencyBusinessRepository;
    this.contactBusinessRepository = contactBusinessRepository;
    this.businessRepository = businessRepository;
    this.addressRepository = addressRepository;
  }

  /*
  POST
   */

  // An agency business
  @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> addAgencyBusiness(@RequestParam String name, @RequestParam String streetName,
                                                  @RequestParam String streetNumber, @RequestParam String postalCode,
                                                  @RequestParam String additionalInfo, @RequestParam Long businessId) {
    JSONObject result = new JSONObject();
    HttpStatus httpStatus;
    Business business = businessRepository.findById(businessId);
    if (business != null) {
      if (!name.equals("") && !streetName.equals("")
              && !streetNumber.equals("") && postalCode.length() == 5) {
        Address address = new Address(streetName, streetNumber, postalCode, additionalInfo);
        addressRepository.save(address);
        AgencyBusiness agencyBusiness = new AgencyBusiness(name, address, business);
        agencyBusinessRepository.save(agencyBusiness);
        result.put("status", "success");
        result.put("agencyBusinessId", agencyBusiness.getId());
        httpStatus = HttpStatus.OK;
      } else {
        result.put("status", "fail");
        result.put("message", "Inputs are not valid");
        httpStatus = HttpStatus.BAD_REQUEST;
      }
    } else {
      result.put("status", "fail");
      httpStatus = HttpStatus.NOT_FOUND;
    }
    return new ResponseEntity<>(result, httpStatus);
  }

  /*
  PUT
   */

  // Bind a contact business
  @PutMapping(value = "/bindContactBusiness", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> bindContactBusiness(@RequestParam Long agencyBusinessId, @RequestParam Long contactBusinessId) {
    JSONObject result = new JSONObject();
    HttpStatus httpStatus;
    AgencyBusiness agencyBusiness = agencyBusinessRepository.findById(agencyBusinessId);
    ContactBusiness contactBusiness = contactBusinessRepository.findById(contactBusinessId);
    if (agencyBusiness != null && contactBusiness != null
        && !contactBusiness.getAgenciesBusiness().contains(agencyBusiness)
        && !agencyBusiness.getContactsBusiness().contains(contactBusiness)) {
      List<AgencyBusiness> agenciesBusiness = contactBusiness.getAgenciesBusiness();
      agenciesBusiness.add(agencyBusiness);
      contactBusiness.setAgenciesBusiness(agenciesBusiness);
      contactBusinessRepository.save(contactBusiness);
      result.put("status", "success");
      httpStatus = HttpStatus.OK;
    } else {
      result.put("status", "fail");
      httpStatus = HttpStatus.NOT_FOUND;
    }
    return new ResponseEntity<>(result, httpStatus);
  }

  // Update name
  @PutMapping(value = "/name")
  public ResponseEntity<Object> updateName(@RequestParam Long id, @RequestParam String name) {
    return this.updateField(id, "name", name);
  }

  // Update streetName
  @PutMapping(value = "/streetName")
  public ResponseEntity<Object> updateStreetName(@RequestParam Long id, @RequestParam String streetName) {
    return this.updateField(id, "streetName", streetName);
  }

  // Update streetNumber
  @PutMapping(value = "/streetNumber")
  public ResponseEntity<Object> updateStreetNumber(@RequestParam Long id, @RequestParam String streetNumber) {
    return this.updateField(id, "streetNumber", streetNumber);
  }

  // Update postalCode
  @PutMapping(value = "/postalCode")
  public ResponseEntity<Object> updatePostalCode(@RequestParam Long id, @RequestParam String postalCode) {
    return this.updateField(id, "postalCode", postalCode);
  }

  // Update additionalInfo
  @PutMapping(value = "/additionalInfo")
  public ResponseEntity<Object> updateAdditionalInfo(@RequestParam Long id, @RequestParam String additionalInfo) {
    return this.updateField(id, "additionalInfo", additionalInfo);
  }

  /*
  DELETE
   */

  // Unbind a contact business
  @DeleteMapping(value = "/unbindContactBusiness", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> unbindContactBusiness(@RequestParam Long agencyBusinessId, @RequestParam Long contactBusinessId) {
    JSONObject result = new JSONObject();
    HttpStatus httpStatus;
    AgencyBusiness agencyBusiness = agencyBusinessRepository.findById(agencyBusinessId);
    ContactBusiness contactBusiness = contactBusinessRepository.findById(contactBusinessId);
    if (agencyBusiness != null && contactBusiness != null
        && contactBusiness.getAgenciesBusiness().contains(agencyBusiness)
        && agencyBusiness.getContactsBusiness().contains(contactBusiness)) {
      List<AgencyBusiness> agenciesBusiness = contactBusiness.getAgenciesBusiness();
      agenciesBusiness.remove(agencyBusiness);
      contactBusiness.setAgenciesBusiness(agenciesBusiness);
      contactBusinessRepository.save(contactBusiness);
      result.put("status", "success");
      httpStatus = HttpStatus.OK;
    } else {
      result.put("status", "fail");
      httpStatus = HttpStatus.NOT_FOUND;
    }
    return new ResponseEntity<>(result, httpStatus);
  }

  /*
  Private local functions
   */

  private ResponseEntity<Object> updateField(Long id, String key, String value) {
    JSONObject result = new JSONObject();
    HttpStatus httpStatus;
    AgencyBusiness agencyBusiness = agencyBusinessRepository.findById(id);

    if (agencyBusiness != null) {
      switch (key) {
        case "name": {
          if (value.length() > 0) {
            agencyBusiness.setName(value);
            result.put("status", "success");
            httpStatus = HttpStatus.OK;
          } else {
            result.put("status","fail");
            result.put("message","Name should contains characters.");
            httpStatus = HttpStatus.BAD_REQUEST;
          }
          break;
        }
        case "streetName": {
          if (value.length() > 0) {
            Address address = agencyBusiness.getAddress();
            address.setStreetName(value);
            result.put("status", "success");
            httpStatus = HttpStatus.OK;
          } else {
            result.put("status","fail");
            result.put("message","Street name should contains characters.");
            httpStatus = HttpStatus.BAD_REQUEST;
          }
          break;
        }
        case "streetNumber": {
          if (value.length() > 0) {
            Address address = agencyBusiness.getAddress();
            address.setStreetNumber(value);
            result.put("status", "success");
            httpStatus = HttpStatus.OK;
          } else {
            result.put("status","fail");
            result.put("message","Street number should contains characters.");
            httpStatus = HttpStatus.BAD_REQUEST;
          }
          break;
        }
        case "postalCode": {
          if (value.length() == 5) {
            Address address = agencyBusiness.getAddress();
            address.setPostalCode(value);
            result.put("status", "success");
            httpStatus = HttpStatus.OK;
          } else {
            result.put("status","fail");
            result.put("message","Postal code should contains 5 characters.");
            httpStatus = HttpStatus.BAD_REQUEST;
          }
          break;
        }
        case "additionalInfo": {
          Address address = agencyBusiness.getAddress();
          address.setAdditionalInfo(value);
          result.put("status", "success");
          httpStatus = HttpStatus.OK;
          break;
        }
        default: {
          httpStatus = HttpStatus.BAD_REQUEST;
        }
      }

    } else {
      result.put("status", "fail");
      result.put("message", "Agency business was not found.");
      httpStatus = HttpStatus.NOT_FOUND;
    }
    return new ResponseEntity<>(result, httpStatus);
  }
}
