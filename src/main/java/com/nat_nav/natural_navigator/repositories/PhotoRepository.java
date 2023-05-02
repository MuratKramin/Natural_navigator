package com.nat_nav.natural_navigator.repositories;

import com.nat_nav.natural_navigator.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photo,Integer> {
    @Query("select p from Photo p where p.hotel_pic.id=?1")
    List<Photo> findPhotoByHotelId(int id_hotel);
}
