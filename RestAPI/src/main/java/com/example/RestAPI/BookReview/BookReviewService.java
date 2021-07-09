package com.example.RestAPI.BookReview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookReviewService {

    private final ReviewRepository repository;

    @Autowired
    public BookReviewService(ReviewRepository repository) {
        this.repository = repository;
    }

    public List<BookReview> getReviews(){

        return repository.findAll();
        //HEllo

    }
}
