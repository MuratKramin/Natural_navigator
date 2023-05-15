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
import java.util.*;

@Service
public class RecommendationService {
    private final HotelRepository hotelRepository;
    private final UserRepository userRepository;
    private final ResidenceHistoryRepository residenceHistoryRepository;

    @Autowired
    public RecommendationService(HotelRepository hotelRepository, UserRepository userRepository, ResidenceHistoryRepository residenceHistoryRepository) {
        this.hotelRepository = hotelRepository;
        this.userRepository = userRepository;
        this.residenceHistoryRepository = residenceHistoryRepository;
    }



    public List<Hotel> getRecommendedHotels(int userId){
        List<Hotel> recommendedHotels=new ArrayList<>();

        int n=100;
        int nUsers=userRepository.findAll().size();
        int nItems= hotelRepository.findAll().size();
        int rank = 8;
        double alpha=0.01;
        double beta =0.01;
        List<Rating> ratings=new ArrayList<>(nUsers);

        /*for (User user:userRepository.findAll()) {
            for (ResidenceHistory visit:user.getResidenceHistoryList()) {
                ratings.add(new Rating(user.getId(),visit.getHotel_rev().getId(),visit.getGrade()));
            }
        }*/
        /*ratings.add(new Rating(1,5,4));
        ratings.add(new Rating(2,1,3));
        ratings.add(new Rating(3,2,5));
        ratings.add(new Rating(4,3,3));
        ratings.add(new Rating(5,4,0));*/

        for(Object[] obj : residenceHistoryRepository.findRatings()){

            System.out.print(obj[0]);
            System.out.print(obj[1]);
            System.out.print(obj[2]);
            ratings.add(new Rating((int) obj[0],(int) obj[1],(Long)obj[2]));

        }


        SparseRealMatrix R = ALSUtils.toMatrix(ratings,nUsers,nItems);
        System.out.println("---");
        System.out.println(R.getColumnDimension());
        System.out.println(R.getRowDimension());
        System.out.println("---");
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

        /*List<List<Rating>> ratingsList=new ArrayList<>();
        for(User user: userRepository.findAll()){
            for(ResidenceHistory residenceHistory: user.getResidenceHistoryList()){

            }
        }*/
        //residenceHistory.findRatings();
        Map<Double,Integer> recMap = new TreeMap<>();

        for(int i =0;i<approximation.getColumnDimension();i++){
            //recommendedHotels.add(hotelRepository.getReferenceById(i));
            recMap.put(approximation.getEntry(userId-1,i),i+1);
        }
        Map<Double,Integer> recMapDesc = new TreeMap<>(recMap).descendingMap();
        System.out.println(recMapDesc);
        //Collections.sort(recommendedHotels,Compa);
        Iterator<Map.Entry<Double,Integer>> iterator=recMapDesc.entrySet().iterator();
        for(int i=0;iterator.hasNext();i++){
            Map.Entry<Double,Integer> pair = iterator.next();
            recommendedHotels.add(hotelRepository.getReferenceById(pair.getValue()));
        }


        return recommendedHotels;
    }
}
