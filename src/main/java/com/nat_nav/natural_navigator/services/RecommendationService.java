package com.nat_nav.natural_navigator.services;

import com.nat_nav.natural_navigator.AlsModel.ALSUtils;
import com.nat_nav.natural_navigator.AlsModel.BatchALS;
import com.nat_nav.natural_navigator.AlsModel.LatentFactors;
import com.nat_nav.natural_navigator.AlsModel.Rating;
import com.nat_nav.natural_navigator.entity.Hotel;
import com.nat_nav.natural_navigator.entity.ResidenceHistory;
import com.nat_nav.natural_navigator.entity.User;
import com.nat_nav.natural_navigator.repositories.HotelRepository;
import com.nat_nav.natural_navigator.repositories.ResidenceHistoryRepository;
import com.nat_nav.natural_navigator.repositories.UserRepository;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SparseRealMatrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class RecommendationService {
    private final HotelRepository hotelRepository;
    private final UserRepository userRepository;
    private final ResidenceHistoryRepository residenceHistory;

    @Autowired
    public RecommendationService(HotelRepository hotelRepository, UserRepository userRepository, ResidenceHistoryRepository residenceHistory) {
        this.hotelRepository = hotelRepository;
        this.userRepository = userRepository;
        this.residenceHistory = residenceHistory;
    }



    public List<Hotel> getRecommendedHotels(int userId){
        List<Hotel> recommendedHotels=new ArrayList<>();

        int n=10;
        int nUsers=userRepository.findAll().size();
        int nItems=hotelRepository.findAll().size();
        int rank = 4;
        double alpha=0.01;
        double beta =0.1;
        List<Rating> ratings=new LinkedList<>();

        for (User user:userRepository.findAll()) {
            for (ResidenceHistory visit:user.getResidenceHistoryList()) {
                ratings.add(new Rating(user.getId(),visit.getHotel_rev().getId(),visit.getGrade()));
            }
        }
        /*ratings.add(new Rating(1,5,4));
        ratings.add(new Rating(2,1,3));
        ratings.add(new Rating(3,2,5));
        ratings.add(new Rating(4,3,3));
        ratings.add(new Rating(5,4,0));*/


        SparseRealMatrix R = ALSUtils.toMatrix(ratings);
        LatentFactors factors = LatentFactors.create(nUsers, nItems, rank);
        BatchALS als = new BatchALS(R, rank, alpha, beta);
        for(int iter = 0 ; iter < n ; iter++) {
            factors = als.run(factors);
        }
        RealMatrix approximation = ALSUtils.approximate(factors);

        System.out.println(approximation);
        System.out.println();
        for (int i=0;i<approximation.getRowDimension();i++){
            for (int j=0;j<approximation.getColumnDimension();j++){
                System.out.print(new BigDecimal(approximation.getEntry(i,j)).round(new MathContext(5, RoundingMode.HALF_UP)));
                System.out.print(" ");
                //recommendedHotels.add(approximation.getEntry());
            }
            System.out.println("");
        }

        List<List<Rating>> ratingsList=new ArrayList<>();
        for(User user: userRepository.findAll()){
            for(ResidenceHistory residenceHistory: user.getResidenceHistoryList()){

            }
        }

        residenceHistory.findRatings();

        for(int i =0;i<approximation.getColumnDimension();i++){
            recommendedHotels.add(hotelRepository.getReferenceById(i));
        }
        //Collections.sort(recommendedHotels,Compa);

        return recommendedHotels;
    }
}
