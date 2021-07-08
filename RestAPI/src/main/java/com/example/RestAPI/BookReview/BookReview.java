package com.example.RestAPI.BookReview;

public class BookReview {

    private String bookTitle;
    private String bookReview;
    private Integer bookRating;
    private Long id;

    public BookReview(String bookTitle, String bookReview, Integer bookRating) {
        this.bookTitle = bookTitle;
        this.bookReview = bookReview;
        this.bookRating = bookRating;
    }

    public BookReview(String bookTitle, Integer bookRating) {
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
