package com.camelot.p44camelotbmw.db.repository;

import com.camelot.p44camelotbmw.db.model.BmwResponseModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BmwResponseRepository extends MongoRepository<BmwResponseModel, String> {
    List<BmwResponseModel> findByResponseCode(String responseCode);
    
    List<BmwResponseModel> findByTimestamp(String timestamp);
}