package com.camelot.p44camelotbmw.consumer;

import com.camelot.p44camelotbmw.constants.UuidGenerator;
import com.camelot.p44camelotbmw.db.model.BmwResponseModel;
import com.camelot.p44camelotbmw.db.model.P44IncomingModel;
import com.camelot.p44camelotbmw.db.repository.BmwResponseRepository;
import com.camelot.p44camelotbmw.db.repository.P44IncomingRepository;
import com.camelot.p44camelotbmw.entity.toBmwEntity.P44ToBmw;
import com.camelot.p44camelotbmw.p44JsonMapper.*;
import com.camelot.p44camelotbmw.producer.KafkaProducer;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Service
public class KafkaConsumerP44 {
    
    private static final Logger logger = LogManager.getLogger(KafkaConsumerP44.class);
    private final KafkaProducer producer;
    public P44IncomingRepository p44IncomingRepository;
    BmwResponseRepository bmwResponseRepository;
    private final RestTemplate restTemplate;
    private final RetryTemplate retryTemplate;
    @Autowired
    public KafkaConsumerP44(KafkaProducer producer, P44IncomingRepository p44IncomingRepository, BmwResponseRepository bmwResponseRepository, RestTemplate restTemplate, RetryTemplate retryTemplate) {
        this.producer = producer;
        this.p44IncomingRepository = p44IncomingRepository;
        this.bmwResponseRepository = bmwResponseRepository;
        this.restTemplate = restTemplate;
        this.retryTemplate = retryTemplate;
    }
    
    /*Development Consumer*/
    @KafkaListener(topics = "p44DataLocal", groupId = "p44DataLocalGroup")
    /*Production Consumer*/
    //@KafkaListener(topics = "p44DataTest", groupId = "bmwGroupTest")
    public void getP44Message(ConsumerRecord<String, String> record) {
        
        
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
        String correlationId = String.valueOf(UuidGenerator.get64MostSignificantBitsForVersion1());
        
        bmwMapping.setLifecycleStatus("");
        bmwMapping.setLifecycleStatusVerbose("");
        bmwMapping.setMainTransportMode("SEA");
        bmwMapping.setTransportationNetwork("SHIP");
        bmwMapping.setLeadTime("");
        bmwMapping.setCurrentLeadTimePerLeg("");
        bmwMapping.setCurrentLeadTimePickUpUntilCurrentTimestamp("");
        bmwMapping.setCurrentLeadTimePickUpUntilDelivery("");
        bmwMapping.setCurrentLeadTimePickUpUntilEta("");
        
        
        try {
            JsonObject shipment = (JsonObject) JsonParser.parseString(record.value());
            
            identifiersMapper.mapIdentifier(correlationId, shipment, bmwMapping);
            currentLocationInfoMapper.mapCurrLocInfo(shipment, bmwMapping);
            deliveryInformationMapper.mapDeliveryInformation(record.key().substring(5), shipment, bmwMapping);
            transportLegInfoMapper.mapTransportLegInfo(shipment, bmwMapping);
            
            recipientMapper.mapRecipient(shipment, bmwMapping);
            senderMapper.mapSender(shipment, bmwMapping);
            carrierMapper.mapCarrier(shipment, bmwMapping);
            containerDimensionsMapper.mapContainerDimension(shipment, bmwMapping);
            materialMapper.mapMaterial(shipment, bmwMapping);
            
            String containerID = bmwMapping.getIdentifier().getContainerId();
            String bmwJson = jsonStartingString + "\"" + containerID + "\"" + jsonStringValue + new Gson().toJson(bmwMapping) + jsonEndString;
            
            /*Temporary tracing of containers for Data validation*/
            if ((Arrays.asList("OOCU8134157", "MRKU2239322", "TGBU7938957", "TGBU9890615", "HLXU8042494", "MRKU2524084 ", "MSDU8752046")).contains(containerID)) {
                this.producer.writeLogMessage("test", "[" + shipment + "," + bmwJson + "]");
            }
            /*Delete above code after Temporary tracing of containers for Data validation is complete*/
            this.producer.writeBMWMessage("test-" + UuidGenerator.get64MostSignificantBitsForVersion1(), bmwJson);
            BmwResponseModel bmwResponseModel = new BmwResponseModel();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(bmwJson, headers);
            try {
                ResponseEntity<String> response = retryTemplate.execute(context -> restTemplate.postForEntity("https://p44-tracking-data-int.bmwgroup.com", entity, String.class));
                bmwResponseModel.setResponseCode(response.getStatusCode().toString());
                bmwResponseModel.setResponseMessage(response.getBody());
                bmwResponseModel.setCorrelationId(correlationId);
                bmwResponseModel.setTimestamp(OffsetDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSSX")));
                bmwResponseRepository.save(bmwResponseModel);
            } catch (Exception exception) {
                logger.error("Unable to push to BMW: " + exception);
                this.producer.writeBMWErrorMessage("test-" + exception.getMessage(), bmwJson);
            }
        } catch (Exception exception) {
            logger.error("Mapping Exception: " + exception + "\n" + record.value());
            this.producer.writeLogErrorMessage("test-" + "Mapping Failed", record.value());
            P44IncomingModel p44IncomingModel = new P44IncomingModel();
            JsonObject shipment = (JsonObject) JsonParser.parseString(record.value());
            p44IncomingModel.setInternalP44Identifier(shipment.get("shipment").getAsJsonObject().get("id").getAsString());
            p44IncomingModel.setTimestamp(OffsetDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSSX")));
            p44IncomingModel.setCause("Mapping Failed");
            p44IncomingRepository.save(p44IncomingModel);
        }
    }
}