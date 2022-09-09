package com.camelot.p44camelotbmw.p44JsonMapper;

import com.camelot.p44camelotbmw.db.CreateShipment;
import com.camelot.p44camelotbmw.db.CreateShipmentRepository;
import com.camelot.p44camelotbmw.entity.toBmwEntity.BMWMapping;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class MaterialMapper {
    public void mapMaterial(CreateShipmentRepository createShipmentRepository, JsonObject shipmentJson, BMWMapping bmwMapping) {
        List<JsonArray> materialList = new ArrayList<>();
        String ShipIdent = shipmentJson.get("shipment").getAsJsonObject().get("id").getAsString();
        List<CreateShipment> materialsList = createShipmentRepository.findByMasterShipmentId(ShipIdent);
        JsonArray materialsJSON = new JsonArray();
        String materials = "";
        for (CreateShipment carrierIdFromDB : materialsList) {
            materials = carrierIdFromDB.getMaterials().replace("\\", "");
            materialsJSON = (JsonArray) JsonParser.parseString(materials);
        }
        materialList.add(materialsJSON);
        bmwMapping.setMaterials(materialList);
    }
}