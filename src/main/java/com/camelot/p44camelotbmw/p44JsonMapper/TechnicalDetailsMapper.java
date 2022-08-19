//package com.camelot.p44camelotbmw.p44JsonMapper;
//
//import com.camelot.p44camelotbmw.entity.toBmwEntity.BMWMapping;
//import com.camelot.p44camelotbmw.entity.toBmwEntity.TechnicalDetail;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class TechnicalDetailsMapper {
//
//    public void mapTechnicalDetails(String corRelId, BMWMapping bmwMapping) {
//        TechnicalDetail technicalDetail = new TechnicalDetail();
//        String lifecycleStatus = "", lifecycleStatusVerbose = "", correlationId = "";
//        List<TechnicalDetail> techDet = new ArrayList<>();
//        correlationId = corRelId;
//        technicalDetail.setCorrelationId(correlationId);
//        technicalDetail.setLifecycleStatus(lifecycleStatus);
//        technicalDetail.setLifecycleStatusVerbose(lifecycleStatusVerbose);
//        techDet.add(technicalDetail);
//        bmwMapping.setTechnicalDetails(techDet);
//    }
//
//}