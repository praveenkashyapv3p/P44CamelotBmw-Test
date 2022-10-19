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

public class MessageMapper {
    
    private static final Logger logger = LogManager.getLogger(MessageMapper.class);
    
    public String mapMessage(String message, KafkaProducer producer) {
    
        P44ToBmw bmwMapping = new P44ToBmw();
        RecipientMapper recipientMapper = new RecipientMapper();
        SenderMapper senderMapper = new SenderMapper();
        CarrierMapper carrierMapper = new CarrierMapper();
        CurrentLocationInfoMapper currentLocationInfoMapper = new CurrentLocationInfoMapper();
        DeliveryInformationMapper deliveryInformationMapper = new DeliveryInformationMapper();
        IdentifiersMapper identifiersMapper = new IdentifiersMapper(producer);
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
    
        String bmwJson = "";
        try {
            JsonObject shipment = (JsonObject) JsonParser.parseString(message);
    
            recipientMapper.mapRecipient(shipment, bmwMapping);
            senderMapper.mapSender(shipment, bmwMapping);
            carrierMapper.mapCarrier(shipment, bmwMapping);
            currentLocationInfoMapper.mapCurrLocInfo(shipment, bmwMapping);
            deliveryInformationMapper.mapDeliveryInformation(shipment, bmwMapping);
            identifiersMapper.mapIdentifier(String.valueOf(UuidGenerator.get64MostSignificantBitsForVersion1()), shipment, bmwMapping);
            transportLegInfoMapper.mapTransportLegInfo(shipment, bmwMapping);
            containerDimensionsMapper.mapContainerDimension(shipment, bmwMapping);
            materialMapper.mapMaterial(shipment, bmwMapping);
            String containerID = bmwMapping.getIdentifier().getContainerId();
            bmwJson = jsonStartingString + "\"" + containerID + "\"" + jsonStringValue + new Gson().toJson(bmwMapping) + jsonEndString;
    
            /*Temporary tracing of containers for Data validation*/
            if ((Arrays.asList("TXGU5345195", "CAIU7821020", "FFAU4281892", "HLBU2516048", "INKU6646068", "MRKU5030927", "MRSU4502596", "TCNU6440363", "MRKU5784526", "CIPU5007854", "MRKU5543278", "TRHU6654055", "TLLU8817673", "BMOU5648580", "CAIU7815835")).contains(containerID)) {
                producer.writeLogMessage("test", bmwJson);
            }
            /*Delete above code after Temporary tracing of containers for Data validation is complete*/
            producer.writeBMWMessage(String.valueOf(UuidGenerator.get64MostSignificantBitsForVersion1()), bmwJson);
            return bmwJson;
        } catch (Exception exception) {
            logger.error("Mapping Exception" + exception + "\n" + message);
            return bmwJson;
        }
    }
}