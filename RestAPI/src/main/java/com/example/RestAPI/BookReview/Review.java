
package com.example.RestAPI.BookReview;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
    @Column(
            name = "Title"
    )
    private String bookTitle;
    private String bookReview;
    private String timeStamp;
    private Double averageRating;
    private Integer numberOfRatings = 0;
    private Integer bookRating;

    public Review() {
        this.timeStamp = (new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).
                format(Calendar.getInstance().getTime());
    }

    public Review(String bookTitle, String bookReview, Integer bookRating) {
        this.bookTitle = bookTitle;
        this.bookReview = bookReview;
        this.timeStamp = (new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).
                format(Calendar.getInstance().getTime());
        this.averageRating = (double)bookRating;
        this.numberOfRatings = this.numberOfRatings + 1;
        this.bookRating = bookRating;
    }

    public Review(String bookTitle, String bookReview) {
        this.bookTitle = bookTitle;
        this.bookReview = bookReview;
        this.timeStamp = (new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).
                format(Calendar.getInstance().getTime());
    }

    public Review(String bookTitle, Integer bookRating) {
        this.bookTitle = bookTitle;
        this.timeStamp = (new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).
                format(Calendar.getInstance().getTime());
        this.averageRating = (double)bookRating;
        this.numberOfRatings = this.numberOfRatings + 1;
        this.bookRating = bookRating;
    }

    public Integer getBookRating() {
        return this.bookRating;
    }

    public void setBookRating(Integer bookRating) { this.bookRating=bookRating; }

    public String getBookTitle() {
        return this.bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookReview() {
        return this.bookReview;
    }

    public void setBookReview(String bookReview) {
        this.bookReview = bookReview;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void addRating(Integer Rating) {
        this.averageRating = ((this.averageRating * this.numberOfRatings) + Rating) / ++this.numberOfRatings;
    }

    public Double getAverageRating() {
        return this.averageRating == null ? 0.0D : this.averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public Integer getNumberOfRatings() {
        return this.numberOfRatings;
    }

    public void setNumberOfRatings(Integer numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    public void calculateNewAverage(Integer bookRating){
        Double Total = (this.averageRating * this.numberOfRatings)-(this.bookRating)+(bookRating);
        System.out.println(Total);
        Double newAverage = (Total/this.numberOfRatings);
        System.out.println(newAverage);
        setAverageRating(newAverage);
    }

    public void calculateNewAverageWithNull(Integer bookRating){
        Double Total = (this.averageRating * this.numberOfRatings)-(this.bookRating)+(bookRating);
        Double newAverage = (Total/++this.numberOfRatings);
        setAverageRating(newAverage);
    }

    public void calculateNewAverageWithDelete(Integer bookRating){
        Double Total = (this.averageRating * this.numberOfRatings)-(this.bookRating)+(bookRating);
        Double newAverage = (Total/--this.numberOfRatings);
        setAverageRating(newAverage);
    }

    public String toString() {
        return "Review{id=" + this.id + ", bookTitle='" + this.bookTitle + "', bookReview='" +
                this.bookReview + "', timeStamp='" + this.timeStamp + "', averageRating=" +
                this.averageRating + ", numberOfRatings=" + this.numberOfRatings
                + ", bookRating=" + this.bookRating + "}";
    }
}
