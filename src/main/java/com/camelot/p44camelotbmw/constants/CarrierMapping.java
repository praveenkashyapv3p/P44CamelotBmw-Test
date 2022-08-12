package com.camelot.p44camelotbmw.constants;

import java.util.HashMap;
import java.util.Map;

public class CarrierMapping {
    public Map<String, String> getCarrierCodes() {
        return new HashMap<>() {
            {
                put("MAEU", "1");
                put("COSU", "1");
                put("SUDU", "13");
                put("HLCU", "21");
                put("MSCU", "24");
                put("ONEY", "24");
                put("EGLV", "29");
                put("MAEU", "43");
                put("LOAD", "48");
            }
        };
    }
}