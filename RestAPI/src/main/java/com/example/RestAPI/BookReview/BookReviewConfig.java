package com.example.RestAPI.BookReview;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BookReviewConfig {
        @Bean
        CommandLineRunner commandLineRunner(ReviewRepository repository) {
            return args -> {
                Review ziad = new Review("hello", 9);
                Review huda = new Review("hj", 9);
                repository.saveAll(List.of(ziad, huda, ziad)
                );
            };
        }
    }



