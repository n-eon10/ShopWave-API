package com.afam.shopwave.Configurations;

import com.afam.shopwave.Models.UserModel;
import com.afam.shopwave.Repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository) {
        return args -> {
            UserModel Afam = new UserModel(

                    "Afam",
                    "neonsellsandbuys",
                    "neon123",
                    "neon@gmail.com",
                    LocalDate.of(2005, Month.MAY, 11)
            );

            UserModel Ifaz = new UserModel(

                    "Ifaz",
                    "ifazsellsandbuys",
                    "ifaz123",
                    "ifaz@gmail.com",
                    LocalDate.of(2005, Month.MAY, 11)
            );

            repository.saveAll(
                    List.of(Afam, Ifaz)
            );
        };
    }
}
