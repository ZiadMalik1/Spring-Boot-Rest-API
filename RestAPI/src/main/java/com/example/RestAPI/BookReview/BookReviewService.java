package com.example.RestAPI.BookReview;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookReviewService {


    public List<BookReview> getReviews(){

        return List.of(new BookReview("Harry", "Amazing", 9),
                new BookReview("Potter", "Hell5o", 9));

    }
}
