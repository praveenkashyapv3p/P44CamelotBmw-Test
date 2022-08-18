package com.camelot.p44camelotbmw.db;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreateShipmentRepository extends MongoRepository<CreateShipment, String> {
    List<CreateShipment> findByMasterShipmentId(String s);
}