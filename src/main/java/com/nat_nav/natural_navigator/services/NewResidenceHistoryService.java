package com.nat_nav.natural_navigator.services;

import com.nat_nav.natural_navigator.entity.ResidenceHistory;
import com.nat_nav.natural_navigator.repositories.ResidenceHistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NewResidenceHistoryService {
    public final ResidenceHistoryRepository residenceHistoryRepository;

    public NewResidenceHistoryService(ResidenceHistoryRepository residenceHistoryRepository) {
        this.residenceHistoryRepository = residenceHistoryRepository;
    }
    @Transactional
    public void add_new(ResidenceHistory residenceHistory){
        residenceHistoryRepository.save(residenceHistory);
    }
}
