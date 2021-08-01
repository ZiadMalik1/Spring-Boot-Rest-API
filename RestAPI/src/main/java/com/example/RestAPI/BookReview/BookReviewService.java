
package com.example.RestAPI.BookReview;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookReviewService {
    private final ReviewRepository repository;

    @Autowired
    public BookReviewService(ReviewRepository repository) {
        this.repository = repository;
    }

    public Double getAverage(String bookTitle){
        List<Review> bookReviews = this.repository.findAll();
        if ((this.repository.findByTitle(bookTitle).size() != 0)) {
            return this.repository.findByTitle(bookTitle).get(0).getAverageRating();
        }
        else{
            throw new IllegalStateException("No Books with Title:" + bookTitle + " Exist");
        }
    }

    public List<Review> getReviews() {
        List<Review> bookReviews = this.repository.findAll();
        ReviewComparator comparator = new ReviewComparator();
        bookReviews.sort(comparator);
        return bookReviews;
    }

    public void addNewReview(Review review) {

        if(review.getBookReview() == null && review.getBookRating() == null){
            throw new IllegalStateException("Must at least Submit either a Comment or a Rating");
        }
        else{
            if(review.getBookRating() != null){
                if (review.getBookRating() > 0 && review.getBookRating() < 6) {
                    if ((this.repository.findByTitle(review.getBookTitle())).size() != 0) {
                        if(review.getBookTitle().equals((this.repository.findByTitle(review.getBookTitle()).get(0)).getBookTitle())){
                            for(int i = this.repository.findByTitle(review.getBookTitle()).size()-1; i > -1; i--) {
                                Review pastBook = this.repository.findByTitle(review.getBookTitle()).get(i);
                                pastBook.addRating(review.getBookRating());
                                this.repository.save(pastBook);
                            }
                            review.setAverageRating(this.repository.findByTitle(review.getBookTitle()).get(0).getAverageRating());
                            review.setNumberOfRatings(this.repository.findByTitle(review.getBookTitle()).get(0).getNumberOfRatings());
                            this.repository.save(review);
                        }
                    }
                    else{
                        System.out.println("Hello");
                        review.addRating(review.getBookRating());
                        this.repository.save(review);
                    }
                }
                else {
                    throw new IllegalStateException("Book Rating Cannot be set to:" + review.getBookRating() + ", Ratings are Between 0-5 Inclusive");
                }
            }
            else{
                if((this.repository.findByTitle(review.getBookTitle())).size() != 0){
                    if (review.getBookTitle().equals(this.repository.findByTitle(review.getBookTitle()).get(0).getBookTitle())) {
                        review.setNumberOfRatings(this.repository.findByTitle(review.getBookTitle()).get(0).getNumberOfRatings());
                        review.setAverageRating(this.repository.findByTitle(review.getBookTitle()).get(0).getAverageRating());
                    }
                }
                this.repository.save(review);
            }
        }
    }
    public void deleteReview(Long studentId) {
        if (!this.repository.existsById(studentId)) {
            throw new IllegalStateException("Review With ID: " + studentId + ", Does Not Exist");
        } else {
            Review review = this.repository.findById(studentId).get();
                if (review.getBookTitle().equals((this.repository.findByTitle(review.getBookTitle()).get(0)).getBookTitle()) && review.getBookRating() != null) {
                    review.calculateNewAverageWithDelete(0);
                    for (int i = this.repository.findByTitle(review.getBookTitle()).size() - 1; i > -1; i--) {
                        Review pastBook = this.repository.findByTitle(review.getBookTitle()).get(i);
                        pastBook.setNumberOfRatings(review.getNumberOfRatings());
                        pastBook.setAverageRating(review.getAverageRating());
                        this.repository.save(pastBook);
                    }
            }this.repository.deleteById(studentId);
        }
    }

    @Transactional
    public void updateReview(Long studentId, String bookReview, Integer bookRating) {
        Review review = this.repository.findById(studentId).orElseThrow(() -> new IllegalStateException("Review With ID: " + studentId + ", Does Not Exist"));

        if (bookReview != null && review.getBookReview() != null) {
            if (review.getBookReview().equals(bookReview)) {
                throw new IllegalStateException("Book Review Cannot be set to:" + bookReview + ", Review is already set to this.");
            }
        }
        if (bookRating != null) {
            if (bookRating >= 6 || bookRating <= -1) {
                throw new IllegalStateException("Book Rating Cannot be set to:" + bookRating + ", Ratings must be between 0-5 Inclusive.");
            }
            else {
                if (review.getBookTitle().equals((this.repository.findByTitle(review.getBookTitle()).get(0)).getBookTitle()) && review.getBookRating() != null) {
                    review.calculateNewAverage(bookRating);
                    review.setBookRating(bookRating);
                    for (int i = this.repository.findByTitle(review.getBookTitle()).size() - 1; i > -1; i--) {
                        Review pastBook = this.repository.findByTitle(review.getBookTitle()).get(i);
                        pastBook.setAverageRating(review.getAverageRating());
                        this.repository.save(pastBook);
                    }
                }
                else if (review.getBookTitle().equals((this.repository.findByTitle(review.getBookTitle()).get(0)).getBookTitle()) && review.getBookRating() == null) {
                    review.setBookRating(0);
                    review.calculateNewAverageWithNull(bookRating);
                    review.setBookRating(bookRating);
                    for (int i = this.repository.findByTitle(review.getBookTitle()).size() - 1; i > -1; i--) {
                        Review pastBook = this.repository.findByTitle(review.getBookTitle()).get(i);
                        pastBook.setAverageRating(review.getAverageRating());
                        pastBook.setNumberOfRatings(review.getNumberOfRatings());
                        this.repository.save(pastBook);
                    }
                }
            }
        }
    }
}

