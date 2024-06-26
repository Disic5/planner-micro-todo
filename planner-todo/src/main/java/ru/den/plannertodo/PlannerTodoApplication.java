package ru.den.plannertodo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"ru.den"})
@EnableJpaRepositories(basePackages = {"ru.den.plannertodo"})
public class PlannerTodoApplication {
    public static void main(String[] args) {
        SpringApplication.run(PlannerTodoApplication.class, args);
    }
}
