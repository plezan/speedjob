package fr.imie.speedjob.services;

import fr.imie.speedjob.models.Image;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Properties;

@Service
public class FileService {
  private String profileImagePath;
  private String logoImagePath;
  private String cvPath;

  public FileService() {
    final Properties properties = new Properties();
    try {
      ClassLoader classloader = Thread.currentThread().getContextClassLoader();
      InputStream is = classloader.getResourceAsStream("config.properties");
      properties.load(is);
      profileImagePath = properties.getProperty("config.path.profileImages");
      logoImagePath = properties.getProperty("config.path.logoImages");
      cvPath = properties.getProperty("config.path.cvImages");
    } catch (Exception e) {
      profileImagePath = null;
      logoImagePath = null;
      cvPath = null;
      e.printStackTrace();
    }
  }

  /**
   * Get a profile image of a user
   * @param firstName
   * @param lastName
   * @return
   * @throws Exception
   */
  public Image getProfileImage(String firstName, String lastName) throws Exception {
    File dir = new File(profileImagePath);
    File[] matches = dir.listFiles(((dir1, name) ->
      name.startsWith(firstName+"_"+lastName)));
    assert matches != null;
    if (matches.length == 0 || matches.length > 1) {
      throw new Exception("File not found or duplicated.");
    } else {
      Image image = new Image();

      // Get extension
      String[] filenameDotParts = matches[0].getName().split("\\.");
      try {
        image.setExtension(filenameDotParts[filenameDotParts.length-1]);
      } catch (Exception e) {
        e.printStackTrace();
      }


      // Get full name
      StringBuilder filename = new StringBuilder();
      for (int i = 0; i < filenameDotParts.length-1; i++) {
        filename.append(filenameDotParts[i]);
      }
      image.setName(filename.toString());

      // Get bytes
      image.setBytes(StreamUtils.copyToByteArray(new FileInputStream(matches[0])));

      // Get file
      image.setFile(matches[0]);

      return image;
    }
  }

  /**
   *
   * @param multipartFile
   * @param firstName
   * @param lastName
   * @return
   */
  public Image updateProfileImage(
    MultipartFile multipartFile,
    String firstName,
    String lastName
  ) {
    Image image = new Image();
    try {
      InputStream inputStream;
      OutputStream outputStream;

      // Check existance of a profile image, if so delete it
      if (profileImageExists(firstName, lastName))
        deleteProfileImageFile(firstName, lastName);

      // Get extension of uploaded file and locally create it
      String[] filenameDotParts = multipartFile.getOriginalFilename().split("\\.");
      image.setName(firstName+"_"+lastName);
      if (!image.setExtension(filenameDotParts[filenameDotParts.length-1]))
        throw new Exception("Extension is not valid for an image.");
      File file = new File(profileImagePath+"/"+image.getName()+"."+image.getExtension());

      try {
        inputStream = multipartFile.getInputStream();

        if (!file.exists())
          file.createNewFile();

        outputStream = new FileOutputStream(file);
        int read = 0;
        byte[] bytes = new byte[1024];

        while ((read = inputStream.read(bytes)) != -1) {
          outputStream.write(bytes, 0, read);
        }
        outputStream.close();
        inputStream.close();

        image.setBytes(bytes);
      } catch (IOException e) {
        e.printStackTrace();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return image;
  }

  /**
   *
   * @param firstName
   * @param lastName
   * @return
   */
  private boolean profileImageExists(String firstName, String lastName) {
    File dir = new File(profileImagePath);
    File[] matches = dir.listFiles(((dir1, name) ->
      name.startsWith(firstName+"_"+lastName)));
    return matches != null && matches.length > 0;
  }

  /**
   *
   * @param firstName
   * @param lastName
   */
  private Boolean deleteProfileImageFile(String firstName, String lastName) {
    File dir = new File(profileImagePath);
    File[] matches = dir.listFiles(((dir1, name) ->
      name.startsWith(firstName+"_"+lastName)));
    assert matches != null;
    return matches[0].delete();
  }
}