package com.example.RestAPI.BookReview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookReviewService {

private final ReviewRepository repository;

    @Autowired
    public BookReviewService(ReviewRepository repository) {
        this.repository = repository;
    }

    public List<Review> getReviews(){
        return repository.findAll();
    }

    public void addNewReview(Review review) {

        if (review.getBookRating() > -1 && review.getBookRating() < 6 && review.getBookRating() != null) {
            repository.save(review);
        }

        else{
            throw new IllegalStateException("Book Rating Cannot be set to:" + review.getBookRating() +
                    ", Ratings are Between 0-5 Inclusive");
        }
    }

    public void deleteReview(Long studentId) {

        if(!repository.existsById(studentId)){
            throw new IllegalStateException("Review With ID: " + studentId + ", Does Not Exist");
        }
        repository.deleteById(studentId);
    }

    @Transactional
    public void updateReview(Long studentId, String bookReview, Integer bookRating) {

        Review review = repository.findById(studentId).orElseThrow(() -> new IllegalStateException(
                    "Review With ID: " + studentId + ", Does Not Exist"));

        if(bookReview != null){
            if(!Objects.equals(bookReview, review.getBookReview())){
                review.setBookReview(bookReview);
            }
            else{
                throw new IllegalStateException("Book Review Cannot be set to:" + bookReview +
                        ", Review is already set to this.");
            }
        }

        if(bookRating != null){
            if(bookRating < 6 && bookRating > -1){
                review.setBookRating(bookRating);
            }
            else{
                throw new IllegalStateException("Book Rating Cannot be set to:" + bookReview +
                        ", Ratings must be between 0-5 Inclusive.");
            }
        }
    }
}
