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
            if ((Arrays.asList("CAIU7053452", "CMAU7681240", "FSCU8704094", "CSNU6428681", "MSKU8538003", "TLLU6848274", "EITU9074179", "TGBU8621308", "HASU4670368", "MSKU9718864", "FANU1740651", "TEMU1608650", "KOCU4488754", "MSKU6889462", "MRSU3202341", "FCIU7369829", "FFAU3068178", "TCNU7686182", "NYKU5107789", "CAIU9003698", "OOCU7340261")).contains(containerID)) {
                producer.writeLogMessage(String.valueOf(UuidGenerator.get64MostSignificantBitsForVersion1()), bmwJson);
            }
            /*Delete above code after Temporary tracing of containers for Data validation is complete*/
            producer.writeBMWMessage(String.valueOf(UuidGenerator.get64MostSignificantBitsForVersion1()), bmwJson);
            return bmwJson;
        } catch (Exception exception) {
            logger.error("Mapping Exception" + exception);
            return bmwJson;
        }
    }
}