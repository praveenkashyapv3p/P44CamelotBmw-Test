package com.camelot.p44camelotbmw.jsonmapper;

import com.camelot.p44camelotbmw.bmwentity.BMWMapping;
import com.camelot.p44camelotbmw.bmwentity.DeliveryInformations;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.time.Instant;

public class DeliveryInformationMapper {
    
    DeliveryInformations deliveryInformations = new DeliveryInformations();
    
    public void mapDeliveryInformation(String shipmentJson, BMWMapping bmwMapping) {
        String eventPlannedDate = "";
        
        deliveryInformations.setEventCreationDateTimeUTC(Instant.now().toString());
        
        deliveryInformations.setEventSendingDateTimeUTC(Instant.now().toString());
        
        JsonObject relShipJSON = (JsonObject) JsonParser.parseString(shipmentJson);
        JsonArray eventsTypeDepFrmStp = (JsonArray) relShipJSON.get("events");
        for (JsonElement eventsTyp : eventsTypeDepFrmStp) {
            if ("DELIVERY".equals(eventsTyp.getAsJsonObject().get("type").getAsString()) && eventsTyp.getAsJsonObject().has("plannedDateTime")) {
                eventPlannedDate = eventsTyp.getAsJsonObject().get("plannedDateTime").getAsString();
            }
        }
        deliveryInformations.setPlanDeliveryDate(eventPlannedDate);
        
        bmwMapping.setDeliveryInformations(deliveryInformations);
    }
}