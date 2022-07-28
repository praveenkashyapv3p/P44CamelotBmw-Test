package com.camelot.p44camelotbmw.jsonmapper;

import com.camelot.p44camelotbmw.bmwentity.BMWMapping;
import com.camelot.p44camelotbmw.bmwentity.Identifiers;
import com.camelot.p44camelotbmw.p44entity.P44Shipment;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class IdentifiersMapper {
    
    
    public void mapIdentifiers(P44Shipment p44Shipment, String shipmentJson, BMWMapping bmwMapping) {
        Identifiers identifiers = new Identifiers();
        String internalP44Identifier = "", containerID = "", bmwShipmentID = "", bookingNumberBOL = "", vesselName = "";
        
        internalP44Identifier = p44Shipment.getShipment().getId();
        identifiers.setInternalP44Identifier(internalP44Identifier);
        containerID = p44Shipment.getShipment().getIdentifiers().get(0).getValue();
        identifiers.setContainerID(containerID);
        
        JsonObject relShipJSON = (JsonObject) JsonParser.parseString(shipmentJson);
        JsonArray relShipIdent = (JsonArray) relShipJSON.get("shipment").getAsJsonObject().get("relatedShipments");
        for (JsonElement relIden : relShipIdent) {
            JsonElement relShipIdentifiers = relIden.getAsJsonObject().get("identifiers");
            JsonArray relIndent = relShipIdentifiers.getAsJsonArray();
            for (JsonElement relshipIdent : relIndent) {
                if ("BOOKING_NUMBER".equals(relshipIdent.getAsJsonObject().get("type").getAsString())) {
                    bookingNumberBOL = relshipIdent.getAsJsonObject().get("value").getAsString();
                    identifiers.setBookingNumberBOL(bookingNumberBOL);
                }
            }
        }
        
        JsonArray routeInfo = (JsonArray) relShipJSON.get("shipment").getAsJsonObject().get("routeInfo").getAsJsonObject().get("routeSegments");
        JsonArray eventsTypeDepFrmStp = (JsonArray) relShipJSON.get("events");
        String eventStopId;
        for (JsonElement routSegIden : routeInfo) {
            JsonElement routSegIdentifiers = routSegIden.getAsJsonObject().get("identifiers");
            JsonArray routSegIndent = routSegIdentifiers.getAsJsonArray();
            String fromStopId = routSegIden.getAsJsonObject().get("fromStopId").getAsString();
            for (JsonElement eventsTyp : eventsTypeDepFrmStp) {
//                if (Arrays.asList("DEPARTURE_FROM_STOP", "GATE_OUT_FULL", "GATE_OUT_EMPTY").contains(eventsTyp.getAsJsonObject().get("type").getAsString()) && eventsTyp.getAsJsonObject().has("stopId")) {
//                    eventStopId = eventsTyp.getAsJsonObject().get("stopId").getAsString();
//                    if (eventStopId.equals(fromStopId) && (eventsTyp.getAsJsonObject().has("dateTime") || eventsTyp.getAsJsonObject().has("receivedDateTime")) ){
//                        for (JsonElement relshipmIdent : routSegIndent) {
//                            if (relshipmIdent.getAsJsonObject().get("type").getAsString().equals("VESSEL_NAME"))
//                                identifiers.setVesselName(relshipmIdent.getAsJsonObject().get("value").getAsString());
//                        }
//                    }
//
//                }
                if (eventsTyp.getAsJsonObject().has("stopId")) {
                    eventStopId = eventsTyp.getAsJsonObject().get("stopId").getAsString();
                    if (eventStopId.equals(fromStopId) && (eventsTyp.getAsJsonObject().has("dateTime"))) {
                        for (JsonElement relshipmIdent : routSegIndent) {
                            if (relshipmIdent.getAsJsonObject().get("type").getAsString().equals("VESSEL_NAME")) {
                                vesselName = relshipmIdent.getAsJsonObject().get("value").getAsString();
                                identifiers.setVesselName(vesselName);
                            }
                        }
                    }
                    
                }
            }
            
        }
        identifiers.setBmwShipmentID(bmwShipmentID);
        bmwMapping.setIdentifiers(identifiers);
    }
}