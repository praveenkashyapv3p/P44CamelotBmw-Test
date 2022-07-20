package com.camelot.p44camelotbmw.jsonmapper;

import com.camelot.p44camelotbmw.bmwentity.BMWMapping;
import com.camelot.p44camelotbmw.bmwentity.Identifiers;
import com.camelot.p44camelotbmw.p44entity.P44Shipment;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class IdentifiersMapper {
    Identifiers identifiers = new Identifiers();
    
    public void mapIdentifiers(P44Shipment p44Shipment, String shipmentJson, BMWMapping bmwMapping) {
        
        identifiers.setInternalP44Identifier(p44Shipment.getShipment().getId());
        
        identifiers.setContainerID(p44Shipment.getShipment().getIdentifiers().get(0).getValue());
        
        JsonObject relShipJSON = (JsonObject) JsonParser.parseString(shipmentJson);
        JsonArray relShipIdent = (JsonArray) relShipJSON.get("shipment").getAsJsonObject().get("relatedShipments");
        for (JsonElement relIden : relShipIdent) {
            JsonElement relShipIdentifiers = relIden.getAsJsonObject().get("identifiers");
            JsonArray relIndent = relShipIdentifiers.getAsJsonArray();
            for (JsonElement relshipIdent : relIndent) {
                if ("BOOKING_NUMBER".equals(relshipIdent.getAsJsonObject().get("type").getAsString()))
                    identifiers.setBookingNumberBOL(relshipIdent.getAsJsonObject().get("value").getAsString());
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
                if ("DEPARTURE_FROM_STOP".equals(eventsTyp.getAsJsonObject().get("type").getAsString())) {
                    eventStopId = eventsTyp.getAsJsonObject().get("stopId").getAsString();
                    if (eventStopId.equals(fromStopId) && eventsTyp.getAsJsonObject().has("dateTime")) {
                        for (JsonElement relshipmIdent : routSegIndent) {
                            if (relshipmIdent.getAsJsonObject().get("type").getAsString().equals("VESSEL_NAME"))
                                identifiers.setVesselName(relshipmIdent.getAsJsonObject().get("value").getAsString());
                        }
                    }
                    
                }
            }
            
        }
        bmwMapping.setIdentifiers(identifiers);
    }
}