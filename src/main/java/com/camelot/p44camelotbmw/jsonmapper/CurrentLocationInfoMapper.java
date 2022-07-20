package com.camelot.p44camelotbmw.jsonmapper;

import com.camelot.p44camelotbmw.bmwentity.BMWMapping;
import com.camelot.p44camelotbmw.bmwentity.CurrentLocationInfos;
import com.camelot.p44camelotbmw.constants.StatusCodes;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.time.Instant;
import java.util.Map;

public class CurrentLocationInfoMapper {
    
    CurrentLocationInfos currentLocationInfos = new CurrentLocationInfos();
    
    public void mapCurrLocInfo(String shipmentJson, BMWMapping bmwMapping) {
        StatusCodes statusCodes = new StatusCodes();
        String eventStopId = "", statusName = "", eventsType = "";
        JsonObject ShipJSON = (JsonObject) JsonParser.parseString(shipmentJson);
        JsonArray stops = (JsonArray) ShipJSON.get("shipment").getAsJsonObject().get("routeInfo").getAsJsonObject().get("stops");
        JsonArray events = (JsonArray) ShipJSON.get("events");
        JsonArray states = (JsonArray) ShipJSON.get("states");
        for (JsonElement eventsTyp : events) {
            if (eventsTyp.getAsJsonObject().has("dateTime")) {
                currentLocationInfos.setTimeStamps(eventsTyp.getAsJsonObject().get("dateTime").getAsString());
                eventStopId = eventsTyp.getAsJsonObject().get("stopId").getAsString();
                eventsType = eventsTyp.getAsJsonObject().get("type").getAsString();
            }
        }
        for (JsonElement position : stops) {
            assert eventStopId != null;
            if (eventStopId.equals(position.getAsJsonObject().get("id").getAsString())) {
                
                currentLocationInfos.setLongitude(position.getAsJsonObject().get("location").getAsJsonObject().get("coordinates").getAsJsonObject().get("longitude").getAsString());
                
                currentLocationInfos.setLatitude(position.getAsJsonObject().get("location").getAsJsonObject().get("coordinates").getAsJsonObject().get("latitude").getAsString());
                
                currentLocationInfos.setLocationName(position.getAsJsonObject().get("location").getAsJsonObject().get("name").getAsString());
                
                JsonElement relShipIdentifiers = position.getAsJsonObject().get("location").getAsJsonObject().get("identifiers");
                for (JsonElement relShipIdent : relShipIdentifiers.getAsJsonArray()) {
                    currentLocationInfos.setLocationID(relShipIdent.getAsJsonObject().get("value").getAsString());
                }
                
                for (JsonElement statesTyp : states) {
                    statusName = statesTyp.getAsJsonObject().get("type").getAsString();
                }
                
                Map<String, String> stCod = statusCodes.getStatusCodes();
                currentLocationInfos.setStatusCode(stCod.get(eventsType));
                
                currentLocationInfos.setStatusName(statusName);
                
                currentLocationInfos.setTimeStamps(Instant.now().toString());
            }
        }
        
        
        bmwMapping.setCurrentLocationInfos(currentLocationInfos);
        //System.out.println("currentLocationInfos" + currentLocationInfos);
    }
}