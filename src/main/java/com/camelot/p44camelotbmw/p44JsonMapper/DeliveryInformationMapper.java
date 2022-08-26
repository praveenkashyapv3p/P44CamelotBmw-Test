package com.camelot.p44camelotbmw.p44JsonMapper;

import com.camelot.p44camelotbmw.db.CreateShipment;
import com.camelot.p44camelotbmw.db.CreateShipmentRepository;
import com.camelot.p44camelotbmw.entity.toBmwEntity.BMWMapping;
import com.camelot.p44camelotbmw.entity.toBmwEntity.DeliveryInformations;
import com.google.gson.JsonObject;

import java.time.Instant;
import java.util.List;

public class DeliveryInformationMapper {
    
    
    public void mapDeliveryInformation(CreateShipmentRepository createShipmentRepository, JsonObject shipmentJson, BMWMapping bmwMapping) {
        DeliveryInformations deliveryInformations = new DeliveryInformations();
        String internalP44Identifier = "", planPickUpDate = "", planDeliveryDate = "", etaDateTimeUTC = "", etaDateRoutePartUTC = "", etdDateNextRoutePart = "", eventCreationDateTimeUTC = "", eventSendingDateTimeUTC = "";
        
        
        eventSendingDateTimeUTC = Instant.now().toString();
        deliveryInformations.setEventSendingDateTimeUTC(eventSendingDateTimeUTC);
        deliveryInformations.setEventCreationDateTimeUTC(eventSendingDateTimeUTC);
        
        //JsonObject relShipJSON = (JsonObject) JsonParser.parseString(shipmentJson);
//        JsonArray eventsTypeDepFrmStp = (JsonArray) shipmentJson.get("events");
//        for (JsonElement eventsTyp : eventsTypeDepFrmStp) {
//            if ("DELIVERY".equalsIgnoreCase(eventsTyp.getAsJsonObject().get("type").getAsString()) && eventsTyp.getAsJsonObject().has("plannedDateTime")) {
//                eventPlannedDate = eventsTyp.getAsJsonObject().get("plannedDateTime").getAsString();
//            }
//        }
        //deliveryInformations.setPlanDeliveryDate(eventPlannedDate);
        internalP44Identifier = shipmentJson.get("shipment").getAsJsonObject().get("id").getAsString();
        List<CreateShipment> shipmentIds = createShipmentRepository.findByMasterShipmentId(internalP44Identifier);
        for (CreateShipment bmwIdFromDB : shipmentIds) {
            planPickUpDate = bmwIdFromDB.getPlanPickUpDate();
            planDeliveryDate = bmwIdFromDB.getPlanDeliveryDate();
        }
        
        deliveryInformations.setPlanPickUpDate(planPickUpDate);
        deliveryInformations.setPlanDeliveryDate(planDeliveryDate);
        deliveryInformations.setEtaDateTimeUTC(etaDateTimeUTC);
        deliveryInformations.setEtaDateRoutePartUTC(etaDateRoutePartUTC);
        deliveryInformations.setEtdDateNextRoutePart(etdDateNextRoutePart);
        
        bmwMapping.setDeliveryInformations(deliveryInformations);
    }
}