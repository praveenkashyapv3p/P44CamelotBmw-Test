package com.camelot.p44camelotbmw.p44JsonMapper;

import com.camelot.p44camelotbmw.entity.toBmwEntity.Identifier;
import com.camelot.p44camelotbmw.entity.toBmwEntity.P44ToBmw;
import com.camelot.p44camelotbmw.producer.KafkaProducer;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Arrays;

public class IdentifiersMapper {
    private final KafkaProducer producer;
    
    public IdentifiersMapper(KafkaProducer producer) {
        this.producer = producer;
    }
    
    public void mapIdentifier(String jsonKey, JsonObject shipmentJson, P44ToBmw bmwMapping) {
        Identifier identifiers = new Identifier();
        String internalP44Identifier = "", bmwShipmentID = "", billOfLading = "", bookingNumber = "", vesselName = "", bmwBusinessUnit = "", bmwContainerId = "";
        
        //Required
        internalP44Identifier = shipmentJson.get("shipment").getAsJsonObject().get("id").getAsString();
        
        JsonArray containerId = (JsonArray) shipmentJson.get("shipment").getAsJsonObject().get("identifiers");
        for (JsonElement contId : containerId) {
            if ("CONTAINER_ID".equalsIgnoreCase(contId.getAsJsonObject().get("type").getAsString())) {
                //Required
                bmwContainerId = contId.getAsJsonObject().get("value").getAsString();
                /*Temporary tracing of containers for Data validation*/
                if ((Arrays.asList("TXGU5345195", "CAIU7821020", "FFAU4281892", "HLBU2516048", "INKU6646068", "MRKU5030927", "MRSU4502596", "TCNU6440363", "MRKU5784526", "CIPU5007854", "MRKU5543278", "TRHU6654055", "TLLU8817673", "BMOU5648580", "CAIU7815835")).contains(bmwContainerId)) {
                    //logger.traceEntry(shipmentJson.toString());
                    this.producer.writeLogMessage("test", shipmentJson.toString());
                }
                /*Delete above code after Temporary tracing of containers for Data validation is complete*/
            }
            if ("BILL_OF_LADING".equalsIgnoreCase(contId.getAsJsonObject().get("type").getAsString())) {
                billOfLading = contId.getAsJsonObject().get("value").getAsString();
            }
        }
    
        if (shipmentJson.get("shipment").getAsJsonObject().has("attributes")) {
            JsonArray attributesList = (JsonArray) shipmentJson.get("shipment").getAsJsonObject().get("attributes");
            for (JsonElement attributes : attributesList) {
                if ("SAP Carrier details".equalsIgnoreCase(attributes.getAsJsonObject().get("name").getAsString())) {
                    JsonElement value = attributes.getAsJsonObject().get("values");
                    bmwShipmentID = value.getAsJsonArray().get(0).getAsString();
                }
                if ("Business unit".equalsIgnoreCase(attributes.getAsJsonObject().get("name").getAsString())) {
                    JsonElement value = attributes.getAsJsonObject().get("values");
                    bmwBusinessUnit = value.getAsJsonArray().get(0).getAsString();
                }
            }
        }
        if (shipmentJson.get("shipment").getAsJsonObject().has("relatedShipments")) {
            JsonArray relShipIdent = (JsonArray) shipmentJson.get("shipment").getAsJsonObject().get("relatedShipments");
            for (JsonElement relIden : relShipIdent) {
                if (relIden.getAsJsonObject().has("identifiers")) {
                    JsonElement relShipIdentifiers = relIden.getAsJsonObject().get("identifiers");
                    JsonArray relIndent = relShipIdentifiers.getAsJsonArray();
                    for (JsonElement relshipIdent : relIndent) {
                        if ("BOOKING_NUMBER".equalsIgnoreCase(relshipIdent.getAsJsonObject().get("type").getAsString())) {
                            bookingNumber = relshipIdent.getAsJsonObject().get("value").getAsString();
                        }
                    }
                }
            }
        }
        /*
         *
         *
         */
        if (shipmentJson.get("shipment").getAsJsonObject().get("routeInfo").getAsJsonObject().has("routeSegments")) {
            for (JsonElement routSegIden : (JsonArray) shipmentJson.get("shipment").getAsJsonObject().get("routeInfo").getAsJsonObject().get("routeSegments")) {
                if (routSegIden.getAsJsonObject().has("identifiers")) {
                    for (JsonElement eventsTyp : (JsonArray) shipmentJson.get("events")) {
                        if (eventsTyp.getAsJsonObject().has("stopId")) {
                            if (eventsTyp.getAsJsonObject().get("stopId").getAsString().equals(routSegIden.getAsJsonObject().get("fromStopId").getAsString()) && (eventsTyp.getAsJsonObject().has("dateTime"))) {
                                for (JsonElement relShipmentIdent : routSegIden.getAsJsonObject().get("identifiers").getAsJsonArray()) {
                                    if (relShipmentIdent.getAsJsonObject().get("type").getAsString().equalsIgnoreCase("VESSEL_NAME")) {
                                        vesselName = relShipmentIdent.getAsJsonObject().get("value").getAsString();
                                        if (vesselName.equalsIgnoreCase("TBN Vessel")) {
                                            vesselName = "";
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        
        identifiers.setContainerId(bmwContainerId);
        identifiers.setP44InternalContainerId(internalP44Identifier);
        identifiers.setP44TechnicalCorrelationId(jsonKey);
        identifiers.setBillOfLading(billOfLading);
        identifiers.setBmwBusinessRelation(bmwBusinessUnit);
        identifiers.setBmwShipmentId(bmwShipmentID);
        identifiers.setBookingNumber(bookingNumber);
        identifiers.setVesselName(vesselName);
        
        bmwMapping.setIdentifier(identifiers);
    }
}