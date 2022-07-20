package com.camelot.p44camelotbmw.jsonmapper;

import com.camelot.p44camelotbmw.bmwentity.*;
import com.camelot.p44camelotbmw.constants.TransportSection;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TransportLegMapper {
    
    TransportSection transportSection = new TransportSection();
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
    
    public void mapTransportLegInfos(String shipmentJson, BMWMapping bmwMapping) {
        List<TransportLegInfo> transportLegInfo1 = new ArrayList<>();
        String eventStopId = "", eventsType = "", polLocId = "", podLocId = "", tspLocId = "";
        int count = 0;
        
        JsonObject ShipJSON = (JsonObject) JsonParser.parseString(shipmentJson);
        JsonArray events = (JsonArray) ShipJSON.get("events");
        JsonArray routeSegments = (JsonArray) ShipJSON.get("shipment").getAsJsonObject().get("routeInfo").getAsJsonObject().get("routeSegments");
        JsonArray stops = (JsonArray) ShipJSON.get("shipment").getAsJsonObject().get("routeInfo").getAsJsonObject().get("stops");
        for (JsonElement position : stops) {
            count++;
            JsonElement transportLegIdentifiers = position.getAsJsonObject().get("location").getAsJsonObject().get("identifiers");
            switch (count) {
                case 1:
                    for (JsonElement transportLegIdent : transportLegIdentifiers.getAsJsonArray()) {
                        tspLocId = position.getAsJsonObject().get("id").getAsString();
                        transportLeg1.setTsp1Loc(transportLegIdent.getAsJsonObject().get("value").getAsString());
                    }
                    for (JsonElement eventsTyp : events) {
                        if ((tspLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("DEPARTURE_FROM_STOP", "GATE_OUT_EMPTY").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                            if (eventsTyp.getAsJsonObject().has("dateTime")) {
                                transportLeg1.setDepartureActual(eventsTyp.getAsJsonObject().get("dateTime").getAsString());
                            } else if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                                transportLeg1.setDeparturePrediction(eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString());
                            }
                        }
                        
                        if ((tspLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("ARRIVAL_AT_STOP", "GATE_IN_FULL").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                            if (eventsTyp.getAsJsonObject().has("dateTime")) {
                                transportLeg1.setArrivalActual(eventsTyp.getAsJsonObject().get("dateTime").getAsString());
                            } else if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                                transportLeg1.setArrivalPrediction(eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString());
                            }
                        }
                    }
                    transportLegInfo.setTransportLeg1(transportLeg1);
                    break;
                case 2:
                    for (JsonElement transportLegIdent : transportLegIdentifiers.getAsJsonArray()) {
                        tspLocId = position.getAsJsonObject().get("id").getAsString();
                        transportLeg2.setTsp2Loc(transportLegIdent.getAsJsonObject().get("value").getAsString());
                    }
                    for (JsonElement eventsTyp : events) {
                        if ((tspLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("DEPARTURE_FROM_STOP", "GATE_OUT_EMPTY").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                            if (eventsTyp.getAsJsonObject().has("dateTime")) {
                                transportLeg2.setDepartureActual(eventsTyp.getAsJsonObject().get("dateTime").getAsString());
                            } else if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                                transportLeg2.setDeparturePrediction(eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString());
                            }
                        }
                        
                        if ((tspLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("ARRIVAL_AT_STOP", "GATE_IN_FULL").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                            if (eventsTyp.getAsJsonObject().has("dateTime")) {
                                transportLeg2.setArrivalActual(eventsTyp.getAsJsonObject().get("dateTime").getAsString());
                            } else if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                                transportLeg2.setArrivalPrediction(eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString());
                            }
                        }
                    }
                    transportLegInfo.setTransportLeg2(transportLeg2);
                    break;
                case 3:
                    for (JsonElement transportLegIdent : transportLegIdentifiers.getAsJsonArray()) {
                        tspLocId = position.getAsJsonObject().get("id").getAsString();
                        transportLeg3.setTsp3Loc(transportLegIdent.getAsJsonObject().get("value").getAsString());
                    }
                    for (JsonElement eventsTyp : events) {
                        if ((tspLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("DEPARTURE_FROM_STOP", "GATE_OUT_EMPTY").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                            if (eventsTyp.getAsJsonObject().has("dateTime")) {
                                transportLeg3.setDepartureActual(eventsTyp.getAsJsonObject().get("dateTime").getAsString());
                            } else if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                                transportLeg3.setDeparturePrediction(eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString());
                            }
                        }
                        
                        if ((tspLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("ARRIVAL_AT_STOP", "GATE_IN_FULL").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                            if (eventsTyp.getAsJsonObject().has("dateTime")) {
                                transportLeg3.setArrivalActual(eventsTyp.getAsJsonObject().get("dateTime").getAsString());
                            } else if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                                transportLeg3.setArrivalPrediction(eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString());
                            }
                        }
                    }
                    transportLegInfo.setTransportLeg3(transportLeg3);
                    break;
                case 4:
                    for (JsonElement transportLegIdent : transportLegIdentifiers.getAsJsonArray()) {
                        tspLocId = position.getAsJsonObject().get("id").getAsString();
                        transportLeg4.setTsp4Loc(transportLegIdent.getAsJsonObject().get("value").getAsString());
                    }
                    for (JsonElement eventsTyp : events) {
                        if ((tspLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("DEPARTURE_FROM_STOP", "GATE_OUT_EMPTY").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                            if (eventsTyp.getAsJsonObject().has("dateTime")) {
                                transportLeg4.setDepartureActual(eventsTyp.getAsJsonObject().get("dateTime").getAsString());
                            } else if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                                transportLeg4.setDeparturePrediction(eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString());
                            }
                        }
                        
                        if ((tspLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("ARRIVAL_AT_STOP", "GATE_IN_FULL").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                            if (eventsTyp.getAsJsonObject().has("dateTime")) {
                                transportLeg4.setArrivalActual(eventsTyp.getAsJsonObject().get("dateTime").getAsString());
                            } else if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                                transportLeg4.setArrivalPrediction(eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString());
                            }
                        }
                    }
                    transportLegInfo.setTransportLeg4(transportLeg4);
                    break;
                case 5:
                    for (JsonElement transportLegIdent : transportLegIdentifiers.getAsJsonArray()) {
                        tspLocId = position.getAsJsonObject().get("id").getAsString();
                        transportLeg5.setTsp5Loc(transportLegIdent.getAsJsonObject().get("value").getAsString());
                    }
                    for (JsonElement eventsTyp : events) {
                        if ((tspLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("DEPARTURE_FROM_STOP", "GATE_OUT_EMPTY").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                            if (eventsTyp.getAsJsonObject().has("dateTime")) {
                                transportLeg5.setDepartureActual(eventsTyp.getAsJsonObject().get("dateTime").getAsString());
                            } else if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                                transportLeg5.setDeparturePrediction(eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString());
                            }
                        }
                        
                        if ((tspLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("ARRIVAL_AT_STOP", "GATE_IN_FULL").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                            if (eventsTyp.getAsJsonObject().has("dateTime")) {
                                transportLeg5.setArrivalActual(eventsTyp.getAsJsonObject().get("dateTime").getAsString());
                            } else if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                                transportLeg5.setArrivalPrediction(eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString());
                            }
                        }
                    }
                    transportLegInfo.setTransportLeg5(transportLeg5);
                    break;
                case 6:
                    for (JsonElement transportLegIdent : transportLegIdentifiers.getAsJsonArray()) {
                        tspLocId = position.getAsJsonObject().get("id").getAsString();
                        transportLeg6.setTsp6Loc(transportLegIdent.getAsJsonObject().get("value").getAsString());
                    }
                    for (JsonElement eventsTyp : events) {
                        if ((tspLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("DEPARTURE_FROM_STOP", "GATE_OUT_EMPTY").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                            if (eventsTyp.getAsJsonObject().has("dateTime")) {
                                transportLeg6.setDepartureActual(eventsTyp.getAsJsonObject().get("dateTime").getAsString());
                            } else if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                                transportLeg6.setDeparturePrediction(eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString());
                            }
                        }
                        if ((tspLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("ARRIVAL_AT_STOP", "GATE_IN_FULL").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                            if (eventsTyp.getAsJsonObject().has("dateTime")) {
                                transportLeg6.setArrivalActual(eventsTyp.getAsJsonObject().get("dateTime").getAsString());
                            } else if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                                transportLeg6.setArrivalPrediction(eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString());
                            }
                        }
                    }
                    transportLegInfo.setTransportLeg6(transportLeg6);
                    break;
                case 7:
                    for (JsonElement transportLegIdent : transportLegIdentifiers.getAsJsonArray()) {
                        tspLocId = position.getAsJsonObject().get("id").getAsString();
                        transportLeg7.setTsp7Loc(transportLegIdent.getAsJsonObject().get("value").getAsString());
                    }
                    for (JsonElement eventsTyp : events) {
                        if ((tspLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("DEPARTURE_FROM_STOP", "GATE_OUT_EMPTY").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                            if (eventsTyp.getAsJsonObject().has("dateTime")) {
                                transportLeg7.setDepartureActual(eventsTyp.getAsJsonObject().get("dateTime").getAsString());
                            } else if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                                transportLeg7.setDeparturePrediction(eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString());
                            }
                        }
                        if ((tspLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("ARRIVAL_AT_STOP", "GATE_IN_FULL").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                            if (eventsTyp.getAsJsonObject().has("dateTime")) {
                                transportLeg7.setArrivalActual(eventsTyp.getAsJsonObject().get("dateTime").getAsString());
                            } else if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                                transportLeg7.setArrivalPrediction(eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString());
                            }
                        }
                    }
                    transportLegInfo.setTransportLeg7(transportLeg7);
                    break;
                case 8:
                    for (JsonElement transportLegIdent : transportLegIdentifiers.getAsJsonArray()) {
                        tspLocId = position.getAsJsonObject().get("id").getAsString();
                        transportLeg8.setTsp8Loc(transportLegIdent.getAsJsonObject().get("value").getAsString());
                    }
                    for (JsonElement eventsTyp : events) {
                        if ((tspLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("DEPARTURE_FROM_STOP", "GATE_OUT_EMPTY").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                            if (eventsTyp.getAsJsonObject().has("dateTime")) {
                                transportLeg8.setDepartureActual(eventsTyp.getAsJsonObject().get("dateTime").getAsString());
                            } else if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                                transportLeg8.setDeparturePrediction(eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString());
                            }
                        }
                        if ((tspLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("ARRIVAL_AT_STOP", "GATE_IN_FULL").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                            if (eventsTyp.getAsJsonObject().has("dateTime")) {
                                transportLeg8.setArrivalActual(eventsTyp.getAsJsonObject().get("dateTime").getAsString());
                            } else if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                                transportLeg8.setArrivalPrediction(eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString());
                            }
                        }
                    }
                    transportLegInfo.setTransportLeg8(transportLeg8);
                    break;
                case 9:
                    for (JsonElement transportLegIdent : transportLegIdentifiers.getAsJsonArray()) {
                        tspLocId = position.getAsJsonObject().get("id").getAsString();
                        transportLeg9.setTsp9Loc(transportLegIdent.getAsJsonObject().get("value").getAsString());
                    }
                    for (JsonElement eventsTyp : events) {
                        if ((tspLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("DEPARTURE_FROM_STOP", "GATE_OUT_EMPTY").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                            if (eventsTyp.getAsJsonObject().has("dateTime")) {
                                transportLeg9.setDepartureActual(eventsTyp.getAsJsonObject().get("dateTime").getAsString());
                            } else if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                                transportLeg9.setDeparturePrediction(eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString());
                            }
                        }
                        if ((tspLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("ARRIVAL_AT_STOP", "GATE_IN_FULL").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                            if (eventsTyp.getAsJsonObject().has("dateTime")) {
                                transportLeg9.setArrivalActual(eventsTyp.getAsJsonObject().get("dateTime").getAsString());
                            } else if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                                transportLeg9.setArrivalPrediction(eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString());
                            }
                        }
                    }
                    transportLegInfo.setTransportLeg9(transportLeg9);
                    break;
                case 10:
                    for (JsonElement transportLegIdent : transportLegIdentifiers.getAsJsonArray()) {
                        tspLocId = position.getAsJsonObject().get("id").getAsString();
                        transportLeg10.setTsp10Loc(transportLegIdent.getAsJsonObject().get("value").getAsString());
                    }
                    for (JsonElement eventsTyp : events) {
                        if ((tspLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("DEPARTURE_FROM_STOP", "GATE_OUT_EMPTY").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                            if (eventsTyp.getAsJsonObject().has("dateTime")) {
                                transportLeg10.setDepartureActual(eventsTyp.getAsJsonObject().get("dateTime").getAsString());
                            } else if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                                transportLeg10.setDeparturePrediction(eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString());
                            }
                        }
                        if ((tspLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("ARRIVAL_AT_STOP", "GATE_IN_FULL").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                            if (eventsTyp.getAsJsonObject().has("dateTime")) {
                                transportLeg10.setArrivalActual(eventsTyp.getAsJsonObject().get("dateTime").getAsString());
                            } else if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                                transportLeg10.setArrivalPrediction(eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString());
                            }
                        }
                    }
                    transportLegInfo.setTransportLeg10(transportLeg10);
                    break;
            }
            
            if (position.getAsJsonObject().get("type").getAsString().equals("PORT_OF_LOADING")) {
                polLocId = position.getAsJsonObject().get("id").getAsString();
                JsonElement relShipIdentifiers = position.getAsJsonObject().get("location").getAsJsonObject().get("identifiers");
                for (JsonElement relShipIdent : relShipIdentifiers.getAsJsonArray()) {
                    pointOfLoading.setPolLoc(relShipIdent.getAsJsonObject().get("value").getAsString());
                }
            }
            
            if (position.getAsJsonObject().get("type").getAsString().equals("PORT_OF_DISCHARGE")) {
                podLocId = position.getAsJsonObject().get("id").getAsString();
                JsonElement relShipIdentifiers = position.getAsJsonObject().get("location").getAsJsonObject().get("identifiers");
                for (JsonElement relShipIdent : relShipIdentifiers.getAsJsonArray()) {
                    pointOfDelivery.setPodLoc(relShipIdent.getAsJsonObject().get("value").getAsString());
                }
            }
        }
        
        for (JsonElement eventsTyp : events) {
            if ((polLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("DEPARTURE_FROM_STOP", "GATE_OUT_EMPTY").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                if (eventsTyp.getAsJsonObject().has("dateTime")) {
                    pointOfLoading.setDepartureActual(eventsTyp.getAsJsonObject().get("dateTime").getAsString());
                } else if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                    pointOfLoading.setDeparturePrediction(eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString());
                }
            }
            
            if ((polLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("ARRIVAL_AT_STOP", "GATE_IN_FULL").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                if (eventsTyp.getAsJsonObject().has("dateTime")) {
                    pointOfLoading.setArrivalActual(eventsTyp.getAsJsonObject().get("dateTime").getAsString());
                } else if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                    pointOfLoading.setArrivalPrediction(eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString());
                }
            }
            
            if ((podLocId.equals(eventsTyp.getAsJsonObject().get("stopId").getAsString())) && (Arrays.asList("ARRIVAL_AT_STOP", "GATE_IN_FULL").contains(eventsTyp.getAsJsonObject().get("type").getAsString()))) {
                if (eventsTyp.getAsJsonObject().has("dateTime")) {
                    pointOfDelivery.setArrivalActual(eventsTyp.getAsJsonObject().get("dateTime").getAsString());
                } else if (eventsTyp.getAsJsonObject().has("estimateDateTime")) {
                    pointOfDelivery.setArrivalPrediction(eventsTyp.getAsJsonObject().get("estimateDateTime").getAsString());
                }
            }
            
            if (eventsTyp.getAsJsonObject().has("dateTime")) {
                eventStopId = eventsTyp.getAsJsonObject().get("stopId").getAsString();
                eventsType = eventsTyp.getAsJsonObject().get("type").getAsString();
            }
        }
        for (JsonElement routeSeg : routeSegments) {
            if (eventStopId.equals(routeSeg.getAsJsonObject().get("fromStopId").getAsString())) {
                transportLegInfo.setCurrentTransportMode(routeSeg.getAsJsonObject().get("transportationMode").getAsString());
            }
        }
        
        Map<String, String> transportSectionMap = transportSection.getTransportSection();
        transportLegInfo.setTransportSection(transportSectionMap.get(eventsType));
        
        transportLegInfo.setPointOfLoading(pointOfLoading);
        
        transportLegInfo.setPointOfDelivery(pointOfDelivery);
        transportLegInfo1.add(transportLegInfo);
        bmwMapping.setTransportLegInfos(transportLegInfo1);
    }
}