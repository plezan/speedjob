package fr.imie.speedjob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.transaction.Transactional;

@SpringBootApplication
public class SpeedjobApplication implements CommandLineRunner {
  public static void main(String[] args) {
    SpringApplication.run(SpeedjobApplication.class, args);
  }

  private static final Logger log = LoggerFactory.getLogger(SpeedjobApplication.class);

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  @Transactional
  public void run(String... strings) throws Exception {
    /* Put your test code here if you're uncertain about new code */
  }
}