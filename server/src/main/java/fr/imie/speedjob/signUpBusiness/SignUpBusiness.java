/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.imie.speedjob.signUpBusiness;

import fr.imie.speedjob.address.Address;
import fr.imie.speedjob.address.AddressRepository;
import fr.imie.speedjob.agencyBusiness.AgencyBusiness;
import fr.imie.speedjob.agencyBusiness.AgencyBusinessRepository;
import fr.imie.speedjob.business.Business;
import fr.imie.speedjob.business.BusinessRepository;
import fr.imie.speedjob.contactBusiness.ContactBusiness;
import fr.imie.speedjob.contactBusiness.ContactBusinessRepository;
import fr.imie.speedjob.user.User;
import fr.imie.speedjob.user.UserRepository;
import java.util.ArrayList;
import java.util.List;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author speedjob
 */
@RestController
@RequestMapping("/signUpBusiness")
public class SignUpBusiness {

    @Autowired
    private AgencyBusinessRepository agencyBusinessRepository;
    @Autowired
    private ContactBusinessRepository contactBusinessRepository;
    @Autowired
    private BusinessRepository businessRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private String logoPath;

    public SignUpBusiness(AgencyBusinessRepository agencyBusinessRepository, ContactBusinessRepository contactBusinessRepository,
            BusinessRepository businessRepository, AddressRepository addressRepository, UserRepository userRepository) {
        this.agencyBusinessRepository = agencyBusinessRepository;
        this.contactBusinessRepository = contactBusinessRepository;
        this.businessRepository = businessRepository;
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> signUpBusiness(@RequestParam String firstName, @RequestParam String lastName,
            @RequestParam String mail, @RequestParam String phone,
            @RequestParam String job, @RequestParam String password,
            @RequestParam String businessName, @RequestParam String streetName,
            @RequestParam String city, @RequestParam String postalCode,
            @RequestParam String businessMail, @RequestParam String businessPhone) {

        JSONObject result = new JSONObject();

        if (userRepository.countByMail(mail) == 0) {
            User user = new User(firstName, lastName,
                    bCryptPasswordEncoder.encode(password),
                    mail);

            ContactBusiness contactBusiness = new ContactBusiness(job);
            contactBusiness.setUser(user);
            user.setContactBusiness(contactBusiness);

            Business business = new Business(businessName, false, "", "", "", businessPhone, businessName);

            Address address = new Address(streetName, "", postalCode, "");

            AgencyBusiness agencyBusiness = new AgencyBusiness(businessName, address, business);
            List<AgencyBusiness> listAgencyBusiness = new ArrayList<>();
            listAgencyBusiness.add(agencyBusiness);
            
            business.setAgenciesBusiness(listAgencyBusiness);
            contactBusiness.setAgenciesBusiness(listAgencyBusiness);
            
            
            contactBusinessRepository.save(contactBusiness);
            userRepository.save(user);
            businessRepository.save(business);
            addressRepository.save(address);
            agencyBusinessRepository.save(agencyBusiness);

            result.put("status", "success");
            result.put("contactBusinessId", contactBusiness.getId());

            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            result.put("status", "fail");
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }
}
