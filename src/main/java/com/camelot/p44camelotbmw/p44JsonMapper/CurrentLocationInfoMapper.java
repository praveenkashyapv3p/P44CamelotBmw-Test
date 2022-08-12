package com.camelot.p44camelotbmw.p44JsonMapper;

import com.camelot.p44camelotbmw.constants.StatusCodes;
import com.camelot.p44camelotbmw.entity.toBmwEntity.BMWMapping;
import com.camelot.p44camelotbmw.entity.toBmwEntity.CurrentLocationInfos;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Map;

public class CurrentLocationInfoMapper {
    
    
    public void mapCurrLocInfo(String shipmentJson, BMWMapping bmwMapping) {
        CurrentLocationInfos currentLocationInfos = new CurrentLocationInfos();
        StatusCodes statusCodes = new StatusCodes();
        String eventStopId = "", statusName = "", eventsType = "", timeStamp = "", longitude = "", latitude = "", geoDateTimeUTC = "", locationID = "", locationName = "", statusCode = "";
        JsonObject ShipJSON = (JsonObject) JsonParser.parseString(shipmentJson);
        JsonArray stops = (JsonArray) ShipJSON.get("shipment").getAsJsonObject().get("routeInfo").getAsJsonObject().get("stops");
        JsonArray events = (JsonArray) ShipJSON.get("events");
        JsonArray positions = (JsonArray) ShipJSON.get("positions");
        JsonArray states = (JsonArray) ShipJSON.get("states");
        for (JsonElement eventsTyp : events) {
            if (eventsTyp.getAsJsonObject().has("dateTime")) {
                currentLocationInfos.setTimeStamps(eventsTyp.getAsJsonObject().get("dateTime").getAsString());
                eventStopId = eventsTyp.getAsJsonObject().get("stopId").getAsString();
                eventsType = eventsTyp.getAsJsonObject().get("type").getAsString();
            }
        }
    
        if (positions.size() > 0) {
            JsonElement posArray = positions.get(positions.size() - 1);
            longitude = posArray.getAsJsonObject().get("longitude").getAsString();
            latitude = posArray.getAsJsonObject().get("latitude").getAsString();
            geoDateTimeUTC = posArray.getAsJsonObject().get("dateTime").getAsString();
        }
        currentLocationInfos.setGeoDateTimeUTC(geoDateTimeUTC);
        currentLocationInfos.setLongitude(longitude);
        currentLocationInfos.setLatitude(latitude);
    
        for (JsonElement position : stops) {
            assert eventStopId != null;
            if (eventStopId.equals(position.getAsJsonObject().get("id").getAsString())) {
                for (JsonElement statesTyp : states) {
                    statusName = statesTyp.getAsJsonObject().get("type").getAsString();
                    timeStamp = statesTyp.getAsJsonObject().get("startDateTime").getAsString();
                }
                Map<String, String> stCod = statusCodes.getStatusCodes();
                statusCode = stCod.get(eventsType);
                currentLocationInfos.setStatusCode(statusCode);
            
                currentLocationInfos.setStatusName(statusName);
            
                if (!statusName.equals("IN_TRANSIT")) {
                    locationName = position.getAsJsonObject().get("location").getAsJsonObject().get("name").getAsString();
                    JsonElement relShipIdentifiers = position.getAsJsonObject().get("location").getAsJsonObject().get("identifiers");
                    for (JsonElement relShipIdent : relShipIdentifiers.getAsJsonArray()) {
                        locationID = relShipIdent.getAsJsonObject().get("value").getAsString();
                    }
                }
                currentLocationInfos.setLocationName(locationName);
            
                currentLocationInfos.setLocationID(locationID);
            
                currentLocationInfos.setTimeStamps(timeStamp);
            }
        }
    
    
        bmwMapping.setCurrentLocationInfos(currentLocationInfos);
    }
}