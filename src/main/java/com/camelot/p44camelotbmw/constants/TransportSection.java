package com.camelot.p44camelotbmw.constants;

import java.util.HashMap;
import java.util.Map;

public class TransportSection {
    public Map<String, String> getTransportSection() {
        return new HashMap<>() {
            {
                put("GATE_OUT_EMPTY", "PRE_HAUL");
                put("ORIGIN", "PRE_HAUL");
                put("PORT_OF_LOADING", "MAIN_HAUL");
                put("GATE_IN_FULL", "MAIN_HAUL");
                put("LOAD", "MAIN_HAUL");
                put("DEPARTURE_FROM_STOP", "MAIN_HAUL");
                put("TRANSHIPMENT_PORT", "MAIN_HAUL");
                put("ARRIVAL_AT_STOP", "MAIN_HAUL");
                put("DISCHARGE", "MAIN_HAUL");
                put("PORT_OF_DISCHARGE", "MAIN_HAUL");
                put("GATE_OUT_FULL", "ON_HAUL");
                put("DELIVERY", "ON_HAUL");
                put("GATE_IN_EMPTY", "ON_HAUL");
            }
        };
    }
}
