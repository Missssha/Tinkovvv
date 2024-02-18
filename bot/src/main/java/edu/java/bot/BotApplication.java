package edu.java.bot;

<<<<<<< HEAD
import edu.java.bot.configurations.ApplicationConfig;
=======
import edu.java.bot.configuration.ApplicationConfig;
>>>>>>> origin/hw1
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfig.class)
public class BotApplication {
    public static void main(String[] args) {
        SpringApplication.run(BotApplication.class, args);
    }
}
