package com.example.RestAPI.BookReview;

import java.util.Comparator;

public class ReviewComparator implements Comparator {
    public ReviewComparator() {
    }
    public int compare(Object o1, Object o2) {
        Review book1 = (Review)o1;
        Review book2 = (Review)o2;
        if(book1.getBookRating() != null && book2.getBookRating() != null){
            if (book1.getBookRating() < book2.getBookRating()) {
                return 1;
            } else {
                return book1.getBookRating() > book2.getBookRating() ? -1 : 0;
            }
        }
        else if ((book1.getBookRating() == null && book2.getBookRating() != null)){
            return 1;
        }
        else if ((book1.getBookRating() != null && book2.getBookRating() == null)){
            return -1;
        }
        else{
            return 0;
        }
    }
}
