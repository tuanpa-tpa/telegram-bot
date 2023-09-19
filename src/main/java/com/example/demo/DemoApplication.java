package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.repo.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(DemoApplication.class, args);
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new MyAmazingBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    @Bean
    public CommandLineRunner commandLineRunner(
            UserRepository userRepository
    ) {
        return args -> {
            var admin = User.builder()
                    .firstname("Admin")
                    .lastname("Admin")
                    .email("admin@gmail.com")
                    .isActive(true)
                    .phoneNumber("012356789")
                    .age(22)
                    .build();
            userRepository.save(admin);

            var manager = User.builder()
                    .firstname("Manager")
                    .lastname("Manager")
                    .email("manager@gmail.com")
                    .isActive(true)
                    .phoneNumber("012356789")
                    .age(22)
                    .build();
            userRepository.save(manager);

            var user1 = User.builder()
                    .firstname("Student")
                    .lastname("A")
                    .email("a@gmail.com")
                    .isActive(true)
                    .phoneNumber("012356789")
                    .age(22)
                    .build();
            userRepository.save(user1);

            var user2 = User.builder()
                    .firstname("Teacher")
                    .lastname("B")
                    .email("b@gmail.com")
                    .isActive(true)
                    .phoneNumber("012356789")
                    .age(22)
                    .build();
            userRepository.save(user2);
        };
    }

}
