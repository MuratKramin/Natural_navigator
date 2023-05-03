package com.nat_nav.natural_navigator.repositories;

import com.nat_nav.natural_navigator.entity.ResidenceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidenceHistoryRepository extends JpaRepository<ResidenceHistory,Integer> {
}
