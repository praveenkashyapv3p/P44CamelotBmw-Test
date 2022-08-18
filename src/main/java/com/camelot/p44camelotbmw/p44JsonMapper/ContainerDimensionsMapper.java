package com.camelot.p44camelotbmw.p44JsonMapper;

import com.camelot.p44camelotbmw.db.CreateShipment;
import com.camelot.p44camelotbmw.db.CreateShipmentRepository;
import com.camelot.p44camelotbmw.entity.toBmwEntity.BMWMapping;
import com.camelot.p44camelotbmw.entity.toBmwEntity.ContainerDimensions;
import com.google.gson.JsonObject;

import java.util.List;

public class ContainerDimensionsMapper {
    public void mapContainerDimensions(CreateShipmentRepository createShipmentRepository, JsonObject shipmentJson, BMWMapping bmwMapping) {
        ContainerDimensions containerDimensions = new ContainerDimensions();
        String totalWeightKGS = "", totalVolumeCBM = "";
        
        String ShipIdent = shipmentJson.get("shipment").getAsJsonObject().get("id").getAsString();
        List<CreateShipment> containerDimensionsList = createShipmentRepository.findByMasterShipmentId(ShipIdent);
        for (CreateShipment carrierIdFromDB : containerDimensionsList) {
            totalWeightKGS = carrierIdFromDB.getTotalWeightKGS();
            totalVolumeCBM = carrierIdFromDB.getTotalVolumeCBM();
        }
        
        containerDimensions.setTotalVolumeCBM(totalVolumeCBM);
        containerDimensions.setTotalWeightKGS(totalWeightKGS);
        
        bmwMapping.setContainerDimensions(containerDimensions);
    }
}