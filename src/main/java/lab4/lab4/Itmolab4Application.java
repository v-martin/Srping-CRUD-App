package lab4.lab4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("lab4.lab4.repo")
@EntityScan("lab4.lab4.entity")
@SpringBootApplication
public class Itmolab4Application {
    public static void main(String[] args) {
        SpringApplication.run(Itmolab4Application.class, args);
    }

}
