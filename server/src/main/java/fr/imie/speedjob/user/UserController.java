package fr.imie.speedjob.user;

import fr.imie.speedjob.SpeedjobApplication;
import fr.imie.speedjob.agencyBusiness.AgencyBusiness;
import fr.imie.speedjob.competence.Competence;
import fr.imie.speedjob.competence.CompetenceRepository;
import fr.imie.speedjob.contactBusiness.ContactBusiness;
import jdk.nashorn.internal.parser.JSONParser;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.validation.constraints.Null;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

@Transactional
@CrossOrigin
@RestController
@RequestMapping("/users")
class UserController {
  private static final Logger log = LoggerFactory.getLogger(SpeedjobApplication.class);

  @Autowired
  private UserRepository userRepository;
  private CompetenceRepository competenceRepository;
  private BCryptPasswordEncoder bCryptPasswordEncoder;
  private String profileImagePath;
  private String cvFilePath;

  public UserController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, CompetenceRepository competenceRepository) {
    this.userRepository = userRepository;
    this.competenceRepository = competenceRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    final Properties properties = new Properties();
    try {
      ClassLoader classloader = Thread.currentThread().getContextClassLoader();
      InputStream is = classloader.getResourceAsStream("config.properties");
      properties.load(is);
      profileImagePath = properties.getProperty("config.path.profileImages");
      cvFilePath = properties.getProperty("config.path.cvFile");
    } catch (Exception e) {
      profileImagePath = null;
      cvFilePath = null;
      e.printStackTrace();
    }
  }

  /*
  GET
   */

  // All users
  @GetMapping(value = "/", produces = "application/json")
  public List<User> findUsers() {
  		return userRepository.findAll();
  }

  // Get profileImage
  @GetMapping(value = "/getProfileImage")
  public ResponseEntity<byte[]> getProfileImage(@RequestParam Long id) throws IOException {
    User user = userRepository.findById(id);
    if (user != null) {
      File dir = new File(profileImagePath);
      File[] matches = dir.listFiles(((dir1, name) ->
              name.startsWith(user.getFirstName()+"_"+user.getLastName())));
      if (matches.length < 0 || matches.length > 1)
        return ResponseEntity
                .notFound()
                .build();
      else {
        byte[] bytes = StreamUtils.copyToByteArray(new FileInputStream(matches[0]));
        // Get extension of uploaded file and locally create it
        String[] filenameDotParts = matches[0].getName().split("\\.");
        if (filenameDotParts[filenameDotParts.length-1].toLowerCase().equals("jpg")
                || filenameDotParts[filenameDotParts.length-1].toLowerCase().equals("jpeg"))
          return ResponseEntity
                  .ok()
                  .contentType(MediaType.IMAGE_JPEG)
                  .body(bytes);
        else if (filenameDotParts[filenameDotParts.length-1].toLowerCase().equals("png"))
          return ResponseEntity
                  .ok()
                  .contentType(MediaType.IMAGE_PNG)
                  .body(bytes);
        else if (filenameDotParts[filenameDotParts.length-1].toLowerCase().equals("gif"))
          return ResponseEntity
                  .ok()
                  .contentType(MediaType.IMAGE_GIF)
                  .body(bytes);
      }
      return ResponseEntity
              .noContent()
              .build();
    } else {
      return ResponseEntity
              .notFound()
              .build();
    }
  }

  @GetMapping(value = "/user")
  public User getUser(@RequestParam Long id) {
      return userRepository.findById(id);
  }

  /*
  POST
   */

  // A user
  @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> addUser(@RequestParam String firstName, @RequestParam String lastName,
																																						@RequestParam String mail, @RequestParam String password) {
    JSONObject result = new JSONObject();
    if (userRepository.countByMail(mail) == 0) {
						User user = new User(
						        firstName,
                                lastName,
                                bCryptPasswordEncoder.encode(password),
                                mail);
						userRepository.save(user);
						result.put("status", "success");
						result.put("userId", user.getId());
      return new ResponseEntity<>(result, HttpStatus.OK);
				} else {
      result.put("status", "fail");
      return new ResponseEntity<>(result, HttpStatus.CONFLICT);
				}
  }

  /*
  PUT
   */

  // Update profileImage
		@PutMapping(value = "/updateProfileImage")
		public @ResponseBody String updateProfileImage(@RequestParam Long id, @RequestParam("profileImage") MultipartFile profileImage) {
  		User user = userRepository.findById(id);
				if (user != null) {
				  String firstName = user.getFirstName();
				  String lastName = user.getLastName();
						try {
								InputStream inputStream;
								OutputStream outputStream;
        // Check existance of a profile image, if so delete it
        if (profileImageExists(firstName, lastName))
          deleteProfileImageFile(firstName, lastName);
        // Get extension of uploaded file and locally create it
								String[] filenameDotParts = profileImage.getOriginalFilename().split("\\.");
								File image = new File(profileImagePath+"/"+firstName+"_"+lastName+"."+filenameDotParts[filenameDotParts.length-1]);

								try {
										inputStream = profileImage.getInputStream();

										if (!image.exists())
												image.createNewFile();

										outputStream = new FileOutputStream(image);
										int read = 0;
										byte[] bytes = new byte[1024];

										while ((read = inputStream.read(bytes)) != -1) {
												outputStream.write(bytes, 0, read);
										}
										outputStream.close();
										inputStream.close();
								} catch (IOException e) {
										e.printStackTrace();
								}
						} catch (Exception e) {
								e.printStackTrace();
						}
						return "Saved";
				} else {
						return "User doesn't exists";
				}
		}

		@PutMapping(value = "/updateUser")
        public @ResponseBody User updateUser(@RequestBody User user) {
		    User userFound = userRepository.findById(user.getId());
		    if (userFound != null) {
		        user.setPassword(userFound.getPassword());
                userRepository.save(user);
                userFound = userRepository.findById(user.getId());
		        return userFound;
            } else {
		        return null;
            }
        }

        @PutMapping(value = "/updateCv")
        public @ResponseBody User updateCv(@RequestParam Long id, @RequestBody MultipartFile file) {
            User userFound = userRepository.findById(id);
            if (userFound != null) {
                String firstName = userFound.getFirstName();
                String lastName = userFound.getLastName();
                String[] filenameDotParts = file.getOriginalFilename().split("\\.");
                String fileName = firstName+"_"+lastName+"_cv."+filenameDotParts[filenameDotParts.length-1];
                try {
                    if (!file.isEmpty()) {

                        InputStream is = file.getInputStream();

                        Files.copy(is, Paths.get(cvFilePath + fileName),
                                StandardCopyOption.REPLACE_EXISTING);

                    }
                } catch (IOException e) {
                        e.printStackTrace();
                }
                userFound.setCv("http://localhost/files/cvFiles/"+fileName);
                userRepository.save(userFound);
                return userFound;
            } else {
                return null;
            }
        }

		/*
		Private local functions
		 */

		private boolean profileImageExists(String firstName, String lastName) {
    File dir = new File(profileImagePath);
    File[] matches = dir.listFiles(((dir1, name) ->
            name.startsWith(firstName+"_"+lastName)));
    return matches.length > 0;
  }

  private void deleteProfileImageFile(String firstName, String lastName) {
    File dir = new File(profileImagePath);
    File[] matches = dir.listFiles(((dir1, name) ->
            name.startsWith(firstName+"_"+lastName)));
    for (File file : matches) {
      file.delete();
    }
  }
    private boolean cvFileExists(String firstName, String lastName) {
        File dir = new File(cvFilePath);
        File[] matches = dir.listFiles(((dir1, name) ->
                name.startsWith(firstName+"_"+lastName+"_cv")));
        return matches.length > 0;
    }

    private void deleteCvFile(String firstName, String lastName) {
        File dir = new File(cvFilePath);
        File[] matches = dir.listFiles(((dir1, name) ->
                name.startsWith(firstName+"_"+lastName+"_cv")));
        for (File file : matches) {
            file.delete();
        }
    }
}
