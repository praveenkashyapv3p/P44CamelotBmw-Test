package com.camelot.p44camelotbmw.p44JsonMapper;

import com.camelot.p44camelotbmw.entity.toBmwEntity.ContainerDimension;
import com.camelot.p44camelotbmw.entity.toBmwEntity.P44ToBmw;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ContainerDimensionsMapper {
    public void mapContainerDimension(JsonObject shipmentJson, P44ToBmw bmwMapping) {
        ContainerDimension containerDimension = new ContainerDimension();
        String totalWeight = "", totalVolume = "", totalVolumeUnit = "", totalWeightUnit = "";
    
        if (shipmentJson.get("shipment").getAsJsonObject().has("attributes")) {
            JsonArray attributesList = (JsonArray) shipmentJson.get("shipment").getAsJsonObject().get("attributes");
            for (JsonElement attributes : attributesList) {
                if ("Container net volume".equalsIgnoreCase(attributes.getAsJsonObject().get("name").getAsString())) {
                    JsonElement value = attributes.getAsJsonObject().get("values");
                    totalVolume = value.getAsJsonArray().get(0).getAsString();
                    totalVolumeUnit = "CBM";
                }
                if ("Container net weight".equalsIgnoreCase(attributes.getAsJsonObject().get("name").getAsString())) {
                    JsonElement value = attributes.getAsJsonObject().get("values");
                    totalWeight = value.getAsJsonArray().get(0).getAsString();
                    totalWeightUnit = "KG";
                }
            }
        }
    
        containerDimension.setTotalVolume(totalVolume);
        containerDimension.setTotalVolumeUnit(totalVolumeUnit);
        containerDimension.setTotalWeight(totalWeight);
        containerDimension.setTotalWeightUnit(totalWeightUnit);
    
        bmwMapping.setContainerDimension(containerDimension);
    }
}