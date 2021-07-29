package com.example.RestAPI.BookReview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<Review> bookReviews(){

        return service.getReviews();

    }
    @PostMapping
    public void registerReview(@RequestBody Review review){

        service.addNewReview(review);

    }
    @DeleteMapping(path = "{studentId}")
    public void deleteReview(@PathVariable("studentId") Long studentId){
    service.deleteReview(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateReview(@PathVariable("studentId") Long studentId, @RequestParam(required = false) String bookReview,
                             @RequestParam(required = false) Integer bookRating){

        service.updateReview(studentId, bookReview, bookRating);

    }

}
