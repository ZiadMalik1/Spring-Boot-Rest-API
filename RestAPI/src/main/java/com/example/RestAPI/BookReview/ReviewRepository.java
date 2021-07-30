package com.example.RestAPI.BookReview;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("select r from Review r where r.bookTitle = :title")
    List<Review> findByTitle(@Param("title") String title);
}
