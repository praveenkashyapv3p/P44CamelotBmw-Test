package com.camelot.p44camelotbmw.jsonmapper;

import com.camelot.p44camelotbmw.bmwentity.BMWMapping;
import com.camelot.p44camelotbmw.bmwentity.TechnicalDetail;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TechnicalDetailsMapper {
    
    
    public static long get64MostSignificantBitsForVersion1() {
        LocalDateTime start = LocalDateTime.of(1582, 10, 15, 0, 0, 0);
        Duration duration = Duration.between(start, LocalDateTime.now());
        long seconds = duration.getSeconds();
        long nanos = duration.getNano();
        long timeForUuidIn100Nanos = seconds * 10000000 + nanos * 100;
        long least12SignificantBitOfTime = (timeForUuidIn100Nanos & 0x000000000000FFFFL) >> 4;
        long version = 1 << 12;
        return (timeForUuidIn100Nanos & 0xFFFFFFFFFFFF0000L) + version + least12SignificantBitOfTime;
    }
    
    public void mapTechnicalDetails(BMWMapping bmwMapping) {
        TechnicalDetail technicalDetail = new TechnicalDetail();
        String lifecycleStatus = "", lifecycleStatusVerbose = "", correlationId = "";
        List<TechnicalDetail> techDet = new ArrayList<>();
        correlationId = String.valueOf(get64MostSignificantBitsForVersion1());
        technicalDetail.setCorrelationId(correlationId);
        technicalDetail.setLifecycleStatus(lifecycleStatus);
        technicalDetail.setLifecycleStatusVerbose(lifecycleStatusVerbose);
        techDet.add(technicalDetail);
        bmwMapping.setTechnicalDetails(techDet);
    }
    
}