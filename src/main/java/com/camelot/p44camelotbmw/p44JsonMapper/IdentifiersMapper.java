package com.camelot.p44camelotbmw.p44JsonMapper;

import com.camelot.p44camelotbmw.entity.toBmwEntity.Identifier;
import com.camelot.p44camelotbmw.entity.toBmwEntity.P44ToBmw;
import com.camelot.p44camelotbmw.producer.KafkaProducer;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class IdentifiersMapper {
    private static final Logger logger = LogManager.getLogger(IdentifiersMapper.class);
    private final KafkaProducer producer;
    
    public IdentifiersMapper(KafkaProducer producer) {
        this.producer = producer;
    }
    
    public void mapIdentifier(String jsonKey, JsonObject shipmentJson, P44ToBmw bmwMapping) {
        Identifier identifiers = new Identifier();
        String internalP44Identifier = "", bmwShipmentID = "", billOfLading = "", bookingNumber = "", vesselName = "", bmwBusinessUnit = "", bmwContainerId = "";
        
        
        internalP44Identifier = shipmentJson.get("shipment").getAsJsonObject().get("id").getAsString();
        
        
        JsonArray containerId = (JsonArray) shipmentJson.get("shipment").getAsJsonObject().get("identifiers");
        for (JsonElement contId : containerId) {
            if ("CONTAINER_ID".equalsIgnoreCase(contId.getAsJsonObject().get("type").getAsString())) {
                bmwContainerId = contId.getAsJsonObject().get("value").getAsString();
                /*Temporary tracing of containers for Data validation*/
                if ((Arrays.asList("CAIU7053452", "CMAU7681240", "FSCU8704094", "CSNU6428681", "MSKU8538003", "TLLU6848274", "EITU9074179", "TGBU8621308", "HASU4670368", "MSKU9718864", "FANU1740651", "TEMU1608650", "KOCU4488754", "MSKU6889462", "MRSU3202341", "FCIU7369829", "FFAU3068178", "TCNU7686182", "NYKU5107789", "CAIU9003698", "OOCU7340261")).contains(contId.getAsJsonObject().get("value").getAsString())) {
                    //logger.traceEntry(shipmentJson.toString());
                    this.producer.writeLogMessage(jsonKey, shipmentJson.toString());
                }
                /*Delete above code after Temporary tracing of containers for Data validation is complete*/
            }
            if ("BILL_OF_LADING".equalsIgnoreCase(contId.getAsJsonObject().get("type").getAsString())) {
                billOfLading = contId.getAsJsonObject().get("value").getAsString();
            }
        }
    
        if (shipmentJson.get("shipment").getAsJsonObject().has("attributes")) {
            JsonArray attributesList = (JsonArray) shipmentJson.get("shipment").getAsJsonObject().get("attributes");
            for (JsonElement attributes : attributesList) {
                if ("SAP Carrier details".equalsIgnoreCase(attributes.getAsJsonObject().get("name").getAsString())) {
                    JsonElement value = attributes.getAsJsonObject().get("values");
                    bmwShipmentID = value.getAsJsonArray().get(0).getAsString();
                }
                if ("Business unit".equalsIgnoreCase(attributes.getAsJsonObject().get("name").getAsString())) {
                    JsonElement value = attributes.getAsJsonObject().get("values");
                    bmwBusinessUnit = value.getAsJsonArray().get(0).getAsString();
                }
            }
        }
        if (shipmentJson.get("shipment").getAsJsonObject().has("relatedShipments")) {
            JsonArray relShipIdent = (JsonArray) shipmentJson.get("shipment").getAsJsonObject().get("relatedShipments");
            for (JsonElement relIden : relShipIdent) {
                if (relIden.getAsJsonObject().has("identifiers")) {
                    JsonElement relShipIdentifiers = relIden.getAsJsonObject().get("identifiers");
                    JsonArray relIndent = relShipIdentifiers.getAsJsonArray();
                    for (JsonElement relshipIdent : relIndent) {
                        if ("BOOKING_NUMBER".equalsIgnoreCase(relshipIdent.getAsJsonObject().get("type").getAsString())) {
                            bookingNumber = relshipIdent.getAsJsonObject().get("value").getAsString();
                        }
                    }
                }
            }
        }
        if (shipmentJson.get("shipment").getAsJsonObject().get("routeInfo").getAsJsonObject().has("routeSegments")) {
            for (JsonElement routSegIden : (JsonArray) shipmentJson.get("shipment").getAsJsonObject().get("routeInfo").getAsJsonObject().get("routeSegments")) {
                if (routSegIden.getAsJsonObject().has("identifiers")) {
                    for (JsonElement eventsTyp : (JsonArray) shipmentJson.get("events")) {
                        if (eventsTyp.getAsJsonObject().has("stopId")) {
                            if (eventsTyp.getAsJsonObject().get("stopId").getAsString().equals(routSegIden.getAsJsonObject().get("fromStopId").getAsString()) && (eventsTyp.getAsJsonObject().has("dateTime"))) {
                                for (JsonElement relShipmentIdent : routSegIden.getAsJsonObject().get("identifiers").getAsJsonArray()) {
                                    if (relShipmentIdent.getAsJsonObject().get("type").getAsString().equalsIgnoreCase("VESSEL_NAME")) {
                                        vesselName = relShipmentIdent.getAsJsonObject().get("value").getAsString();
                                        if (vesselName.equalsIgnoreCase("TBN Vessel")) {
                                            vesselName = "";
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        
        identifiers.setContainerId(bmwContainerId);
        identifiers.setP44InternalContainerId(internalP44Identifier);
        identifiers.setP44TechnicalCorrelationId(jsonKey);
        identifiers.setBillOfLading(billOfLading);
        identifiers.setBmwBusinessRelation(bmwBusinessUnit);
        identifiers.setBmwShipmentId(bmwShipmentID);
        identifiers.setBookingNumber(bookingNumber);
        identifiers.setVesselName(vesselName);
        
        bmwMapping.setIdentifier(identifiers);
    }
}