package com.example.RestAPI.BookReview;

import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookReviewConfig {
    public BookReviewConfig() {
    }
    @Bean
    CommandLineRunner commandLineRunner(ReviewRepository repository) {
        return (args) -> {
            Review review1 = new Review("Harry Potter and the Goblet of Fire", "Amazing!", 2);
            Review review2 = new Review("Narnia", "Holy Cow!", 3);
            repository.saveAll(List.of(review1, review2));
        };
    }
}



