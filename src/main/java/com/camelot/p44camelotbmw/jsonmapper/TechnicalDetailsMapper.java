package com.camelot.p44camelotbmw.jsonmapper;

import com.camelot.p44camelotbmw.bmwentity.BMWMapping;
import com.camelot.p44camelotbmw.bmwentity.TechnicalDetail;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TechnicalDetailsMapper {
    //private final AtomicInteger count = new AtomicInteger();
    TechnicalDetail technicalDetail = new TechnicalDetail();

    public void mapTechnicalDetails(String shipmentJson, BMWMapping bmwMapping) {
        List<TechnicalDetail> techDet = new ArrayList<>();
        //int hitCount = count.incrementAndGet();
        technicalDetail.setCorrelationId(String.valueOf(get64MostSignificantBitsForVersion1()));
        techDet.add(technicalDetail);
        bmwMapping.setTechnicalDetails(techDet);
    }

    public static long get64MostSignificantBitsForVersion1() {
        LocalDateTime start = LocalDateTime.of(1582, 10, 15, 0, 0, 0);
        Duration duration = Duration.between(start, LocalDateTime.now());
        long seconds = duration.getSeconds();
        long nanos = duration.getNano();
        long timeForUuidIn100Nanos = seconds * 10000000 + nanos * 100;
        long least12SignificantBitOfTime = (timeForUuidIn100Nanos & 0x000000000000FFFFL) >> 4;
        long version = 1 << 12;
        return
                (timeForUuidIn100Nanos & 0xFFFFFFFFFFFF0000L) + version + least12SignificantBitOfTime;
    }

}
