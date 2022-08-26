package com.camelot.p44camelotbmw.db;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SenderPlantCodeRepository extends MongoRepository<SenderPlantCode, String> {
    List<SenderPlantCode> findByPackingPlantCodeAndPackingPlantName(String plantCode, String plantName);
}