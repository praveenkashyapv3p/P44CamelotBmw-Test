package com.camelot.p44camelotbmw.db.repository;

import com.camelot.p44camelotbmw.db.model.P44IncomingModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface P44IncomingRepository extends MongoRepository<P44IncomingModel, String> {
    
    List<P44IncomingModel> findByInternalP44Identifier(String internalP44Identifier);
    
    List<P44IncomingModel> findByCause(String cause);
    
    List<P44IncomingModel> findByTimestamp(String timestamp);
}