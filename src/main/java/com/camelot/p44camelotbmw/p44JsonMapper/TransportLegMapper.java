package com.camelot.p44camelotbmw.p44JsonMapper;

import com.camelot.p44camelotbmw.constants.TransportSection;
import com.camelot.p44camelotbmw.entity.toBmwEntity.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.*;

public class TransportLegMapper {
    
    
    public void mapTransportLegInfos(JsonObject shipmentJson, BMWMapping bmwMapping) {
        TransportSection transportSection = new TransportSection();
        DeliveryInformations deliveryInformations = new DeliveryInformations();
        TransportLegInfo transportLegInfo = new TransportLegInfo();
        TransportLeg1 transportLeg1 = new TransportLeg1();
        TransportLeg2 transportLeg2 = new TransportLeg2();
        TransportLeg3 transportLeg3 = new TransportLeg3();
        TransportLeg4 transportLeg4 = new TransportLeg4();
        TransportLeg5 transportLeg5 = new TransportLeg5();
        TransportLeg6 transportLeg6 = new TransportLeg6();
        TransportLeg7 transportLeg7 = new TransportLeg7();
        TransportLeg8 transportLeg8 = new TransportLeg8();
        TransportLeg9 transportLeg9 = new TransportLeg9();
        TransportLeg10 transportLeg10 = new TransportLeg10();
        PointOfLoading pointOfLoading = new PointOfLoading();
        PointOfDelivery pointOfDelivery = new PointOfDelivery();
        List<TransportLegInfo> transportLegInfo1 = new ArrayList<>();
        String podLoc = "", podArrivalPrediction = "", podArrivalActual = "", polLoc = "", polArrivalPrediction = "", polArrivalActual = "", polDeparturePrediction = "", polDepartureActual = "";
        String eventStopId = "", etaDateTimeUTC = "", eventsType = "", polLocId = "", podLocId = "", tspLocId = "", currentTransportMode = "", transportSectionString = "", lifeCycleStatusVerbose = "active";
        int count = 0;
        String arrivalPrediction1 = "", arrivalActual1 = "", departurePrediction1 = "", departureActual1 = "";
        String arrivalPrediction2 = "", arrivalActual2 = "", departurePrediction2 = "", departureActual2 = "";
        String arrivalPrediction3 = "", arrivalActual3 = "", departurePrediction3 = "", departureActual3 = "";
        String arrivalPrediction4 = "", arrivalActual4 = "", departurePrediction4 = "", departureActual4 = "";
        String arrivalPrediction5 = "", arrivalActual5 = "", departurePrediction5 = "", departureActual5 = "";
        String arrivalPrediction6 = "", arrivalActual6 = "", departurePrediction6 = "", departureActual6 = "";
        String arrivalPrediction7 = "", arrivalActual7 = "", departurePrediction7 = "", departureActual7 = "";
        String arrivalPrediction8 = "", arrivalActual8 = "", departurePrediction8 = "", departureActual8 = "";
        String arrivalPrediction9 = "", arrivalActual9 = "", departurePrediction9 = "", departureActual9 = "";
        String arrivalPrediction10 = "", arrivalActual10 = "", departurePrediction10 = "", departureActual10 = "";
        String tsp1Loc = "", tsp2Loc = "", tsp3Loc = "", tsp4Loc = "", tsp5Loc = "", tsp6Loc = "", tsp7Loc = "", tsp8Loc = "", tsp9Loc = "", tsp10Loc = "";
    
        //JsonObject ShipJSON = (JsonObject) JsonParser.parseString(shipmentJson);
        JsonArray events = (JsonArray) shipmentJson.get("events");
        JsonArray routeSegments = (JsonArray) shipmentJson.get("shipment").getAsJsonObject().get("routeInfo").getAsJsonObject().get("routeSegments");
        JsonArray stops = (JsonArray) shipmentJson.get("shipment").getAsJsonObject().get("routeInfo").getAsJsonObject().get("stops");
        for (JsonElement position : stops) {
            count++;
            if (position.getAsJsonObject().has("location") && position.getAsJsonObject().get("location").getAsJsonObject().has("identifiers")) {
                JsonElement transportLegIdentifiers = position.getAsJsonObject().get("location").getAsJsonObject().get("identifiers");
                switch (count) {
                    case 1:
                        for (JsonElement transportLegIdent : transportLegIdentifiers.getAsJsonArray()) {
                            tspLocId = position.getAsJsonObject().get("id").getAsString();
                            tsp1Loc = transportLegIdent.getAsJsonObject().get("value").getAsString();
                            transportLeg1.setTsp1Loc(tsp1Loc);
                        }
                        for (JsonElement eventsTyp : events) {
                            if ((tspLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("DEPARTURE_FROM_STOP", "GATE_OUT_FULL", "GATE_OUT_EMPTY").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                                if (eventsTyp.getAsJsonObject().has("dateTime")) {
                                    departureActual1 = eventsTyp.getAsJsonObject().get("dateTime").getAsString();
                                    transportLeg1.setDepartureActual(departureActual1);
                                }
                                if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                                    departurePrediction1 = eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString();
                                    transportLeg1.setDeparturePrediction(departurePrediction1);
                                }
                            }
                            
                            if ((tspLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("ARRIVAL_AT_STOP", "GATE_IN_EMPTY", "GATE_IN_FULL").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                                if (eventsTyp.getAsJsonObject().has("dateTime")) {
                                    arrivalActual1 = eventsTyp.getAsJsonObject().get("dateTime").getAsString();
                                    transportLeg1.setArrivalActual(arrivalActual1);
                                }
                                if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                                    arrivalPrediction1 = eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString();
                                    transportLeg1.setArrivalPrediction(arrivalPrediction1);
                                }
                            }
                        }
                        transportLegInfo.setTransportLeg1(transportLeg1);
                        break;
                    case 2:
                        for (JsonElement transportLegIdent : transportLegIdentifiers.getAsJsonArray()) {
                            tspLocId = position.getAsJsonObject().get("id").getAsString();
                            tsp2Loc = transportLegIdent.getAsJsonObject().get("value").getAsString();
                            transportLeg2.setTsp2Loc(tsp2Loc);
                        }
                        for (JsonElement eventsTyp : events) {
                            if ((tspLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("DEPARTURE_FROM_STOP", "GATE_OUT_FULL", "GATE_OUT_EMPTY").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                                if (eventsTyp.getAsJsonObject().has("dateTime")) {
                                    departureActual2 = eventsTyp.getAsJsonObject().get("dateTime").getAsString();
                                    transportLeg2.setDepartureActual(departureActual2);
                                }
                                if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                                    departurePrediction2 = eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString();
                                    transportLeg2.setDeparturePrediction(departurePrediction2);
                                }
                            }
                            
                            if ((tspLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("ARRIVAL_AT_STOP", "GATE_IN_EMPTY", "GATE_IN_FULL").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                                if (eventsTyp.getAsJsonObject().has("dateTime")) {
                                    arrivalActual2 = eventsTyp.getAsJsonObject().get("dateTime").getAsString();
                                    transportLeg2.setArrivalActual(arrivalActual2);
                                }
                                if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                                    arrivalPrediction2 = eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString();
                                    transportLeg2.setArrivalPrediction(arrivalPrediction2);
                                }
                            }
                        }
                        transportLegInfo.setTransportLeg2(transportLeg2);
                        break;
                    case 3:
                        for (JsonElement transportLegIdent : transportLegIdentifiers.getAsJsonArray()) {
                            tspLocId = position.getAsJsonObject().get("id").getAsString();
                            tsp3Loc = transportLegIdent.getAsJsonObject().get("value").getAsString();
                            transportLeg3.setTsp3Loc(tsp3Loc);
                        }
                        for (JsonElement eventsTyp : events) {
                            if ((tspLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("DEPARTURE_FROM_STOP", "GATE_OUT_FULL", "GATE_OUT_EMPTY").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                                if (eventsTyp.getAsJsonObject().has("dateTime")) {
                                    departureActual3 = eventsTyp.getAsJsonObject().get("dateTime").getAsString();
                                    transportLeg3.setDepartureActual(departureActual3);
                                }
                                if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                                    departurePrediction3 = eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString();
                                    transportLeg3.setDeparturePrediction(departurePrediction3);
                                }
                            }
                            
                            if ((tspLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("ARRIVAL_AT_STOP", "GATE_IN_EMPTY", "GATE_IN_FULL").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                                if (eventsTyp.getAsJsonObject().has("dateTime")) {
                                    arrivalActual3 = eventsTyp.getAsJsonObject().get("dateTime").getAsString();
                                    transportLeg3.setArrivalActual(arrivalActual3);
                                }
                                if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                                    arrivalPrediction3 = eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString();
                                    transportLeg3.setArrivalPrediction(arrivalPrediction3);
                                }
                            }
                        }
                        transportLegInfo.setTransportLeg3(transportLeg3);
                        break;
                    case 4:
                        for (JsonElement transportLegIdent : transportLegIdentifiers.getAsJsonArray()) {
                            tspLocId = position.getAsJsonObject().get("id").getAsString();
                            tsp4Loc = transportLegIdent.getAsJsonObject().get("value").getAsString();
                            transportLeg4.setTsp4Loc(tsp4Loc);
                        }
                        for (JsonElement eventsTyp : events) {
                            if ((tspLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("DEPARTURE_FROM_STOP", "GATE_OUT_FULL", "GATE_OUT_EMPTY").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                                if (eventsTyp.getAsJsonObject().has("dateTime")) {
                                    departureActual4 = eventsTyp.getAsJsonObject().get("dateTime").getAsString();
                                    transportLeg4.setDepartureActual(departureActual4);
                                }
                                if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                                    departurePrediction4 = eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString();
                                    transportLeg4.setDeparturePrediction(departurePrediction4);
                                }
                            }
                            
                            if ((tspLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("ARRIVAL_AT_STOP", "GATE_IN_EMPTY", "GATE_IN_FULL").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                                if (eventsTyp.getAsJsonObject().has("dateTime")) {
                                    arrivalActual4 = eventsTyp.getAsJsonObject().get("dateTime").getAsString();
                                    transportLeg4.setArrivalActual(arrivalActual4);
                                }
                                if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                                    arrivalPrediction4 = eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString();
                                    transportLeg4.setArrivalPrediction(arrivalPrediction4);
                                }
                            }
                        }
                        transportLegInfo.setTransportLeg4(transportLeg4);
                        break;
                    case 5:
                        for (JsonElement transportLegIdent : transportLegIdentifiers.getAsJsonArray()) {
                            tspLocId = position.getAsJsonObject().get("id").getAsString();
                            tsp5Loc = transportLegIdent.getAsJsonObject().get("value").getAsString();
                            transportLeg5.setTsp5Loc(tsp5Loc);
                        }
                        for (JsonElement eventsTyp : events) {
                            if ((tspLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("DEPARTURE_FROM_STOP", "GATE_OUT_FULL", "GATE_OUT_EMPTY").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                                if (eventsTyp.getAsJsonObject().has("dateTime")) {
                                    departureActual5 = eventsTyp.getAsJsonObject().get("dateTime").getAsString();
                                    transportLeg5.setDepartureActual(departureActual5);
                                }
                                if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                                    departurePrediction5 = eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString();
                                    transportLeg5.setDeparturePrediction(departurePrediction5);
                                }
                            }
                            
                            if ((tspLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("ARRIVAL_AT_STOP", "GATE_IN_EMPTY", "GATE_IN_FULL").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                                if (eventsTyp.getAsJsonObject().has("dateTime")) {
                                    arrivalActual5 = eventsTyp.getAsJsonObject().get("dateTime").getAsString();
                                    transportLeg5.setArrivalActual(arrivalActual5);
                                }
                                if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                                    arrivalPrediction5 = eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString();
                                    transportLeg5.setArrivalPrediction(arrivalPrediction5);
                                }
                            }
                        }
                        transportLegInfo.setTransportLeg5(transportLeg5);
                        break;
                    case 6:
                        for (JsonElement transportLegIdent : transportLegIdentifiers.getAsJsonArray()) {
                            tspLocId = position.getAsJsonObject().get("id").getAsString();
                            tsp6Loc = transportLegIdent.getAsJsonObject().get("value").getAsString();
                            transportLeg6.setTsp6Loc(tsp6Loc);
                        }
                        for (JsonElement eventsTyp : events) {
                            if ((tspLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("DEPARTURE_FROM_STOP", "GATE_OUT_FULL", "GATE_OUT_EMPTY").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                                if (eventsTyp.getAsJsonObject().has("dateTime")) {
                                    departureActual6 = eventsTyp.getAsJsonObject().get("dateTime").getAsString();
                                    transportLeg6.setDepartureActual(departureActual6);
                                }
                                if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                                    departurePrediction6 = eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString();
                                    transportLeg6.setDeparturePrediction(departurePrediction6);
                                }
                            }
                            if ((tspLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("ARRIVAL_AT_STOP", "GATE_IN_EMPTY", "GATE_IN_FULL").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                                if (eventsTyp.getAsJsonObject().has("dateTime")) {
                                    arrivalActual6 = eventsTyp.getAsJsonObject().get("dateTime").getAsString();
                                    transportLeg6.setArrivalActual(arrivalActual6);
                                }
                                if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                                    arrivalPrediction6 = eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString();
                                    transportLeg6.setArrivalPrediction(arrivalPrediction6);
                                }
                            }
                        }
                        transportLegInfo.setTransportLeg6(transportLeg6);
                        break;
                    case 7:
                        for (JsonElement transportLegIdent : transportLegIdentifiers.getAsJsonArray()) {
                            tspLocId = position.getAsJsonObject().get("id").getAsString();
                            tsp7Loc = transportLegIdent.getAsJsonObject().get("value").getAsString();
                            transportLeg7.setTsp7Loc(tsp7Loc);
                        }
                        for (JsonElement eventsTyp : events) {
                            if ((tspLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("DEPARTURE_FROM_STOP", "GATE_OUT_FULL", "GATE_OUT_EMPTY").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                                if (eventsTyp.getAsJsonObject().has("dateTime")) {
                                    departureActual7 = eventsTyp.getAsJsonObject().get("dateTime").getAsString();
                                    transportLeg7.setDepartureActual(departureActual7);
                                }
                                if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                                    departurePrediction7 = eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString();
                                    transportLeg7.setDeparturePrediction(departurePrediction7);
                                }
                            }
                            if ((tspLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("ARRIVAL_AT_STOP", "GATE_IN_EMPTY", "GATE_IN_FULL").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                                if (eventsTyp.getAsJsonObject().has("dateTime")) {
                                    arrivalActual7 = eventsTyp.getAsJsonObject().get("dateTime").getAsString();
                                    transportLeg7.setArrivalActual(arrivalActual7);
                                }
                                if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                                    arrivalPrediction7 = eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString();
                                    transportLeg7.setArrivalPrediction(arrivalPrediction7);
                                }
                            }
                        }
                        transportLegInfo.setTransportLeg7(transportLeg7);
                        break;
                    case 8:
                        for (JsonElement transportLegIdent : transportLegIdentifiers.getAsJsonArray()) {
                            tspLocId = position.getAsJsonObject().get("id").getAsString();
                            tsp8Loc = transportLegIdent.getAsJsonObject().get("value").getAsString();
                            transportLeg8.setTsp8Loc(tsp8Loc);
                        }
                        for (JsonElement eventsTyp : events) {
                            if ((tspLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("DEPARTURE_FROM_STOP", "GATE_OUT_FULL", "GATE_OUT_EMPTY").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                                if (eventsTyp.getAsJsonObject().has("dateTime")) {
                                    departureActual8 = eventsTyp.getAsJsonObject().get("dateTime").getAsString();
                                    transportLeg8.setDepartureActual(departureActual8);
                                }
                                if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                                    departurePrediction8 = eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString();
                                    transportLeg8.setDeparturePrediction(departurePrediction8);
                                }
                            }
                            if ((tspLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("ARRIVAL_AT_STOP", "GATE_IN_EMPTY", "GATE_IN_FULL").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                                if (eventsTyp.getAsJsonObject().has("dateTime")) {
                                    arrivalActual8 = eventsTyp.getAsJsonObject().get("dateTime").getAsString();
                                    transportLeg8.setArrivalActual(arrivalActual8);
                                }
                                if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                                    arrivalPrediction8 = eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString();
                                    transportLeg8.setArrivalPrediction(arrivalPrediction8);
                                }
                            }
                        }
                        transportLegInfo.setTransportLeg8(transportLeg8);
                        break;
                    case 9:
                        for (JsonElement transportLegIdent : transportLegIdentifiers.getAsJsonArray()) {
                            tspLocId = position.getAsJsonObject().get("id").getAsString();
                            tsp9Loc = transportLegIdent.getAsJsonObject().get("value").getAsString();
                            transportLeg9.setTsp9Loc(tsp9Loc);
                        }
                        for (JsonElement eventsTyp : events) {
                            if ((tspLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("DEPARTURE_FROM_STOP", "GATE_OUT_FULL", "GATE_OUT_EMPTY").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                                if (eventsTyp.getAsJsonObject().has("dateTime")) {
                                    departureActual9 = eventsTyp.getAsJsonObject().get("dateTime").getAsString();
                                    transportLeg9.setDepartureActual(departureActual9);
                                }
                                if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                                    departurePrediction9 = eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString();
                                    transportLeg9.setDeparturePrediction(departurePrediction9);
                                }
                            }
                            if ((tspLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("ARRIVAL_AT_STOP", "GATE_IN_EMPTY", "GATE_IN_FULL").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                                if (eventsTyp.getAsJsonObject().has("dateTime")) {
                                    arrivalActual9 = eventsTyp.getAsJsonObject().get("dateTime").getAsString();
                                    transportLeg9.setArrivalActual(arrivalActual9);
                                }
                                if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                                    arrivalPrediction9 = eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString();
                                    transportLeg9.setArrivalPrediction(arrivalPrediction9);
                                }
                            }
                        }
                        transportLegInfo.setTransportLeg9(transportLeg9);
                        break;
                    case 10:
                        for (JsonElement transportLegIdent : transportLegIdentifiers.getAsJsonArray()) {
                            tspLocId = position.getAsJsonObject().get("id").getAsString();
                            tsp10Loc = transportLegIdent.getAsJsonObject().get("value").getAsString();
                            transportLeg10.setTsp10Loc(tsp10Loc);
                        }
                        for (JsonElement eventsTyp : events) {
                            if ((tspLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("DEPARTURE_FROM_STOP", "GATE_OUT_FULL", "GATE_OUT_EMPTY").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                                if (eventsTyp.getAsJsonObject().has("dateTime")) {
                                    departureActual10 = eventsTyp.getAsJsonObject().get("dateTime").getAsString();
                                    transportLeg10.setDepartureActual(departureActual10);
                                }
                                if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                                    departurePrediction10 = eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString();
                                    transportLeg10.setDeparturePrediction(departurePrediction10);
                                }
                            }
                            if ((tspLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("ARRIVAL_AT_STOP", "GATE_IN_EMPTY", "GATE_IN_FULL").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                                if (eventsTyp.getAsJsonObject().has("dateTime")) {
                                    arrivalActual10 = eventsTyp.getAsJsonObject().get("dateTime").getAsString();
                                    transportLeg10.setArrivalActual(arrivalActual10);
                                }
                                if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                                    arrivalPrediction10 = eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString();
                                    transportLeg10.setArrivalPrediction(arrivalPrediction10);
                                }
                            }
                        }
                        transportLegInfo.setTransportLeg10(transportLeg10);
                        break;
                }
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
                }
                
                if ((podLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("ARRIVAL_AT_STOP", "GATE_IN_EMPTY", "GATE_IN_FULL").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                    if (eventsTyp.getAsJsonObject().has("dateTime")) {
                        podArrivalActual = eventsTyp.getAsJsonObject().get("dateTime").getAsString();
                        pointOfDelivery.setArrivalActual(podArrivalActual);
                        lifeCycleStatusVerbose = "completed";
                    }
                    if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                        podArrivalPrediction = eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString();
                        pointOfDelivery.setArrivalPrediction(podArrivalPrediction);
                    }
                }
    
                if ((podLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Objects.equals("ARRIVAL_AT_STOP", eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                    if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                        deliveryInformations.setEtaDateTimeUTC(etaDateTimeUTC);
                    }
                }
    
                if (eventsTyp.getAsJsonObject().has("dateTime")) {
                    eventStopId = eventsTyp.getAsJsonObject().get("stopId").getAsString();
                    eventsType = eventsTyp.getAsJsonObject().get("type").getAsString();
                }
            }
        }
        for (JsonElement routeSeg : routeSegments) {
            if (eventStopId.equals(routeSeg.getAsJsonObject().get("fromStopId").getAsString())) {
                currentTransportMode = routeSeg.getAsJsonObject().get("transportationMode").getAsString();
                if (currentTransportMode.equalsIgnoreCase("OCEAN")) {
                    currentTransportMode = "SEA";
                }
            }
        }
        transportLegInfo.setCurrentTransportMode(currentTransportMode);
        Map<String, String> transportSectionMap = transportSection.getTransportSection();
        transportSectionString = transportSectionMap.get(eventsType);
        transportLegInfo.setTransportSection(transportSectionString);
    
        transportLegInfo.setPointOfLoading(pointOfLoading);
    
        transportLegInfo.setPointOfDelivery(pointOfDelivery);
        transportLegInfo1.add(transportLegInfo);
        bmwMapping.setTransportLegInfos(transportLegInfo1);
    
        bmwMapping.setDeliveryInformations(deliveryInformations);
    
    
        bmwMapping.setLifecycleStatusVerbose(lifeCycleStatusVerbose);
    
    }
}