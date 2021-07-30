package com.example.RestAPI.BookReview;

import java.util.Comparator;

public class ReviewComparator implements Comparator {
    public ReviewComparator() {
    }

    public int compare(Object o1, Object o2) {
        Review book1 = (Review)o1;
        Review book2 = (Review)o2;
        if (book1.getAverageRating() < book2.getAverageRating()) {
            return 1;
        } else {
            return book1.getAverageRating() > book2.getAverageRating() ? -1 : 0;
        }
    }
}
