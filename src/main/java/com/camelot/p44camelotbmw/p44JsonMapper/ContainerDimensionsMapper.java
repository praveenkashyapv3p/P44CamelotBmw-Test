package com.camelot.p44camelotbmw.p44JsonMapper;

import com.camelot.p44camelotbmw.entity.toBmwEntity.BMWMapping;
import com.camelot.p44camelotbmw.entity.toBmwEntity.ContainerDimensions;

public class ContainerDimensionsMapper {
    public void mapContainerDimensions(String shipmentJson, BMWMapping bmwMapping) {
        ContainerDimensions containerDimensions = new ContainerDimensions();
        String totalWeightKGS = "", totalVolumeCBM = "";
        
        containerDimensions.setTotalVolumeCBM(totalVolumeCBM);
        containerDimensions.setTotalWeightKGS(totalWeightKGS);
        
        bmwMapping.setContainerDimensions(containerDimensions);
    }
}