package br.com.alura.comex;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class ComexApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComexApplication.class, args);
    }

}
