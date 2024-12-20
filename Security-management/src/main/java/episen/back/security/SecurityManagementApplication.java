package episen.back.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"back.models"})
public class SecurityManagementApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SecurityManagementApplication.class, args);
    }
    private static final Logger logger = LoggerFactory.getLogger(SecurityManagementApplication.class);

    @Override
    public void run(String... args) {
        logger.info("Starting application...");

        logger.info("Application finished processing.");
    }
}
