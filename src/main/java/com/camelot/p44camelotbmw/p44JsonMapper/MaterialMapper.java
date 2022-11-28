package com.camelot.p44camelotbmw.p44JsonMapper;

import com.camelot.p44camelotbmw.entity.toBmwEntity.Material;
import com.camelot.p44camelotbmw.entity.toBmwEntity.P44ToBmw;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaterialMapper {
    public void mapMaterial(JsonObject shipmentJson, P44ToBmw bmwMapping) {
        List<Material> materialList = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        String materialNumber = "", purchaseOrder = "", deliveryNoteNumber = "", p44Quantity = "", bmwQuantity = "", quantityUnit = "";
        String ShipIdent = shipmentJson.get("shipment").getAsJsonObject().get("id").getAsString();
        String url = "https://na12.api.project44.com/api/v4/shipments/" + ShipIdent + "/loads";
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("Camelot_Test@bmwsandbox.com", "c3hx>7U9^Y");
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        String jsonResponse = response.getBody();
        JsonObject shipmentIdJSON = (JsonObject) JsonParser.parseString(jsonResponse);
        if (shipmentIdJSON.getAsJsonObject().get("results").getAsJsonArray().size() > 0) {
            JsonArray itemsArray = (JsonArray) shipmentIdJSON.getAsJsonObject().get("results").getAsJsonArray().get(0).getAsJsonObject().get("items");
            for (JsonElement items : itemsArray) {
                Material material = new Material();
                if (items.getAsJsonObject().has("stockKeepingUnit"))
                    materialNumber = items.getAsJsonObject().get("stockKeepingUnit").getAsString();
                if (items.getAsJsonObject().has("unitQuantity"))
                    p44Quantity = items.getAsJsonObject().get("unitQuantity").getAsString();
                if (items.getAsJsonObject().has("description"))
                    deliveryNoteNumber = items.getAsJsonObject().get("description").getAsString();
                if (items.getAsJsonObject().has("orderIdentifierReferences")) {
                    purchaseOrder = items.getAsJsonObject().get("orderIdentifierReferences").getAsJsonArray().get(0).getAsJsonObject().get("orderType").getAsString();
                    if (purchaseOrder.equalsIgnoreCase("PURCHASE_ORDER")) {
                        purchaseOrder = items.getAsJsonObject().get("orderIdentifierReferences").getAsJsonArray().get(0).getAsJsonObject().get("orderIdentifier").getAsString();
                    }
                }
                material.setMaterialNumber(materialNumber);
                material.setDeliveryNoteNumber(deliveryNoteNumber);
                material.setPurchaseOrder(purchaseOrder);
                material.setBmwQuantity(bmwQuantity);
                material.setQuantityUnit(quantityUnit);
                material.setP44Quantity(p44Quantity);
                materialList.add(material);
            }
        } else {
            JsonArray materialValues = new JsonArray();
            JsonArray purchaseOrderValues = new JsonArray();
            if (shipmentJson.get("shipment").getAsJsonObject().has("attributes")) {
                JsonArray attributesList = (JsonArray) shipmentJson.get("shipment").getAsJsonObject().get("attributes");
                for (JsonElement attributes : attributesList) {
                    if ("Material".equalsIgnoreCase(attributes.getAsJsonObject().get("name").getAsString())) {
                        materialValues = (JsonArray) attributes.getAsJsonObject().get("values");
                    }
                    if ("Purchase order".equalsIgnoreCase(attributes.getAsJsonObject().get("name").getAsString())) {
                        purchaseOrderValues = (JsonArray) attributes.getAsJsonObject().get("values");
                    }
                }
            }
            for (int i = 0; i < materialValues.size(); i++) {
                Material material = new Material();
                material.setMaterialNumber(materialValues.get(i).getAsString());
                material.setDeliveryNoteNumber(deliveryNoteNumber);
                material.setPurchaseOrder(purchaseOrderValues.get(i).getAsString());
                material.setBmwQuantity(bmwQuantity);
                material.setQuantityUnit(quantityUnit);
                material.setP44Quantity(p44Quantity);
                materialList.add(material);
            }
        }
        
        bmwMapping.setMaterials(materialList);
    }
}