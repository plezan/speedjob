package fr.imie.speedjob.controllers;

import fr.imie.speedjob.SpeedjobApplication;
import fr.imie.speedjob.repositories.CompetenceRepository;
import fr.imie.speedjob.models.Competence;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

@CrossOrigin
@RestController
@RequestMapping("/competence")
class CompetenceController {
    private static final Logger log = LoggerFactory.getLogger(SpeedjobApplication.class);

    @Autowired
    private fr.imie.speedjob.repositories.CompetenceRepository CompetenceRepository;

    public CompetenceController(CompetenceRepository competenceRepository) {
        this.CompetenceRepository = competenceRepository;
        final Properties properties = new Properties();
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream("config.properties");
            properties.load(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

  /*
  GET
   */

  @GetMapping(value = "/competence")
  public List<Competence> competences () {
      return CompetenceRepository.findAll();
  }

  /*
  POST
   */

  /*
  PUT
   */

  /*
  DELETE
   */

  /*
  Private local functions
   */

}