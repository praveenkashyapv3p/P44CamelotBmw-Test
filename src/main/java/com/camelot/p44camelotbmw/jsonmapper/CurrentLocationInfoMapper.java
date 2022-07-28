package com.camelot.p44camelotbmw.jsonmapper;

import com.camelot.p44camelotbmw.bmwentity.BMWMapping;
import com.camelot.p44camelotbmw.bmwentity.CurrentLocationInfos;
import com.camelot.p44camelotbmw.constants.StatusCodes;
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
        JsonArray states = (JsonArray) ShipJSON.get("states");
        for (JsonElement eventsTyp : events) {
            if (eventsTyp.getAsJsonObject().has("dateTime")) {
                currentLocationInfos.setTimeStamps(eventsTyp.getAsJsonObject().get("dateTime").getAsString());
                eventStopId = eventsTyp.getAsJsonObject().get("stopId").getAsString();
                eventsType = eventsTyp.getAsJsonObject().get("type").getAsString();
            }
//            else if ( eventsTyp.getAsJsonObject().has("receivedDateTime")) {
//                currentLocationInfos.setTimeStamps(eventsTyp.getAsJsonObject().get("receivedDateTime").getAsString());
//                eventStopId = eventsTyp.getAsJsonObject().get("stopId").getAsString();
//                eventsType = eventsTyp.getAsJsonObject().get("type").getAsString();
//            }
        }
        for (JsonElement position : stops) {
            assert eventStopId != null;
            if (eventStopId.equals(position.getAsJsonObject().get("id").getAsString())) {
                longitude = position.getAsJsonObject().get("location").getAsJsonObject().get("coordinates").getAsJsonObject().get("longitude").getAsString();
                currentLocationInfos.setLongitude(longitude);
                latitude = position.getAsJsonObject().get("location").getAsJsonObject().get("coordinates").getAsJsonObject().get("latitude").getAsString();
                currentLocationInfos.setLatitude(latitude);
                locationName = position.getAsJsonObject().get("location").getAsJsonObject().get("name").getAsString();
                currentLocationInfos.setLocationName(locationName);
                
                JsonElement relShipIdentifiers = position.getAsJsonObject().get("location").getAsJsonObject().get("identifiers");
                for (JsonElement relShipIdent : relShipIdentifiers.getAsJsonArray()) {
                    locationID = relShipIdent.getAsJsonObject().get("value").getAsString();
                    currentLocationInfos.setLocationID(locationID);
                }
                
                for (JsonElement statesTyp : states) {
                    statusName = statesTyp.getAsJsonObject().get("type").getAsString();
                    timeStamp = statesTyp.getAsJsonObject().get("startDateTime").getAsString();
                }
                
                Map<String, String> stCod = statusCodes.getStatusCodes();
                statusCode = stCod.get(eventsType);
                currentLocationInfos.setStatusCode(statusCode);
                
                currentLocationInfos.setStatusName(statusName);
                
                currentLocationInfos.setTimeStamps(timeStamp);
            }
        }
        
        //currentLocationInfos.setGeoDateTimeUTC(geoDateTimeUTC);
        
        bmwMapping.setCurrentLocationInfos(currentLocationInfos);
        //System.out.println("currentLocationInfos" + currentLocationInfos);
    }
}