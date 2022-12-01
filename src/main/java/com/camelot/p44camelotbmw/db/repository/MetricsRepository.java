package com.camelot.p44camelotbmw.db.repository;

import com.camelot.p44camelotbmw.db.model.MetricsModel;

import java.util.List;

public interface MetricsRepository {
    
    List<MetricsModel> findByDate(String date);
    
    void upsert(MetricsModel newShipment);
}