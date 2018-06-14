package fr.imie.speedjob.controllers;

import commons.ImageExtensions;
import fr.imie.speedjob.SpeedjobApplication;
import fr.imie.speedjob.models.Image;
import fr.imie.speedjob.models.User;
import fr.imie.speedjob.services.FileService;
import fr.imie.speedjob.services.UserService;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/users")
class UserController {
  private static final Logger log = LoggerFactory.getLogger(SpeedjobApplication.class);
  private UserService userService;
  private FileService fileService;

  public UserController(UserService userService, FileService fileService) {
    this.userService = userService;
    this.fileService = fileService;
  }

  /*
  GET
   */

  // All users
  @GetMapping(value = "/", produces = "application/json")
  public List<User> findUsers() {
    return userService.getAll();
  }

  // Get profileImage
  @GetMapping(value = "/getProfileImage")
  public ResponseEntity<byte[]> getProfileImage(@RequestParam Long id) throws Exception {
    User user = userService.getOne(id);
    if (user != null && user.getHasProfileImage()) {
      // Get image
      Image image = this.fileService.getProfileImage(user.getFirstName(), user.getLastName());
      if (image.getExtension().equals(ImageExtensions.JPEG.value)
        || image.getExtension().equals(ImageExtensions.JPG.value))
        return ResponseEntity
          .ok()
          .contentType(MediaType.IMAGE_JPEG)
          .body(image.getBytes());
      else if (image.getExtension().equals(ImageExtensions.PNG.value))
        return ResponseEntity
          .ok()
          .contentType(MediaType.IMAGE_PNG)
          .body(image.getBytes());
      else if (image.getExtension().equals(ImageExtensions.GIF.value))
        return ResponseEntity
          .ok()
          .contentType(MediaType.IMAGE_GIF)
          .body(image.getBytes());
    }
    return ResponseEntity
      .noContent()
      .build();
  }

  /*
  POST
   */

  // A user
  @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> addUser(
    @RequestParam String firstName,
    @RequestParam String lastName,
    @RequestParam String mail,
    @RequestParam String password
  ) throws Exception {
    JSONObject result = new JSONObject();
    User user = this.userService.addOne(firstName, lastName, password, mail);
    if (user.getId() != null) {
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
  public ResponseEntity<Object> updateProfileImage(@RequestParam Long id, @RequestParam("profileImage") MultipartFile profileImage) {
    JSONObject result = new JSONObject();
    User user = this.userService.getOne(id);
    if (user != null) {
      try {
        this.userService.updateProfileImage(profileImage, user);
        result.put("status", "success");
        return new ResponseEntity<>(result, HttpStatus.OK);
      } catch (Exception e) {
        result.put("status", "fail");
        result.put("message", "Difficulty to write profile image.");
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
      }
    } else {
      result.put("status", "fail");
      result.put("message", "User doesn't exist");
      return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }
  }

  /*
  DELETE
   */

  @DeleteMapping(value = "/")
  public ResponseEntity<Object> deleteOne(@RequestParam Long id) {
    JSONObject result = new JSONObject();
    User user = this.userService.getOne(id);

    if (user != null) {
      this.userService.deleteOne(user);
      result.put("status", "success");
      return new ResponseEntity<>(result, HttpStatus.OK);
    } else {
      result.put("status", "fail");
      return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }
  }
}
