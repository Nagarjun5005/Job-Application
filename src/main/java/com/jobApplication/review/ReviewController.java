package com.jobApplication.review;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/companies/{companyId}")
public class ReviewController {

    ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){
        List<Review> allReviews = reviewService.getAllReviews(companyId);
        return new ResponseEntity<>(allReviews, HttpStatus.FOUND);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String>createReview(@PathVariable Long companyId,@RequestBody  Review review){
        boolean reviewSaved= reviewService.createReview(companyId, review);
        if(reviewSaved){
            return new ResponseEntity<>("created review successfully",HttpStatus.FOUND);
        }
        else{
            return new ResponseEntity<>("not saved ",HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review>getReview(@PathVariable Long  companyId,@PathVariable Long reviewId){
        Review review = reviewService.getReview(companyId, reviewId);
        return new ResponseEntity<>( review,HttpStatus.OK);
    }


    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String>updateReview(@PathVariable Long companyId,@PathVariable Long reviewId,@RequestBody Review review){
        boolean updateReview = reviewService.updateReview(companyId, reviewId, review);
        if(updateReview){
            return new ResponseEntity<>("Review updated successfully",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("review or company not sound",HttpStatus.NOT_FOUND);

        }
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String>deleteReview(@PathVariable Long companyId,@PathVariable Long reviewId){
        boolean deleteReview= reviewService.deleteReview(companyId,reviewId);
        if(deleteReview){
            return new ResponseEntity<>("deleted successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("review or company not found",HttpStatus.NOT_FOUND);
    }

}
