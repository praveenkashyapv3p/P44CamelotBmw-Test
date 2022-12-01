package com.camelot.p44camelotbmw.controller;

import com.camelot.p44camelotbmw.db.model.MetricsModel;
import com.camelot.p44camelotbmw.db.repository.MetricsRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MetricsController {
    
    MetricsRepository metricsRepository;
    
    @Autowired
    public MetricsController(MetricsRepository metricsRepository) {
        this.metricsRepository = metricsRepository;
    }
    
    @GetMapping(path = "/test/stats", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getStats(@RequestParam String id) {
        List<MetricsModel> metricsCreate = metricsRepository.findByDate(id);
        return new Gson().toJson(metricsCreate);
    }
    
}