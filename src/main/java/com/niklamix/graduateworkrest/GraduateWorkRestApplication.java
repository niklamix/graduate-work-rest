package com.niklamix.graduateworkrest;

import com.niklamix.graduateworkrest.configuration.LoggingConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GraduateWorkRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraduateWorkRestApplication.class, args);
	}

	@Bean
	public LoggingConfiguration requestLoggingFilter() {
		LoggingConfiguration loggingFilter = new LoggingConfiguration();
		loggingFilter.setIncludeClientInfo(true);
		loggingFilter.setIncludeQueryString(true);
		loggingFilter.setIncludePayload(true);
		loggingFilter.setIncludeHeaders(true);
		return loggingFilter;
	}
}
