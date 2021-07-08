package com.example.RestAPI.BookReview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/bookie")
public class BookReviewController {

    private final BookReviewService service;

    @Autowired
    public BookReviewController(BookReviewService service) {
        this.service = service;
    }

    @GetMapping
    public List<BookReview> bookReviews(){

        return service.getReviews();

    }

}
