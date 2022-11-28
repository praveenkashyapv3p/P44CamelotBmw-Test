package com.camelot.p44camelotbmw.db.repository;

import com.camelot.p44camelotbmw.db.model.BmwCreateShipmentModel;
import org.springframework.data.mongodb.core.FindAndReplaceOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BmwCreateShipmentRepositoryImpl implements BmwCreateShipmentRepository {
    
    private final MongoTemplate mongoTemplate;
    
    public BmwCreateShipmentRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    
    @Override
    public List<BmwCreateShipmentModel> findByBookingNumber(String bookingNumber) {
        Query query = new Query().addCriteria(Criteria.where("bookingNumber").is(bookingNumber));
        return mongoTemplate.find(query, BmwCreateShipmentModel.class);
    }
    
    @Override
    public List<BmwCreateShipmentModel> findByBillOfLading(String billOfLading) {
        Query query = new Query().addCriteria(Criteria.where("billOfLading").is(billOfLading));
        return mongoTemplate.find(query, BmwCreateShipmentModel.class);
    }
    
    @Override
    public BmwCreateShipmentModel updateUsingFindAndReplace(String isExistingShipmentId, BmwCreateShipmentModel newShipment) {
        Query query = new Query().addCriteria(Criteria.where("shipmentId").is(isExistingShipmentId));
        FindAndReplaceOptions options = new FindAndReplaceOptions().upsert().returnNew();
        
        return mongoTemplate.findAndReplace(query, newShipment, options, BmwCreateShipmentModel.class, "bmwCreateShipment", BmwCreateShipmentModel.class);
    }
    
    @Override
    public BmwCreateShipmentModel save(BmwCreateShipmentModel bmwCreateShipmentModel) {
        return mongoTemplate.save(bmwCreateShipmentModel);
    }
}