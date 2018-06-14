package fr.imie.speedjob.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.imie.speedjob.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;

import static fr.imie.speedjob.security.SecurityConstants.*;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  private AuthenticationManager authenticationManager;

  public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
          throws AuthenticationException {
    try {
      User creds = new ObjectMapper().readValue(request.getInputStream(), User.class);

      return authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
          creds.getMail(),
          creds.getPassword(),
          new ArrayList<>()
        )
      );
    } catch (IOException e){
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
																																										FilterChain chain, Authentication authResult)
          throws IOException, ServletException {
    String token = Jwts.builder()
            .setSubject(((org.springframework.security.core.userdetails.User) authResult.getPrincipal()).getUsername())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
            .compact();
    String fullToken = URLEncoder.encode(TOKEN_PREFIX + token, "UTF-8");
    response.addHeader(HEADER_STRING, fullToken);
    Cookie tokenCookie = new Cookie("token", fullToken);
    tokenCookie.setMaxAge(EXPIRATION_TIME);
    response.addCookie(tokenCookie);
  }
}
