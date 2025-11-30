package gm.rh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point of the RH (Human Resources) Spring Boot application.
 * The {@link SpringBootApplication} annotation enables auto-configuration,
 * component scanning, and Spring Boot's default configuration mechanisms.
 * This class bootstraps the entire application.
 */
@SpringBootApplication
public class RhApplication {

    /**
     * Application entry point.
     * Starts the Spring Boot application by running the embedded server
     * and initializing the Spring context.
     *
     * @param args command-line arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(RhApplication.class, args);
    }
}
