package com.camelot.p44camelotbmw.p44JsonMapper;

import com.camelot.p44camelotbmw.db.CreateShipment;
import com.camelot.p44camelotbmw.db.CreateShipmentRepository;
import com.camelot.p44camelotbmw.entity.toBmwEntity.BMWMapping;
import com.camelot.p44camelotbmw.entity.toBmwEntity.DeliveryInformations;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

public class DeliveryInformationMapper {
    
    
    public void mapDeliveryInformation(CreateShipmentRepository createShipmentRepository, JsonObject shipmentJson, BMWMapping bmwMapping) {
        DeliveryInformations deliveryInformations = new DeliveryInformations();
        String internalP44Identifier = "", planPickUpDate = "", planDeliveryDate = "", etaDateTimeUTC = "", etaDateRoutePartUTC = "", etdDateNextRoutePart = "", eventCreationDateTimeUTC = "", eventSendingDateTimeUTC = "", routeSegId = "", nextRouteSeg = "", toStpId = "", frmStpId = "";
    
    
        eventSendingDateTimeUTC = Instant.now().toString();
        deliveryInformations.setEventSendingDateTimeUTC(eventSendingDateTimeUTC);
        deliveryInformations.setEventCreationDateTimeUTC(eventSendingDateTimeUTC);
        internalP44Identifier = shipmentJson.get("shipment").getAsJsonObject().get("id").getAsString();
        List<CreateShipment> shipmentIds = createShipmentRepository.findByMasterShipmentId(internalP44Identifier);
        for (CreateShipment bmwIdFromDB : shipmentIds) {
            planPickUpDate = bmwIdFromDB.getPlanPickUpDate();
            planDeliveryDate = bmwIdFromDB.getPlanDeliveryDate();
        }
    
        if (shipmentJson.has("positions")) {
            JsonArray positions = (JsonArray) shipmentJson.get("positions");
            if (positions.size() > 0) {
                JsonElement posArray = positions.get(positions.size() - 1);
                routeSegId = posArray.getAsJsonObject().get("routeSegmentId").getAsString();
            }
        }
    
        if (shipmentJson.get("shipment").getAsJsonObject().get("routeInfo").getAsJsonObject().has("routeSegments")) {
            JsonArray routeSegments = (JsonArray) shipmentJson.get("shipment").getAsJsonObject().get("routeInfo").getAsJsonObject().get("routeSegments");
            for (JsonElement routeSeg : routeSegments) {
                if (routeSegId.equalsIgnoreCase(routeSeg.getAsJsonObject().get("id").getAsString())) {
                    toStpId = routeSeg.getAsJsonObject().get("toStopId").getAsString();
                }
                if (toStpId.equalsIgnoreCase(routeSeg.getAsJsonObject().get("fromStopId").getAsString())) {
                    nextRouteSeg = routeSeg.getAsJsonObject().get("id").getAsString();
                }
            }
        }
    
        JsonArray events = (JsonArray) shipmentJson.get("events");
        for (JsonElement eventsTyp : events) {
            if (eventsTyp.getAsJsonObject().has("routeSegmentId")) {
                if ((eventsTyp.getAsJsonObject().get("routeSegmentId").getAsString().equalsIgnoreCase(routeSegId)) && eventsTyp.getAsJsonObject().has("estimateDateTime") && (Arrays.asList("ARRIVAL_AT_STOP", "GATE_IN_EMPTY", "GATE_IN_FULL").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                    etaDateRoutePartUTC = eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString();
                }
                if ((eventsTyp.getAsJsonObject().get("routeSegmentId").getAsString().equalsIgnoreCase(nextRouteSeg)) && eventsTyp.getAsJsonObject().has("estimateDateTime") && (Arrays.asList("ARRIVAL_AT_STOP", "GATE_IN_EMPTY", "GATE_IN_FULL").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                    etdDateNextRoutePart = eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString();
                }
            }
        }
    
        JsonArray stops = (JsonArray) shipmentJson.get("shipment").getAsJsonObject().get("routeInfo").getAsJsonObject().get("stops");
        for (JsonElement position : stops) {
            if (position.getAsJsonObject().get("type").getAsString().equals("PORT_OF_DISCHARGE")) {
                for (JsonElement eventsTyp : events) {
                    if ((position.getAsJsonObject().get("id").getAsString().equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && eventsTyp.getAsJsonObject().has("estimateDateTime") && (Arrays.asList("ARRIVAL_AT_STOP", "GATE_IN_EMPTY", "GATE_IN_FULL").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                        etaDateTimeUTC = eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString();
                    }
                }
            }
        }
    
        deliveryInformations.setPlanPickUpDate(planPickUpDate);
        deliveryInformations.setPlanDeliveryDate(planDeliveryDate);
        deliveryInformations.setEtaDateTimeUTC(etaDateTimeUTC);
        deliveryInformations.setEtaDateRoutePartUTC(etaDateRoutePartUTC);
        deliveryInformations.setEtdDateNextRoutePart(etdDateNextRoutePart);
    
        bmwMapping.setDeliveryInformations(deliveryInformations);
    }
}