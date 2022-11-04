package com.camelot.p44camelotbmw.p44JsonMapper;

import com.camelot.p44camelotbmw.entity.toBmwEntity.DeliveryInformation;
import com.camelot.p44camelotbmw.entity.toBmwEntity.P44ToBmw;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class DeliveryInformationMapper {
    private static final Logger logger = LogManager.getLogger(DeliveryInformationMapper.class);
    
    public void mapDeliveryInformation(String eventCreationDateTimeUTC, JsonObject shipmentJson, P44ToBmw bmwMapping) {
        DeliveryInformation deliveryInformation = new DeliveryInformation();
        String planPickUpDate = "", planDeliveryDate = "", etaDateTimeUTC = "", etaDateRoutePartUTC = "", etdDateNextRoutePart = "", eventSendingDateTimeUTC = "", routeSegId = "", nextRouteSeg = "", toStpId = "";
    
        DateTimeFormatter inF = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX");
        DateTimeFormatter outF = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSSX");
        String output = OffsetDateTime.now(ZoneOffset.UTC).format(outF);
    
        eventSendingDateTimeUTC = output;
        deliveryInformation.setEventSendingDateTimeUtc(eventSendingDateTimeUTC);
//        eventCreationDateTimeUTC = output;
        deliveryInformation.setEventCreationDateTimeUtc(eventCreationDateTimeUTC);
        if (shipmentJson.get("shipment").getAsJsonObject().has("attributes")) {
            JsonArray attributesList = (JsonArray) shipmentJson.get("shipment").getAsJsonObject().get("attributes");
            for (JsonElement attributes : attributesList) {
                if ("Main haulage planned start".equalsIgnoreCase(attributes.getAsJsonObject().get("name").getAsString())) {
                    JsonElement value = attributes.getAsJsonObject().get("values");
                    try {
                        ZonedDateTime result = ZonedDateTime.parse(value.getAsJsonArray().get(0).getAsString(), inF);
                        planDeliveryDate = result.format(outF);
                    } catch (Exception exception) {
                        logger.error("Main haulage planned start format error" + exception);
                    }
                }
                if ("Main haulage planned end".equalsIgnoreCase(attributes.getAsJsonObject().get("name").getAsString())) {
                    JsonElement value = attributes.getAsJsonObject().get("values");
                    try {
                        ZonedDateTime result = ZonedDateTime.parse(value.getAsJsonArray().get(0).getAsString(), inF);
                        planPickUpDate = result.format(outF);
                    } catch (Exception exception) {
                        logger.error("Main haulage planned end format error" + exception);
                    }
                }
            }
        }
        JsonArray events = (JsonArray) shipmentJson.get("events");
        if (shipmentJson.has("positions")) {
            JsonArray positions = (JsonArray) shipmentJson.get("positions");
            if (positions.size() > 0) {
                JsonElement posArray = positions.get(positions.size() - 1);
                routeSegId = posArray.getAsJsonObject().get("routeSegmentId").getAsString();
            }
        } else {
            for (JsonElement eventsTyp : events) {
                if (eventsTyp.getAsJsonObject().has("dateTime") && eventsTyp.getAsJsonObject().has("routeSegmentId")) {
                    routeSegId = eventsTyp.getAsJsonObject().get("routeSegmentId").getAsString();
                }
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
        
        
        for (JsonElement eventsTyp : events) {
            if (eventsTyp.getAsJsonObject().has("routeSegmentId")) {
                if ((eventsTyp.getAsJsonObject().get("routeSegmentId").getAsString().equalsIgnoreCase(routeSegId)) && eventsTyp.getAsJsonObject().has("estimateDateTime") && (Arrays.asList("ARRIVAL_AT_STOP", "GATE_IN_EMPTY", "GATE_IN_FULL").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                    ZonedDateTime result = ZonedDateTime.parse(eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString(), inF);
                    etaDateRoutePartUTC = result.format(outF);
                }
                if ((eventsTyp.getAsJsonObject().get("routeSegmentId").getAsString().equalsIgnoreCase(nextRouteSeg)) && eventsTyp.getAsJsonObject().has("estimateDateTime") && (Arrays.asList("ARRIVAL_AT_STOP", "GATE_IN_EMPTY", "GATE_IN_FULL").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                    ZonedDateTime result = ZonedDateTime.parse(eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString(), inF);
                    etdDateNextRoutePart = result.format(outF);
                }
            }
        }
        
        JsonArray stops = (JsonArray) shipmentJson.get("shipment").getAsJsonObject().get("routeInfo").getAsJsonObject().get("stops");
        for (JsonElement position : stops) {
            if (position.getAsJsonObject().get("type").getAsString().equals("PORT_OF_DISCHARGE")) {
                for (JsonElement eventsTyp : events) {
                    if ((position.getAsJsonObject().get("id").getAsString().equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && eventsTyp.getAsJsonObject().has("estimateDateTime") && (Arrays.asList("ARRIVAL_AT_STOP", "GATE_IN_EMPTY", "GATE_IN_FULL").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                        ZonedDateTime result = ZonedDateTime.parse(eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString(), inF);
                        //Required
                        etaDateTimeUTC = result.format(outF);
                    }
                }
            }
        }
    
        deliveryInformation.setPlanPickUpDate(planPickUpDate);
        deliveryInformation.setPlanDeliveryDate(planDeliveryDate);
        deliveryInformation.setEtaDateTimeUtc(etaDateTimeUTC);
        deliveryInformation.setEtaDateRoutePartUtc(etaDateRoutePartUTC);
        deliveryInformation.setEtdDateNextRoutePart(etdDateNextRoutePart);
    
        bmwMapping.setDeliveryInformation(deliveryInformation);
    }
}