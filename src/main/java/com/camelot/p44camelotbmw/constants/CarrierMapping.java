package com.camelot.p44camelotbmw.constants;

import java.util.HashMap;
import java.util.Map;

public class CarrierMapping {
    public Map<String, String> getCarrierId() {
        return new HashMap<>() {
            {
                put("91137211", "MAEU");
                put("16785910", "COSU");
                put("16636310", "SUDU");
                put("59160210", "HLCU");
                put("16829810", "MSCU");
                put("22966710", "ONEY");
                put("89334310", "EGLV");
                put("13766210", "MAEU");
                put("10002710", "OOCL");
                put("13820210", "ONEY");
                put("19851310", "MSCU");
                put("19403410", "CMDU");
                put("98924710", "DAYU");
                put("49705710", "HDMU");
                put("21670710", "?");
            }
        };
    }
    
    public Map<String, String> getCarrierName() {
        return new HashMap<>() {
            {
                put("91137211", "MAERSK DEUTSCHLAND");
                put("16785910", "COSCO SHIPPING LINES (GERMANY) GMBH");
                put("16636310", "HSUD HAMBURG SUEDAMERIKANISCHE");
                put("59160210", "HAPAG LLOYD AG");
                put("16829810", "MSC MEDITERRANEAN SHIPPING COMPANY GERMANY GMBH");
                put("22966710", "ONE OCEAN NETWORK EXPRESS (EUROPE) LTD.");
                put("89334310", "EVERGREEN SHIPPING AGENCY");
                put("13766210", "MAERSK, INC. USA");
                put("10002710", "OOCL ORIENT OVERSEAS CONTAINER LINE LTD");
                put("13820210", "ONE OCEAN NETWORK EXPRESS PTE.LTD.");
                put("19851310", "MSC MEDITERRANEAN SHIPPING");
                put("19403410", "CMA CGM SAS");
                put("98924710", "DAL DEUTSCHE AFRIKA-LINIEN");
                put("49705710", "HMM HYUNDAI MERCHANT MARINE");
                put("21670710", "TFG TRANSFRACHT GMBH");
            }
        };
    }
}