package com.camelot.p44camelotbmw.jsonmapper;

import com.camelot.p44camelotbmw.bmwentity.BMWMapping;
import com.camelot.p44camelotbmw.bmwentity.ContainerDimensions;

public class ContainerDimensionsMapper {
    public void mapContainerDimensions(String shipmentJson, BMWMapping bmwMapping) {
        ContainerDimensions containerDimensions = new ContainerDimensions();
        String totalWeightKGS = "", totalVolumeCBM = "";
        
        containerDimensions.setTotalVolumeCBM(totalVolumeCBM);
        containerDimensions.setTotalWeightKGS(totalWeightKGS);
        
        bmwMapping.setContainerDimensions(containerDimensions);
    }
}