package com.camelot.p44camelotbmw.p44JsonMapper;

import com.camelot.p44camelotbmw.constants.StatusCodes;
import com.camelot.p44camelotbmw.entity.toBmwEntity.BMWMapping;
import com.camelot.p44camelotbmw.entity.toBmwEntity.CurrentLocationInfos;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Map;

public class CurrentLocationInfoMapper {
    
    
    public void mapCurrLocInfo(JsonObject shipmentJson, BMWMapping bmwMapping) {
    
        CurrentLocationInfos currentLocationInfos = new CurrentLocationInfos();
        StatusCodes statusCodes = new StatusCodes();
        String eventStopId = "", statusName = "", eventsType = "", timeStamp = "", longitude = "", latitude = "", geoDateTimeUTC = "", locationID = "", locationName = "", statusCode = "";
        JsonArray events = (JsonArray) shipmentJson.get("events");
    
        for (JsonElement eventsTyp : events) {
            if (eventsTyp.getAsJsonObject().has("dateTime")) {
                eventStopId = eventsTyp.getAsJsonObject().get("stopId").getAsString();
                eventsType = eventsTyp.getAsJsonObject().get("type").getAsString();
            }
        }
    
        if (shipmentJson.has("positions")) {
            JsonArray positions = (JsonArray) shipmentJson.get("positions");
            if (positions.size() > 0) {
                JsonElement posArray = positions.get(positions.size() - 1);
                longitude = posArray.getAsJsonObject().get("longitude").getAsString();
                latitude = posArray.getAsJsonObject().get("latitude").getAsString();
                geoDateTimeUTC = posArray.getAsJsonObject().get("dateTime").getAsString();
            }
        }
    
        JsonArray stops = (JsonArray) shipmentJson.get("shipment").getAsJsonObject().get("routeInfo").getAsJsonObject().get("stops");
        JsonArray states = (JsonArray) shipmentJson.get("states");
        for (JsonElement position : stops) {
            assert eventStopId != null;
            if (eventStopId.equals(position.getAsJsonObject().get("id").getAsString())) {
                /*
                Get the last entry of states array and its details as statusName and timeStamp
                 */
                for (JsonElement statesTyp : states) {
                    statusName = statesTyp.getAsJsonObject().get("type").getAsString();
                    timeStamp = statesTyp.getAsJsonObject().get("startDateTime").getAsString();
                }
                currentLocationInfos.setStatusName(statusName);
                /*
                 * Static mapping of status code
                 */
                Map<String, String> stCod = statusCodes.getStatusCodes();
                statusCode = stCod.get(eventsType);
                currentLocationInfos.setStatusCode(statusCode);
            
                /*
                 *  Print locationId and locationName only if current status is not IN_TRANSIT
                 */
                if (!statusName.equalsIgnoreCase("IN_TRANSIT")) {
                    locationName = position.getAsJsonObject().get("location").getAsJsonObject().get("name").getAsString();
                    if (position.getAsJsonObject().get("location").getAsJsonObject().has("identifiers")) {
                        JsonElement relShipIdentifiers = position.getAsJsonObject().get("location").getAsJsonObject().get("identifiers");
                        for (JsonElement relShipIdent : relShipIdentifiers.getAsJsonArray()) {
                            locationID = relShipIdent.getAsJsonObject().get("value").getAsString();
                        }
                    }
                }
            
            
                if (statusName.equalsIgnoreCase("AT_STOP") && position.getAsJsonObject().get("location").getAsJsonObject().has("coordinates")) {
                    JsonElement relShipIdentifiers = position.getAsJsonObject().get("location").getAsJsonObject().get("coordinates");
                    latitude = relShipIdentifiers.getAsJsonObject().get("latitude").getAsString();
                    longitude = relShipIdentifiers.getAsJsonObject().get("longitude").getAsString();
                    geoDateTimeUTC = timeStamp;
                }
                currentLocationInfos.setGeoDateTimeUTC(geoDateTimeUTC);
                //if (!longitude.equals("") && !latitude.equals("")) {
                //LatLngConverter latLngConverter = new LatLngConverter();
                //float[] coordinates = {Float.parseFloat(longitude), Float.parseFloat(latitude)};
                //String dmsResult = latLngConverter.processCoordinates(coordinates);
                //String[] coordinatesString = (dmsResult.split(","));
                //longitude = coordinatesString[0];
                //latitude = coordinatesString[1];
                //}
            
                currentLocationInfos.setLongitude(longitude);
                currentLocationInfos.setLatitude(latitude);
                currentLocationInfos.setLocationName(locationName);
                currentLocationInfos.setLocationID(locationID);
                currentLocationInfos.setTimeStamps(timeStamp);
            }
        }
    
        bmwMapping.setCurrentLocationInfos(currentLocationInfos);
    }
}