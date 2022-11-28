package com.camelot.p44camelotbmw.p44JsonMapper;

import com.camelot.p44camelotbmw.entity.toBmwEntity.Identifier;
import com.camelot.p44camelotbmw.entity.toBmwEntity.P44ToBmw;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class IdentifiersMapper {
    
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