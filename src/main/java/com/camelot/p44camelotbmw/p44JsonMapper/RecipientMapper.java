package com.camelot.p44camelotbmw.p44JsonMapper;

import com.camelot.p44camelotbmw.entity.toBmwEntity.P44ToBmw;
import com.camelot.p44camelotbmw.entity.toBmwEntity.Recipient;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class RecipientMapper {
    
    public void mapRecipient(JsonObject shipmentJson, P44ToBmw bmwMapping) {
        String recipientId = "", recipientName = "", recipientUnloadingPoint = "";
        Recipient recipient = new Recipient();
        if (shipmentJson.get("shipment").getAsJsonObject().has("attributes")) {
            JsonArray attributesList = (JsonArray) shipmentJson.get("shipment").getAsJsonObject().get("attributes");
            for (JsonElement attributes : attributesList) {
                if ("Plant Code".equalsIgnoreCase(attributes.getAsJsonObject().get("name").getAsString())) {
                    JsonElement value = attributes.getAsJsonObject().get("values");
                    recipientId = value.getAsJsonArray().get(0).getAsString();
                }
                if ("Plant description".equalsIgnoreCase(attributes.getAsJsonObject().get("name").getAsString())) {
                    JsonElement value = attributes.getAsJsonObject().get("values");
                    recipientName = value.getAsJsonArray().get(0).getAsString();
                }
                if ("Plant Unloading Point".equalsIgnoreCase(attributes.getAsJsonObject().get("name").getAsString())) {
                    JsonElement value = attributes.getAsJsonObject().get("values");
                    recipientUnloadingPoint = value.getAsJsonArray().get(0).getAsString();
                }
            }
        }
        recipient.setId(recipientId);
        recipient.setName(recipientName);
        recipient.setUnloadingPoint(recipientUnloadingPoint);
        bmwMapping.setRecipient(recipient);
    }
}