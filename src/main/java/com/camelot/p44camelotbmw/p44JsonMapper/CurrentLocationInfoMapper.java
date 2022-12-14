package com.camelot.p44camelotbmw.p44JsonMapper;

import com.camelot.p44camelotbmw.constants.StatusCodes;
import com.camelot.p44camelotbmw.entity.toBmwEntity.CurrentLocationInfo;
import com.camelot.p44camelotbmw.entity.toBmwEntity.P44ToBmw;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class CurrentLocationInfoMapper {
    
    
    public void mapCurrLocInfo(JsonObject shipmentJson, P44ToBmw bmwMapping) {
        
        CurrentLocationInfo currentLocationInfos = new CurrentLocationInfo();
        StatusCodes statusCodes = new StatusCodes();
        String eventStopId = "", statusName = "", eventsType = "", timeStamp = "", longitude = "", latitude = "", geoDateTimeUTC = "",
                locationID = "", locationName = "", statusCode = "", sourceOfPositionData = "";
        
        DateTimeFormatter inF = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX");
        DateTimeFormatter outF = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSSX");
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
                ZonedDateTime result = ZonedDateTime.parse(posArray.getAsJsonObject().get("dateTime").getAsString(), inF);
                geoDateTimeUTC = result.format(outF);
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
                    //Required
                    statusName = statesTyp.getAsJsonObject().get("type").getAsString();
                    ZonedDateTime result = ZonedDateTime.parse(statesTyp.getAsJsonObject().get("startDateTime").getAsString(), inF);
                    //Required
                    timeStamp = result.format(outF);
                }
    
                /*
                 * Static mapping of status code
                 */
                Map<String, String> stCod = statusCodes.getStatusCodes();
                //Required
                statusCode = stCod.get(eventsType);
    
    
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
    
                currentLocationInfos.setStatusCode(statusCode);
                currentLocationInfos.setStatusName(statusName);
                currentLocationInfos.setTimeStamp(timeStamp);
                currentLocationInfos.setGeoDateTimeUtc(geoDateTimeUTC);
                currentLocationInfos.setLatitude(latitude);
                currentLocationInfos.setLongitude(longitude);
                currentLocationInfos.setLocationId(locationID);
                currentLocationInfos.setLocationName(locationName);
                currentLocationInfos.setSourceofPositionData(sourceOfPositionData);
            }
        }
        
        bmwMapping.setCurrentLocationInfo(currentLocationInfos);
    }
}