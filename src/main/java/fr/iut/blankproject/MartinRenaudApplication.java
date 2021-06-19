package fr.iut.blankproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class MartinRenaudApplication {
    public static void main(String[] args) {
        SpringApplication.run(MartinRenaudApplication.class, args);
    }
}
