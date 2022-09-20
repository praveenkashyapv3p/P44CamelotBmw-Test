package com.camelot.p44camelotbmw.p44JsonMapper;

import com.camelot.p44camelotbmw.entity.toBmwEntity.ContainerDimension;
import com.camelot.p44camelotbmw.entity.toBmwEntity.P44ToBmw;
import com.google.gson.JsonObject;

public class ContainerDimensionsMapper {
    public void mapContainerDimension(JsonObject shipmentJson, P44ToBmw bmwMapping) {
        ContainerDimension containerDimension = new ContainerDimension();
        String totalWeight = "", totalVolume = "", totalVolumeUnit = "", totalWeightUnit = "";
        
        String ShipIdent = shipmentJson.get("shipment").getAsJsonObject().get("id").getAsString();
        
        
        containerDimension.setTotalVolume(totalVolume);
        containerDimension.setTotalVolumeUnit(totalVolumeUnit);
        containerDimension.setTotalWeight(totalWeight);
        containerDimension.setTotalWeightUnit(totalWeightUnit);
        
        bmwMapping.setContainerDimension(containerDimension);
    }
}