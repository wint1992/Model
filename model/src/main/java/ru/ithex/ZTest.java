package ru.ithex;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import ru.ithex.model.repositories.OrganizationRepository;

@SpringBootApplication
@RequiredArgsConstructor
@Configuration
public class ZTest implements CommandLineRunner {
    private final OrganizationRepository organizationRepository;

    public static void main(String[] args) {
        var app = SpringApplication.run(ZTest.class, args);
    }

    @Override
    public void run(String... strings){
    }
}
