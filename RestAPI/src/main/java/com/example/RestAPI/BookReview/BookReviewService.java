
package com.example.RestAPI.BookReview;

import java.util.List;
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

    public List<Review> getReviews() {
        List<Review> bookReviews = this.repository.findAll();
        System.out.println(bookReviews);
        ReviewComparator comparator = new ReviewComparator();
        bookReviews.sort(comparator);
        return bookReviews;
    }

    public void addNewReview(Review review) {
        if (review.getBookRating() > 0 && review.getBookRating() < 6) {
            System.out.println(this.repository.findByTitle(review.getBookTitle()).get(0));
            if (review.getBookTitle().equals(((Review)this.repository.findByTitle(review.getBookTitle()).get(0)).getBookTitle())) {
                System.out.println("hello");

                for(int i = 0; i < this.repository.findByTitle(review.getBookTitle()).size(); ++i) {
                    Review pastBook = (Review)this.repository.findByTitle(review.getBookTitle()).get(i);
                    pastBook.addRating(review.getBookRating());
                    this.repository.save(pastBook);
                }

                review.setAverageRating(((Review)this.repository.findByTitle(review.getBookTitle()).get(0)).getAverageRating());
                review.setNumberOfRatings(((Review)this.repository.findByTitle(review.getBookTitle()).get(0)).getNumberOfRatings());
            }

            this.repository.save(review);
        } else {
            throw new IllegalStateException("Book Rating Cannot be set to:" + review.getBookRating() + ", Ratings are Between 0-5 Inclusive");
        }
    }

    public void deleteReview(Long studentId) {
        if (!this.repository.existsById(studentId)) {
            throw new IllegalStateException("Review With ID: " + studentId + ", Does Not Exist");
        } else {
            this.repository.deleteById(studentId);
        }
    }

    @Transactional
    public void updateReview(Long studentId, String bookReview, Integer bookRating) {
        Review review = (Review)this.repository.findById(studentId).orElseThrow(() -> {
            return new IllegalStateException("Review With ID: " + studentId + ", Does Not Exist");
        });
        if (bookReview != null) {
            if (review.getBookReview().equals(bookReview)) {
                throw new IllegalStateException("Book Review Cannot be set to:" + bookReview + ", Review is already set to this.");
            }

            review.setBookReview(bookReview);
        }

        if (bookRating != null) {
            if (bookRating >= 6 || bookRating <= -1) {
                throw new IllegalStateException("Book Rating Cannot be set to:" + bookReview + ", Ratings must be between 0-5 Inclusive.");
            }

            review.setBookRating(bookRating);
        }

    }
}

