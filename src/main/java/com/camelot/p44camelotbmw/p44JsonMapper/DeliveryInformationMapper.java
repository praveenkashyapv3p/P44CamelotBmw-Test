package com.camelot.p44camelotbmw.p44JsonMapper;

import com.camelot.p44camelotbmw.entity.toBmwEntity.BMWMapping;
import com.camelot.p44camelotbmw.entity.toBmwEntity.DeliveryInformations;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.time.Instant;

public class DeliveryInformationMapper {
    
    
    public void mapDeliveryInformation(String shipmentJson, BMWMapping bmwMapping) {
        DeliveryInformations deliveryInformations = new DeliveryInformations();
        String eventPlannedDate = "", planPickUpDate = "", planDeliveryDate = "", etaDateTimeUTC = "", etaDateRoutePartUTC = "", etdDateNextRoutePart = "", eventCreationDateTimeUTC = "", eventSendingDateTimeUTC = "";
        
        deliveryInformations.setEventCreationDateTimeUTC(eventCreationDateTimeUTC);
        
        eventSendingDateTimeUTC = Instant.now().toString();
        deliveryInformations.setEventSendingDateTimeUTC(eventSendingDateTimeUTC);
        
        JsonObject relShipJSON = (JsonObject) JsonParser.parseString(shipmentJson);
        JsonArray eventsTypeDepFrmStp = (JsonArray) relShipJSON.get("events");
        for (JsonElement eventsTyp : eventsTypeDepFrmStp) {
            if ("DELIVERY".equals(eventsTyp.getAsJsonObject().get("type").getAsString()) && eventsTyp.getAsJsonObject().has("plannedDateTime")) {
                eventPlannedDate = eventsTyp.getAsJsonObject().get("plannedDateTime").getAsString();
            }
        }
        deliveryInformations.setPlanDeliveryDate(eventPlannedDate);
        deliveryInformations.setPlanPickUpDate(planPickUpDate);
        deliveryInformations.setPlanDeliveryDate(planDeliveryDate);
        deliveryInformations.setEtaDateTimeUTC(etaDateTimeUTC);
        deliveryInformations.setEtaDateRoutePartUTC(etaDateRoutePartUTC);
        deliveryInformations.setEtdDateNextRoutePart(etdDateNextRoutePart);
        
        bmwMapping.setDeliveryInformations(deliveryInformations);
    }
}