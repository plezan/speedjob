package fr.imie.speedjob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpeedjobApplication {
		public static void main(String[] args) {
		SpringApplication.run(SpeedjobApplication.class, args);
	}

		private static final Logger log = LoggerFactory.getLogger(SpeedjobApplication.class);

		@Bean
		public BCryptPasswordEncoder bCryptPasswordEncoder() {
				return new BCryptPasswordEncoder();
		}
}
