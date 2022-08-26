package com.camelot.p44camelotbmw.p44JsonMapper;

import com.camelot.p44camelotbmw.db.CreateShipment;
import com.camelot.p44camelotbmw.db.CreateShipmentRepository;
import com.camelot.p44camelotbmw.db.RecipientPlantCodeRepository;
import com.camelot.p44camelotbmw.db.SenderPlantCodeRepository;
import com.camelot.p44camelotbmw.entity.toBmwEntity.BMWMapping;
import com.camelot.p44camelotbmw.entity.toBmwEntity.Identifiers;
import com.camelot.p44camelotbmw.producer.KafkaProducer;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

public class IdentifiersMapper {
    private static final Logger logger = LogManager.getLogger(IdentifiersMapper.class);
    private final KafkaProducer producer;
    
    public IdentifiersMapper(KafkaProducer producer) {
        this.producer = producer;
    }
    
    public void mapIdentifiers(CreateShipmentRepository shipmentRepository, SenderPlantCodeRepository senderPlantCodeRepository, RecipientPlantCodeRepository recipientPlantCodeRepository, String jsonKey, JsonObject shipmentJson, BMWMapping bmwMapping) {
        Identifiers identifiers = new Identifiers();
        String internalP44Identifier = "", containerID = "", bmwShipmentID = "", billOfLading = "", bookingNumber = "", bookingNumberBOL = "", vesselName = "", bmwBusinessUnit = "";
        
        identifiers.setCorrelationId(jsonKey);
        
        //JsonObject relShipJSON = (JsonObject) JsonParser.parseString(shipmentJson);
        internalP44Identifier = shipmentJson.get("shipment").getAsJsonObject().get("id").getAsString();
        identifiers.setInternalP44Identifier(internalP44Identifier);
        
        JsonArray containerId = (JsonArray) shipmentJson.get("shipment").getAsJsonObject().get("identifiers");
        for (JsonElement contId : containerId) {
            if ("CONTAINER_ID".equalsIgnoreCase(contId.getAsJsonObject().get("type").getAsString())) {
                identifiers.setContainerID(contId.getAsJsonObject().get("value").getAsString());
                /*Temporary tracing of containers for Data validation*/
                if ((Arrays.asList(
                        "GAOU6076128", "BSIU9639751", "TRHU5337518", "MRSU3849066", "MRKU3692924", "TRHU7195840", "EGHU8487330",
                        "MRSU4700966", "UETU5615695", "SUDU5493296", "MSKU0166189", "MSDU8759550", "MSMU6905667", "DRYU9607220",
                        "BEAU5422048", "CBHU8932857", "TCKU6677710", "FCIU9157330", "HLBU3174042"
                )).contains(contId.getAsJsonObject().get("value").getAsString())) {
                    //logger.traceEntry(shipmentJson.toString());
                    this.producer.writeLogMessage(jsonKey, shipmentJson.toString());
                }
                /*Delete above code after Temporary tracing of containers for Data validation is complete*/
            }
            if ("BILL_OF_LADING".equalsIgnoreCase(contId.getAsJsonObject().get("type").getAsString())) {
                billOfLading = contId.getAsJsonObject().get("value").getAsString();
            }
        }
        
        List<CreateShipment> shipmentIds = shipmentRepository.findByMasterShipmentId(internalP44Identifier);
        for (CreateShipment bmwIdFromDB : shipmentIds) {
            bmwShipmentID = bmwIdFromDB.getBmwShipmentId();
        }
        
        JsonArray relShipIdent = (JsonArray) shipmentJson.get("shipment").getAsJsonObject().get("relatedShipments");
        for (JsonElement relIden : relShipIdent) {
            JsonElement relShipIdentifiers = relIden.getAsJsonObject().get("identifiers");
            JsonArray relIndent = relShipIdentifiers.getAsJsonArray();
            for (JsonElement relshipIdent : relIndent) {
                if ("BOOKING_NUMBER".equalsIgnoreCase(relshipIdent.getAsJsonObject().get("type").getAsString())) {
                    bookingNumber = relshipIdent.getAsJsonObject().get("value").getAsString();
                }
            }
        }
        
        JsonArray routeInfo = (JsonArray) shipmentJson.get("shipment").getAsJsonObject().get("routeInfo").getAsJsonObject().get("routeSegments");
        JsonArray eventsTypeDepFrmStp = (JsonArray) shipmentJson.get("events");
        String eventStopId;
        for (JsonElement routSegIden : routeInfo) {
            JsonElement routSegIdentifiers = routSegIden.getAsJsonObject().get("identifiers");
            JsonArray routSegIndent = routSegIdentifiers.getAsJsonArray();
            String fromStopId = routSegIden.getAsJsonObject().get("fromStopId").getAsString();
            for (JsonElement eventsTyp : eventsTypeDepFrmStp) {
                if (eventsTyp.getAsJsonObject().has("stopId")) {
                    eventStopId = eventsTyp.getAsJsonObject().get("stopId").getAsString();
                    if (eventStopId.equals(fromStopId) && (eventsTyp.getAsJsonObject().has("dateTime"))) {
                        for (JsonElement relShipmentIdent : routSegIndent) {
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

//        if (!billOfLading.equals("")) {
//            bookingNumberBOL = billOfLading;
//        } else if (!bookingNumber.equals("")) {
//            bookingNumberBOL = bookingNumber;
//        }
        
        
        identifiers.setBmwBusinessUnit(bmwBusinessUnit);
        identifiers.setBookingNumber(bookingNumber);
        identifiers.setBillOfLading(billOfLading);
        identifiers.setVesselName(vesselName);
        identifiers.setBmwShipmentID(bmwShipmentID);
        bmwMapping.setIdentifiers(identifiers);
    }
}