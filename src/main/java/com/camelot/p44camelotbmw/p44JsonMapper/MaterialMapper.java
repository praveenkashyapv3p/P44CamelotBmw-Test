package com.camelot.p44camelotbmw.p44JsonMapper;

import com.camelot.p44camelotbmw.db.CreateShipment;
import com.camelot.p44camelotbmw.db.CreateShipmentRepository;
import com.camelot.p44camelotbmw.entity.toBmwEntity.BMWMapping;
import com.camelot.p44camelotbmw.entity.toBmwEntity.Material;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class MaterialMapper {
    public void mapMaterial(CreateShipmentRepository createShipmentRepository, JsonObject shipmentJson, BMWMapping bmwMapping) {
        
        Material material = new Material();
        List<JsonArray> materialList = new ArrayList<>();
        
        //JsonObject relShipIdentJSON = (JsonObject) JsonParser.parseString(shipmentJson);
        String ShipIdent = shipmentJson.get("shipment").getAsJsonObject().get("id").getAsString();
        List<CreateShipment> materialsList = createShipmentRepository.findByMasterShipmentId(ShipIdent);
        JsonArray materialsJSON = new JsonArray();
        String materials = "";
        for (CreateShipment carrierIdFromDB : materialsList) {
            materials = carrierIdFromDB.getMaterials().replace("\\", "");
            materialsJSON = (JsonArray) JsonParser.parseString(materials);
        }


//        material.setMaterialNumber(carrierIdFromDB.get);
//        material.setPurchaseOrder(carrierIdFromDB.getTotalVolumeCBM());
//        material.setQuantity(carrierIdFromDB.getTotalVolumeCBM());
//        material.setDeliverNoteNumber(carrierIdFromDB.getTotalVolumeCBM());
        materialList.add(materialsJSON);
        bmwMapping.setMaterials(materialList);
    }
}