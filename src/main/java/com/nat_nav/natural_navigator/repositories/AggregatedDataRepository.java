/*
package com.nat_nav.natural_navigator.repositories;

import com.nat_nav.natural_navigator.entity.AggregatedData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AggregatedDataRepository extends JpaRepository<AggregatedData,Integer> {
    @Query(value = "select id_user,id_hotel,sum(grade) as gr from residence_history " +
            "group by id_user,id_hotel ;",nativeQuery = true)
    public List<AggregatedData> findRatings();
}
*/
