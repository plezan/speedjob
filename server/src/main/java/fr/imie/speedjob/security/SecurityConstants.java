package fr.imie.speedjob.security;

public class SecurityConstants {
  public static final String SECRET = "SecretKeyToGenJWTs";
  public static final long EXPIRATION_TIME = 150_000_000;
  public static final String TOKEN_PREFIX = "JWT_Speed_Job ";
  public static final String HEADER_STRING = "Authorization";
  public static final String SIGN_UP_URL = "/users/add";
}
