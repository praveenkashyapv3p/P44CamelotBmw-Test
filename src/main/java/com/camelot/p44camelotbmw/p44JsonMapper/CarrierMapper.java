package com.camelot.p44camelotbmw.p44JsonMapper;

import com.camelot.p44camelotbmw.entity.toBmwEntity.Carrier;
import com.camelot.p44camelotbmw.entity.toBmwEntity.P44ToBmw;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class CarrierMapper {
    public void mapCarrier(JsonObject shipmentJson, P44ToBmw bmwMapping) {
        String carrierId = "", carrierName = "";
        Carrier carrier = new Carrier();
        if (shipmentJson.get("shipment").getAsJsonObject().has("attributes")) {
            JsonArray attributesList = (JsonArray) shipmentJson.get("shipment").getAsJsonObject().get("attributes");
            for (JsonElement attributes : attributesList) {
                if ("Main haulage carrier".equalsIgnoreCase(attributes.getAsJsonObject().get("name").getAsString())) {
                    JsonElement value = attributes.getAsJsonObject().get("values");
                    carrierId = value.getAsJsonArray().get(0).getAsString();
                }
                if ("Main haulage carrier name".equalsIgnoreCase(attributes.getAsJsonObject().get("name").getAsString())) {
                    JsonElement value = attributes.getAsJsonObject().get("values");
                    carrierName = value.getAsJsonArray().get(0).getAsString();
                }
            }
        }
        carrier.setId(carrierId);
        carrier.setName(carrierName);
        bmwMapping.setCarrier(carrier);
    }
}