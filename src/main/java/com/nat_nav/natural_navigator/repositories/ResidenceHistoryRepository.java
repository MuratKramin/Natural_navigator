package com.nat_nav.natural_navigator.repositories;

import com.nat_nav.natural_navigator.entity.ResidenceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Repository
public interface ResidenceHistoryRepository extends JpaRepository<ResidenceHistory,Integer> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO residence_history" +
            "(check_in_date, check_out_date, total_cost, review, grade, id_user, id_hotel)" +
            "VALUES((?1), (?2), (?3), (?4), (?5), (?6), (?7));",nativeQuery = true)
    void insert(Date CheckInDate, Date CheckOutDate, double TotalCost, String Review, Integer Grade, int Users_rev, int Hotel_rev);

    @Query(value = "select id_user,id_hotel,sum(grade) as gr from residence_history " +
            "group by id_user,id_hotel ;",nativeQuery = true)
    public List<Object[]> findRatings();

    /*@Modifying
    @Query(value = "INSERT INTO residence_history" +
            "(check_in_date, check_out_date, total_cost, review, grade, id_user, id_hotel)" +
            "VALUES(, '', 0, '', 0, 0, 0);",nativeQuery = true)
    void insert(ResidenceHistory residenceHistory);*/

}
