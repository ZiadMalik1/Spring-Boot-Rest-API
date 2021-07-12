package com.example.RestAPI.BookReview;

import javax.persistence.*;

@Entity
@Table
public class Review {
    @Id
    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    private Long id;
    private String bookTitle;
    private String bookReview;
    private Integer bookRating;

    public Review() {

    }

    public Review(Long id, String bookTitle, String bookReview, Integer bookRating) {
        this.id = id;
        this.bookTitle = bookTitle;
        this.bookReview = bookReview;
        this.bookRating = bookRating;
    }

    public Review(String bookTitle, Integer bookRating) {

        this.bookTitle = bookTitle;
        this.bookRating = bookRating;
    }


    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookReview() {
        return bookReview;
    }

    public void setBookReview(String bookReview) {
        this.bookReview = bookReview;
    }

    public Integer getBookRating() {
        return bookRating;
    }

    public void setBookRating(Integer bookRating) {
        this.bookRating = bookRating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BookReview{" +
                "bookTitle='" + bookTitle + '\'' +
                ", bookReview='" + bookReview + '\'' +
                ", bookRating=" + bookRating +
                ", id=" + id +
                '}';
    }
}
