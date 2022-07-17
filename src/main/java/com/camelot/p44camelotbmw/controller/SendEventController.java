package com.camelot.p44camelotbmw.controller;

import com.camelot.p44camelotbmw.bmwentity.BMWMapping;
import com.camelot.p44camelotbmw.jsonmapper.*;
import com.camelot.p44camelotbmw.p44entity.P44Shipment;
import com.camelot.p44camelotbmw.producer.KafkaProducer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendEventController {
    private final KafkaProducer producer;


    public SendEventController(KafkaProducer producer) {
        this.producer = producer;
    }

    @PostMapping(value = "/v1/sendEvent", consumes = "application/json")
    public ResponseEntity<String> shipmentDetailsFromP44(@RequestBody String shipmentJson) {
//        Gson gson = new Gson();
//        String jsonStartingString = "{\"records\":[{\"key\":";
//        String jsonStringValue = ",\"value\":";
//        String jsonEndString = "}]}";
//        P44Shipment myObj = gson.fromJson(shipmentJson, new TypeToken<P44Shipment>() {
//        }.getType());
//        try{
//            identifiersMapping.mapIdentifiers(myObj, shipmentJson, bmwMapping);
//            contactInformationMapping.mapContactInfo(shipmentJson, bmwMapping);
//            currentLocationInfoMapper.mapCurrLocInfo(shipmentJson, bmwMapping);
//            bmwMapping.setTransportationNetwork("SHIP");
//            bmwMapping.setMainTransportMode("SEA");
//            transportLegMapper.mapTransportLegInfos(shipmentJson, bmwMapping);
//            technicalDetailsMapper.mapTechnicalDetails(shipmentJson, bmwMapping);
//            deliveryInformationMapper.mapDeliveryInformation(shipmentJson, bmwMapping);
//            String jsonKey = String.valueOf(TechnicalDetailsMapper.get64MostSignificantBitsForVersion1());
//            String bmwJson = jsonStartingString + jsonKey + jsonStringValue + new Gson().toJson(bmwMapping) + jsonEndString;
//            this.producer.writeP44Message(jsonKey, bmwJson);
//            return ResponseEntity.ok().build();//body(response.getBody());
//        }catch (Exception e){
//            return ResponseEntity.unprocessableEntity().build();
//        }
        try {
            String jsonKey = String.valueOf(TechnicalDetailsMapper.get64MostSignificantBitsForVersion1());
            this.producer.writeP44Message(jsonKey, shipmentJson);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
}




