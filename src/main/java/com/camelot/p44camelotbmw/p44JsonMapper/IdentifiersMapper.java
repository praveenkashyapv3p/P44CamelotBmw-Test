package com.camelot.p44camelotbmw.p44JsonMapper;

import com.camelot.p44camelotbmw.db.CreateShipment;
import com.camelot.p44camelotbmw.db.CreateShipmentRepository;
import com.camelot.p44camelotbmw.entity.toBmwEntity.BMWMapping;
import com.camelot.p44camelotbmw.entity.toBmwEntity.Identifiers;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.List;

public class IdentifiersMapper {
    
    
    public void mapIdentifiers(CreateShipmentRepository shipmentRepository, JsonObject shipmentJson, BMWMapping bmwMapping) {
        Identifiers identifiers = new Identifiers();
        String internalP44Identifier = "", containerID = "", bmwShipmentID = "", bookingNumberBOL = "", vesselName = "";
        
        
        //JsonObject relShipJSON = (JsonObject) JsonParser.parseString(shipmentJson);
        internalP44Identifier = shipmentJson.get("shipment").getAsJsonObject().get("id").getAsString();
        identifiers.setInternalP44Identifier(internalP44Identifier);
        
        JsonArray containerId = (JsonArray) shipmentJson.get("shipment").getAsJsonObject().get("identifiers");
        for (JsonElement contId : containerId) {
            if ("CONTAINER_ID".equals(contId.getAsJsonObject().get("type").getAsString())) {
                identifiers.setContainerID(contId.getAsJsonObject().get("value").getAsString());
            }
        }
        
        List<CreateShipment> shipmentIds = shipmentRepository.findByMasterShipmentId(internalP44Identifier);
        for (CreateShipment bmwIdFromDB : shipmentIds) {
            bmwShipmentID = bmwIdFromDB.getBmwShipmentId();
        }
        
        JsonArray relShipIdent = (JsonArray) shipmentJson.get("shipment").getAsJsonObject().get("relatedShipments");
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
        
        JsonArray routeInfo = (JsonArray) shipmentJson.get("shipment").getAsJsonObject().get("routeInfo").getAsJsonObject().get("routeSegments");
        JsonArray eventsTypeDepFrmStp = (JsonArray) shipmentJson.get("events");
        String eventStopId;
        for (JsonElement routSegIden : routeInfo) {
            JsonElement routSegIdentifiers = routSegIden.getAsJsonObject().get("identifiers");
            JsonArray routSegIndent = routSegIdentifiers.getAsJsonArray();
            String fromStopId = routSegIden.getAsJsonObject().get("fromStopId").getAsString();
            for (JsonElement eventsTyp : eventsTypeDepFrmStp) {
                if (eventsTyp.getAsJsonObject().has("stopId")) {
                    eventStopId = eventsTyp.getAsJsonObject().get("stopId").getAsString();
                    if (eventStopId.equals(fromStopId) && (eventsTyp.getAsJsonObject().has("dateTime"))) {
                        for (JsonElement relShipmentIdent : routSegIndent) {
                            if (relShipmentIdent.getAsJsonObject().get("type").getAsString().equals("VESSEL_NAME")) {
                                vesselName = relShipmentIdent.getAsJsonObject().get("value").getAsString();
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