package commons;

public enum ImageExtensions {
  JPEG("jpeg"),
  JPG("jpg"),
  PNG("png"),
  GIF("gif"),
  PDF("pdf");

  public final String value;

  ImageExtensions(String value) {
    this.value = value;
  }
}