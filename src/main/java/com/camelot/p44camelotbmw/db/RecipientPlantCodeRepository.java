package com.camelot.p44camelotbmw.db;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipientPlantCodeRepository extends MongoRepository<RecipientPlantCode, String> {
    List<RecipientPlantCode> findByPlantCodeAndPlantDescription(String plantCode, String plantDescription);
}