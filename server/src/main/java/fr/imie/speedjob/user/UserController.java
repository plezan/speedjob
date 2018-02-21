package fr.imie.speedjob.user;

import fr.imie.speedjob.SpeedjobApplication;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.Properties;

@RestController
@RequestMapping("/users")
class UserController {
  private static final Logger log = LoggerFactory.getLogger(SpeedjobApplication.class);

  @Autowired
  private UserRepository userRepository;
  private BCryptPasswordEncoder bCryptPasswordEncoder;
		private String profileImagePath;

  public UserController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.userRepository = userRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    final Properties properties = new Properties();
    try {
      ClassLoader classloader = Thread.currentThread().getContextClassLoader();
      InputStream is = classloader.getResourceAsStream("config.properties");
      properties.load(is);
      profileImagePath = properties.getProperty("config.path.profileImages");
    } catch (Exception e) {
      profileImagePath = null;
      e.printStackTrace();
    }
  }
  // GET verbs

  // All users
  @RequestMapping(value = "/", produces = "application/json", method = RequestMethod.GET)
  public List<User> findUsers() {
  		return userRepository.findAll();
  }

  // POST verbs

  // A user
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody String addUser(@RequestParam String firstName, @RequestParam String lastName,
																																						@RequestParam String mail, @RequestParam String password) {
  		if (userRepository.countByMail(mail) == 0) {
						User user = new User();
						user.setFirstName(firstName);
						user.setLastName(lastName);
						user.setMail(mail);
						user.setPassword(bCryptPasswordEncoder.encode(password));
						userRepository.save(user);
						return "Saved";
				} else {
  				return "Mail already used";
				}
  }

  @RequestMapping(value = "/getProfileImage", method = RequestMethod.GET)
		public ResponseEntity<byte[]> getProfileImage(@RequestParam Long id) throws IOException {
				User user = userRepository.getOne(id);
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
        return ResponseEntity
                .ok()
                .body(bytes);
      }
				} else {
						return ResponseEntity
														.notFound()
														.build();
				}
  }

		@RequestMapping(value = "/updateProfileImage", method = RequestMethod.PUT)
		public @ResponseBody String updateProfileImage(@RequestParam Long id, @RequestParam("profileImage") MultipartFile profileImage) {
  		User user = userRepository.getOne(id);
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

		private boolean profileImageExists(String firstName, String lastName) {
    File dir = new File(profileImagePath);
    File[] matches = dir.listFiles(((dir1, name) ->
            name.startsWith(firstName+"_"+lastName)));
    if (matches.length > 0)
      return true;
    else
      return false;
  }

  private void deleteProfileImageFile(String firstName, String lastName) {
    File dir = new File(profileImagePath);
    File[] matches = dir.listFiles(((dir1, name) ->
            name.startsWith(firstName+"_"+lastName)));
    for (File file : matches) {
      file.delete();
    }
  }
}
