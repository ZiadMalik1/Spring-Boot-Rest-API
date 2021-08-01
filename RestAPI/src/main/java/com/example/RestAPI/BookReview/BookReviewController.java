package com.example.RestAPI.BookReview;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
        path = {"/api/v1/bookie"}
)
public class BookReviewController {
    private final BookReviewService service;

    @Autowired
    public BookReviewController(BookReviewService service) {
        this.service = service;
    }

    @GetMapping
    public List<Review> bookReviews() {
        return this.service.getReviews();
    }

    @GetMapping(path = {"{bookTitle}"})
    public String bookAverage(@PathVariable String bookTitle){
        return "The Book Rating Average for Book: " + bookTitle + "\nAverage Book Rating: " + this.service.getAverage(bookTitle);
    }

    @PostMapping
    public void registerReview(@RequestBody Review review) {
        this.service.addNewReview(review);
    }

    @DeleteMapping(
            path = {"{studentId}"}
    )
    public void deleteReview(@PathVariable("studentId") Long studentId) {
        this.service.deleteReview(studentId);
    }

    @PutMapping(
            path = {"{studentId}"}
    )
    public void updateReview(@PathVariable("studentId") Long studentId, @RequestParam(required = false) String bookReview, @RequestParam(required = false) Integer bookRating) {
        this.service.updateReview(studentId, bookReview, bookRating);
    }
}
