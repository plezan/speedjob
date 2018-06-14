package fr.imie.speedjob.models;

import commons.ImageExtensions;

import java.io.File;

public class Image {
  private String name;
  private String extension;
  private byte[] bytes;
  private File file;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getExtension() {
    return extension;
  }

  public Boolean setExtension(String extension) throws Exception {
    String extensionLowercase = extension.toLowerCase();
    if (extensionLowercase.equals(ImageExtensions.JPEG.value)
      || extensionLowercase.equals(ImageExtensions.JPG.value)
      || extensionLowercase.equals(ImageExtensions.PNG.value)
      || extensionLowercase.equals(ImageExtensions.GIF.value)
      || extensionLowercase.equals(ImageExtensions.PDF.value)
      ) {
      this.extension = extensionLowercase;
      return true;
    } else {
      return false;
    }
  }

  public byte[] getBytes() {
    return bytes;
  }

  public void setBytes(byte[] bytes) {
    this.bytes = bytes;
  }

  public File getFile() {
    return file;
  }

  public void setFile(File file) {
    this.file = file;
  }
}
