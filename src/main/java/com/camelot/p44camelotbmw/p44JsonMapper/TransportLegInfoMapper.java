package com.camelot.p44camelotbmw.p44JsonMapper;

import com.camelot.p44camelotbmw.constants.TransportSection;
import com.camelot.p44camelotbmw.entity.toBmwEntity.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TransportLegInfoMapper {
    public void mapTransportLegInfo(JsonObject shipmentJson, P44ToBmw bmwMapping) {
        TransportSection transportSection = new TransportSection();
        TransportLegInfo transportLegInfo = new TransportLegInfo();
        PointOfLoading pointOfLoading = new PointOfLoading();
        PointOfDelivery pointOfDelivery = new PointOfDelivery();
        List<Leg> transportLegInfo1 = new ArrayList<>();
        
        String eventStopId = "", currentTransportMode = "", transportSectionString = "", eventsType = "", lifeCycleStatus = "active";
        int count = 0;
        JsonArray events = (JsonArray) shipmentJson.get("events");
        JsonArray stops = (JsonArray) shipmentJson.get("shipment").getAsJsonObject().get("routeInfo").getAsJsonObject().get("stops");
        
        String polLoc = "", polLocId = "", podLoc = "", podLocId = "", arrivalPrediction = "", arrivalActual = "", departurePrediction = "", departureActual = "", tspLoc = "", arrivalPlanned = "", departurePlanned = "", tspLocId = "", polDeparturePlanned = "", polArrivalPlanned = "", podArrivalPrediction = "", podArrivalActual = "", podArrivalPlanned = "", polArrivalPrediction = "", polArrivalActual = "", polDeparturePrediction = "", polDepartureActual = "";
        for (JsonElement position : stops) {
            Leg leg = new Leg();
            count++;
            if (position.getAsJsonObject().has("location") && position.getAsJsonObject().get("location").getAsJsonObject().has("identifiers")) {
                JsonElement transportLegIdentifiers = position.getAsJsonObject().get("location").getAsJsonObject().get("identifiers");
                for (JsonElement transportLegIdent : transportLegIdentifiers.getAsJsonArray()) {
                    tspLocId = position.getAsJsonObject().get("id").getAsString();
                    tspLoc = transportLegIdent.getAsJsonObject().get("value").getAsString();
                    leg.setTspLoc(tspLoc);
                }
                leg.setLegNumber(String.valueOf(count));
                for (JsonElement eventsTyp : events) {
                    if ((tspLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("DEPARTURE_FROM_STOP", "GATE_OUT_FULL", "GATE_OUT_EMPTY").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                        if (eventsTyp.getAsJsonObject().has("dateTime")) {
                            departureActual = eventsTyp.getAsJsonObject().get("dateTime").getAsString();
                            leg.setDepartureActual(departureActual);
                        }
                        if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                            departurePrediction = eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString();
                            leg.setDeparturePrediction(departurePrediction);
                        }
                        if (eventsTyp.getAsJsonObject().has("plannedDateTime")) {
                            departurePlanned = eventsTyp.getAsJsonObject().get("plannedDateTime").getAsString();
                            leg.setDeparturePlanned(departurePlanned);
                        }
                    }
                    
                    if ((tspLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("ARRIVAL_AT_STOP", "GATE_IN_EMPTY", "GATE_IN_FULL").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                        if (eventsTyp.getAsJsonObject().has("dateTime")) {
                            arrivalActual = eventsTyp.getAsJsonObject().get("dateTime").getAsString();
                            leg.setArrivalActual(arrivalActual);
                        }
                        if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                            arrivalPrediction = eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString();
                            leg.setArrivalPrediction(arrivalPrediction);
                        }
                        if (eventsTyp.getAsJsonObject().has("plannedDateTime")) {
                            arrivalPlanned = eventsTyp.getAsJsonObject().get("plannedDateTime").getAsString();
                            leg.setArrivalPlanned(arrivalPlanned);
                        }
                    }
                }
                transportLegInfo1.add(leg);
            }
            
            if (position.getAsJsonObject().get("type").getAsString().equals("PORT_OF_LOADING")) {
                polLocId = position.getAsJsonObject().get("id").getAsString();
                if (position.getAsJsonObject().has("location") && position.getAsJsonObject().get("location").getAsJsonObject().has("identifiers")) {
                    JsonElement relShipIdentifiers = position.getAsJsonObject().get("location").getAsJsonObject().get("identifiers");
                    for (JsonElement relShipIdent : relShipIdentifiers.getAsJsonArray()) {
                        polLoc = relShipIdent.getAsJsonObject().get("value").getAsString();
                        pointOfLoading.setPolLoc(polLoc);
                    }
                }
            }
            
            if (position.getAsJsonObject().get("type").getAsString().equals("PORT_OF_DISCHARGE")) {
                podLocId = position.getAsJsonObject().get("id").getAsString();
                if (position.getAsJsonObject().has("location") && position.getAsJsonObject().get("location").getAsJsonObject().has("identifiers")) {
                    JsonElement relShipIdentifiers = position.getAsJsonObject().get("location").getAsJsonObject().get("identifiers");
                    for (JsonElement relShipIdent : relShipIdentifiers.getAsJsonArray()) {
                        podLoc = relShipIdent.getAsJsonObject().get("value").getAsString();
                        pointOfDelivery.setPodLoc(podLoc);
                    }
                }
            }
        }
        
        for (JsonElement eventsTyp : events) {
            if (!eventsTyp.getAsJsonObject().get("type").getAsString().equalsIgnoreCase("UNKNOWN")) {
                if ((polLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("DEPARTURE_FROM_STOP", "GATE_OUT_FULL", "GATE_OUT_EMPTY").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                    if (eventsTyp.getAsJsonObject().has("dateTime")) {
                        polDepartureActual = eventsTyp.getAsJsonObject().get("dateTime").getAsString();
                        pointOfLoading.setDepartureActual(polDepartureActual);
                    }
                    if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                        polDeparturePrediction = eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString();
                        pointOfLoading.setDeparturePrediction(polDeparturePrediction);
                    }
                    if (eventsTyp.getAsJsonObject().has("plannedDateTime")) {
                        polDeparturePlanned = eventsTyp.getAsJsonObject().get("plannedDateTime").getAsString();
                        pointOfLoading.setDeparturePlanned(polDeparturePlanned);
                    }
                }
                
                if ((polLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("ARRIVAL_AT_STOP", "GATE_IN_EMPTY", "GATE_IN_FULL").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                    if (eventsTyp.getAsJsonObject().has("dateTime")) {
                        polArrivalActual = eventsTyp.getAsJsonObject().get("dateTime").getAsString();
                        pointOfLoading.setArrivalActual(polArrivalActual);
                    }
                    if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                        polArrivalPrediction = eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString();
                        pointOfLoading.setArrivalPrediction(polArrivalPrediction);
                    }
                    if (eventsTyp.getAsJsonObject().has("plannedDateTime")) {
                        polArrivalPlanned = eventsTyp.getAsJsonObject().get("plannedDateTime").getAsString();
                        pointOfLoading.setArrivalPlanned(polArrivalPlanned);
                    }
                }
                
                if ((podLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("ARRIVAL_AT_STOP", "GATE_IN_EMPTY", "GATE_IN_FULL").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                    if (eventsTyp.getAsJsonObject().has("dateTime")) {
                        podArrivalActual = eventsTyp.getAsJsonObject().get("dateTime").getAsString();
                        pointOfDelivery.setArrivalActual(podArrivalActual);
                        lifeCycleStatus = "completed";
                    }
                    if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                        podArrivalPrediction = eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString();
                        pointOfDelivery.setArrivalPrediction(podArrivalPrediction);
                    }
                    if (eventsTyp.getAsJsonObject().has("plannedDateTime")) {
                        podArrivalPlanned = eventsTyp.getAsJsonObject().get("plannedDateTime").getAsString();
                        pointOfDelivery.setArrivalPlanned(podArrivalPlanned);
                    }
                }
                
                if (eventsTyp.getAsJsonObject().has("dateTime")) {
                    eventStopId = eventsTyp.getAsJsonObject().get("stopId").getAsString();
                    eventsType = eventsTyp.getAsJsonObject().get("type").getAsString();
                }
            }
        }
        if (shipmentJson.get("shipment").getAsJsonObject().get("routeInfo").getAsJsonObject().has("routeSegments")) {
            JsonArray routeSegments = (JsonArray) shipmentJson.get("shipment").getAsJsonObject().get("routeInfo").getAsJsonObject().get("routeSegments");
            for (JsonElement routeSeg : routeSegments) {
                if (eventStopId.equals(routeSeg.getAsJsonObject().get("fromStopId").getAsString())) {
                    currentTransportMode = routeSeg.getAsJsonObject().get("transportationMode").getAsString();
                    if (currentTransportMode.equalsIgnoreCase("OCEAN")) {
                        currentTransportMode = "SEA";
                    }
                }
            }
        }
        
        /*
         * currentTransportMode
         */
        if (shipmentJson.get("shipment").getAsJsonObject().get("routeInfo").getAsJsonObject().has("routeSegments")) {
            JsonArray routeSegments = (JsonArray) shipmentJson.get("shipment").getAsJsonObject().get("routeInfo").getAsJsonObject().get("routeSegments");
            for (JsonElement routeSeg : routeSegments) {
                if (eventStopId.equals(routeSeg.getAsJsonObject().get("fromStopId").getAsString())) {
                    currentTransportMode = routeSeg.getAsJsonObject().get("transportationMode").getAsString();
                    if (currentTransportMode.equalsIgnoreCase("OCEAN")) {
                        currentTransportMode = "SEA";
                    }
                }
            }
        }
        transportLegInfo.setCurrentTransportMode(currentTransportMode);
        transportLegInfo.setLegs(transportLegInfo1);
        
        /*
         * TransportSection
         */
        Map<String, String> transportSectionMap = transportSection.getTransportSection();
        transportSectionString = transportSectionMap.get(eventsType);
        transportLegInfo.setTransportSection(transportSectionString);
        
        transportLegInfo.setPointOfLoading(pointOfLoading);
        
        transportLegInfo.setPointOfDelivery(pointOfDelivery);
        bmwMapping.setTransportLegInfo(transportLegInfo);
        
        bmwMapping.setLifecycleStatus(lifeCycleStatus);
        
        
    }
}