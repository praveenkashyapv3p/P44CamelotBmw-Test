package com.camelot.p44camelotbmw.constants;

import java.util.HashMap;
import java.util.Map;

public class StatusCodes {

    public Map<String, String> getStatusCodes() {
        return new HashMap<>() {
            {
                put("GATE_IN_FULL", "1");
                put("ARRIVAL_AT_STOP", "1");
                put("GATE_OUT_EMPTY", "13");
                put("DELIVERY", "21");
                put("DEPARTURE_FROM_STOP", "24");
                put("GATE_OUT_FULL", "24");
                put("DISCHARGE", "29");
                put("GATE_IN_EMPTY", "43");
                put("LOAD", "48");
            }
        };
    }
}
