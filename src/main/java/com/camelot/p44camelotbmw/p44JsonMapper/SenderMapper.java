package com.camelot.p44camelotbmw.p44JsonMapper;

import com.camelot.p44camelotbmw.entity.toBmwEntity.P44ToBmw;
import com.camelot.p44camelotbmw.entity.toBmwEntity.Sender;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class SenderMapper {
    public void mapSender(JsonObject shipmentJson, P44ToBmw bmwMapping) {
        String senderId = "", senderName = "";
        Sender sender = new Sender();
        if (shipmentJson.get("shipment").getAsJsonObject().has("attributes")) {
            JsonArray attributesList = (JsonArray) shipmentJson.get("shipment").getAsJsonObject().get("attributes");
            for (JsonElement attributes : attributesList) {
                if ("Packing plant code".equalsIgnoreCase(attributes.getAsJsonObject().get("name").getAsString())) {
                    JsonElement value = attributes.getAsJsonObject().get("values");
                    senderId = value.getAsJsonArray().get(0).getAsString();
                }
                if ("Packing plant name".equalsIgnoreCase(attributes.getAsJsonObject().get("name").getAsString())) {
                    JsonElement value = attributes.getAsJsonObject().get("values");
                    senderName = value.getAsJsonArray().get(0).getAsString();
                }
            }
        }
        sender.setId(senderId);
        sender.setName(senderName);
        bmwMapping.setSender(sender);
    }
}