package com.camelot.p44camelotbmw.db.repository;

import com.camelot.p44camelotbmw.db.model.BmwCreateShipmentModel;

import java.util.List;

public interface BmwCreateShipmentRepository {
    
    List<BmwCreateShipmentModel> findByBookingNumber(String bookingNumber);
    
    List<BmwCreateShipmentModel> findByBillOfLading(String billOfLading);
    
    BmwCreateShipmentModel updateUsingFindAndReplace(String isExistingShipmentId, BmwCreateShipmentModel newShipment);
    
    BmwCreateShipmentModel save(BmwCreateShipmentModel city);
}