package com.camelot.p44camelotbmw.consumer;

import com.camelot.p44camelotbmw.bmwentity.BMWMapping;
import com.camelot.p44camelotbmw.jsonmapper.*;
import com.camelot.p44camelotbmw.p44entity.P44Shipment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class KafkaConsumerBMW {
    BMWMapping bmwMapping = new BMWMapping();
    IdentifiersMapper identifiersMapping = new IdentifiersMapper();
    ContactInformationMapper contactInformationMapping = new ContactInformationMapper();
    CurrentLocationInfoMapper currentLocationInfoMapper = new CurrentLocationInfoMapper();
    TransportLegMapper transportLegMapper = new TransportLegMapper();
    TechnicalDetailsMapper technicalDetailsMapper = new TechnicalDetailsMapper();
    DeliveryInformationMapper deliveryInformationMapper = new DeliveryInformationMapper();
    @Autowired
    private RestTemplate restTemplate;
    
    @KafkaListener(topics = "p44Data", groupId = "bmwGroup")
    public void getTransformedMessage(String message) {
        Gson gson = new Gson();
        String jsonStartingString = "{\"records\":[{\"key\":";
        String jsonStringValue = ",\"value\":";
        String jsonEndString = "}]}";
        P44Shipment myObj = gson.fromJson(message, new TypeToken<P44Shipment>() {
        }.getType());
        try {
            identifiersMapping.mapIdentifiers(myObj, message, bmwMapping);
            contactInformationMapping.mapContactInfo(message, bmwMapping);
            currentLocationInfoMapper.mapCurrLocInfo(message, bmwMapping);
            bmwMapping.setTransportationNetwork("SHIP");
            bmwMapping.setMainTransportMode("SEA");
            transportLegMapper.mapTransportLegInfos(message, bmwMapping);
            technicalDetailsMapper.mapTechnicalDetails(message, bmwMapping);
            deliveryInformationMapper.mapDeliveryInformation(message, bmwMapping);
            String jsonKey = String.valueOf(TechnicalDetailsMapper.get64MostSignificantBitsForVersion1());
            String bmwJson = jsonStartingString + jsonKey + jsonStringValue + new Gson().toJson(bmwMapping) + jsonEndString;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(bmwJson, headers);
            ResponseEntity<String> response = restTemplate.postForEntity("https://p44-tracking-data-int.bmwgroup.com", entity, String.class);
            System.out.println("response: " + response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}