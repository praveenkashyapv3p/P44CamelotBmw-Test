package com.camelot.p44camelotbmw.db.repository;

import com.camelot.p44camelotbmw.db.model.BmwCreateShipmentModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BmwCreateShipmentRepository extends MongoRepository<BmwCreateShipmentModel, String> {
    
    List<BmwCreateShipmentModel> findByBookingNumber(String bookingNumber);
    
    List<BmwCreateShipmentModel> findByBillOfLading(String billOfLading);
    
}