package com.camelot.p44camelotbmw.db.repository;

import com.camelot.p44camelotbmw.db.model.MetricsModel;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MetricsRepositoryImpl implements MetricsRepository {
    
    private final MongoTemplate mongoTemplate;
    
    public MetricsRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    
    @Override
    public List<MetricsModel> findByDate(String date) {
        Query query = new Query().addCriteria(Criteria.where("date").is(date));
        return mongoTemplate.find(query, MetricsModel.class);
    }
    
    @Override
    public void upsert(MetricsModel metricsModel) {
        Query query = new Query().addCriteria(Criteria.where("date").is(metricsModel.getDate()));
        Update updateDefinition = new Update();
        updateDefinition.set("date", metricsModel.getDate());
        updateDefinition.set("received", metricsModel.getReceived());
        updateDefinition.set("mapped", metricsModel.getMapped());
        updateDefinition.set("sent", metricsModel.getSent());
        FindAndModifyOptions options = new FindAndModifyOptions().returnNew(true).upsert(true);
        mongoTemplate.findAndModify(query, updateDefinition, options, MetricsModel.class);
    }
}