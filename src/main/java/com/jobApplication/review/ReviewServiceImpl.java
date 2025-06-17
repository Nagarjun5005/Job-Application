package com.jobApplication.review;


import com.jobApplication.company.Company;
import com.jobApplication.company.CompanyRepository;
import com.jobApplication.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{


   private final ReviewRepository  reviewRepository;

   private final  CompanyRepository companyRepository;

   private final CompanyService companyService;


    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyRepository companyRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyRepository = companyRepository;
        this.companyService = companyService;
    }



    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review>reviewList=reviewRepository.findByCompanyId(companyId);
        return reviewList;
    }


    @Override
    public boolean createReview(Long companyId, Review review) {
      Company company= companyRepository.getCompanyById(companyId);
      if(company!=null){
          review.setCompany(company);
          reviewRepository.save(review);
          return true;
      }
      return false;
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviewList = reviewRepository.findByCompanyId(companyId);
        return reviewList.stream().filter(review->review.getId().equals(reviewId)).findFirst().orElse(null);
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review review) {

        Company company=companyRepository.findById(companyId).orElse(null);
        if(company==null){
            return false;
        }


        //This condition is a safety check:
       //This means the Review we found does not belong to the Company with the provided companyId.

        Review existingReview=reviewRepository.findById(reviewId).orElse(null);
        if(existingReview==null||!existingReview.getCompany().getId().equals(companyId)){
            return false;
        }
        existingReview.setTitle(review.getTitle());
        existingReview.setDescription(review.getDescription());
        existingReview.setRating(review.getRating());
        reviewRepository.save(existingReview);
        return true;


    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        Company company=companyRepository.findById(companyId).orElse(null);
        if(company==null){
            return false;
        }

        Review existingReview=reviewRepository.findById(reviewId).orElse(null);
        if(existingReview==null||!existingReview.getCompany().getId().equals(companyId)){
            return false;
        }

        reviewRepository.delete(existingReview);
        return true;
    }
}
