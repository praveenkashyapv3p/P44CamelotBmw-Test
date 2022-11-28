package com.camelot.p44camelotbmw.p44JsonMapper;

import com.camelot.p44camelotbmw.constants.UuidGenerator;
import com.camelot.p44camelotbmw.entity.toBmwEntity.P44ToBmw;
import com.camelot.p44camelotbmw.producer.KafkaProducer;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MessageMapper {
    
    private static final Logger logger = LogManager.getLogger(MessageMapper.class);
    
    public Map<String, String> mapMessage(String eventCreationDateTimeUTC, String message, KafkaProducer producer, String correlationId) {
    
        P44ToBmw bmwMapping = new P44ToBmw();
        RecipientMapper recipientMapper = new RecipientMapper();
        SenderMapper senderMapper = new SenderMapper();
        CarrierMapper carrierMapper = new CarrierMapper();
        CurrentLocationInfoMapper currentLocationInfoMapper = new CurrentLocationInfoMapper();
        DeliveryInformationMapper deliveryInformationMapper = new DeliveryInformationMapper();
        IdentifiersMapper identifiersMapper = new IdentifiersMapper();
        TransportLegInfoMapper transportLegInfoMapper = new TransportLegInfoMapper();
        ContainerDimensionsMapper containerDimensionsMapper = new ContainerDimensionsMapper();
        MaterialMapper materialMapper = new MaterialMapper();
    
        String jsonStartingString = "{\"records\":[{\"key\":";
        String jsonStringValue = ",\"value\":";
        String jsonEndString = "}]}";
    
        bmwMapping.setLifecycleStatus("");
        bmwMapping.setLifecycleStatusVerbose("");
        bmwMapping.setMainTransportMode("SEA");
        bmwMapping.setTransportationNetwork("SHIP");
        bmwMapping.setLeadTime("");
        bmwMapping.setCurrentLeadTimePerLeg("");
        bmwMapping.setCurrentLeadTimePickUpUntilCurrentTimestamp("");
        bmwMapping.setCurrentLeadTimePickUpUntilDelivery("");
        bmwMapping.setCurrentLeadTimePickUpUntilEta("");
    
        Map<String, String> statusMessage = new HashMap<>();
        String bmwJson = "";
        try {
            JsonObject shipment = (JsonObject) JsonParser.parseString(message);
    
            identifiersMapper.mapIdentifier(correlationId, shipment, bmwMapping);
            currentLocationInfoMapper.mapCurrLocInfo(shipment, bmwMapping);
            deliveryInformationMapper.mapDeliveryInformation(eventCreationDateTimeUTC, shipment, bmwMapping);
            transportLegInfoMapper.mapTransportLegInfo(shipment, bmwMapping);
    
            recipientMapper.mapRecipient(shipment, bmwMapping);
            senderMapper.mapSender(shipment, bmwMapping);
            carrierMapper.mapCarrier(shipment, bmwMapping);
            containerDimensionsMapper.mapContainerDimension(shipment, bmwMapping);
            materialMapper.mapMaterial(shipment, bmwMapping);
    
            String containerID = bmwMapping.getIdentifier().getContainerId();
            bmwJson = jsonStartingString + "\"" + containerID + "\"" + jsonStringValue + new Gson().toJson(bmwMapping) + jsonEndString;
    
            /*Temporary tracing of containers for Data validation*/
            if ((Arrays.asList("OOCU8134157", "MRKU2239322", "TGBU7938957", "TGBU9890615", "HLXU8042494", "MRKU2524084 ", "MSDU8752046")).contains(containerID)) {
                producer.writeLogMessage("test", "[" + shipment + "," + bmwJson + "]");
            }
            /*Delete above code after Temporary tracing of containers for Data validation is complete*/
            producer.writeBMWMessage("test-" + UuidGenerator.get64MostSignificantBitsForVersion1(), bmwJson);
            statusMessage.put("success", bmwJson);
            return statusMessage;
        } catch (Exception exception) {
            logger.error("Mapping Exception: " + exception + "\n" + message);
            statusMessage.put("Mapping Failed", message);
            return statusMessage;
        }
    }
}