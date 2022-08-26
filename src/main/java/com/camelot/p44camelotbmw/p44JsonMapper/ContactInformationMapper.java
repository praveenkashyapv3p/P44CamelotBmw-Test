package com.camelot.p44camelotbmw.p44JsonMapper;

import com.camelot.p44camelotbmw.db.CreateShipment;
import com.camelot.p44camelotbmw.db.CreateShipmentRepository;
import com.camelot.p44camelotbmw.entity.toBmwEntity.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ContactInformationMapper {
    
    
    public void mapContactInfo(CreateShipmentRepository shipmentRepository, JsonObject shipmentJson, BMWMapping bmwMapping) {
        ContactInformation contactInformation = new ContactInformation();
        Sender sender = new Sender();
        Recepient recepient = new Recepient();
        Carrier carrier = new Carrier();
        String senderID = "", senderName = "", carrierIdFromGet = "", recipientID = "", recipientName = "", carrierID = "", carrierName = "";
    
        List<ContactInformation> contactInformation1 = new ArrayList<>();
        //JsonObject relShipIdentJSON = (JsonObject) JsonParser.parseString(shipmentJson);
        String ShipIdent = shipmentJson.get("shipment").getAsJsonObject().get("id").getAsString();
        String url = "https://na12.api.project44.com/api/v4/shipments/" + ShipIdent + "/tracking";
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("Camelot_Test@bmwsandbox.com", "c3hx>7U9^Y");
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> request = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        String jsonResponse = response.getBody();
        JsonObject shipmentIdJSON = (JsonObject) JsonParser.parseString(jsonResponse);
    
        List<CreateShipment> shipmentIds = shipmentRepository.findByMasterShipmentId(ShipIdent);
        for (CreateShipment carrierIdFromDB : shipmentIds) {
//            senderID = carrierIdFromDB.getSenderId();
//            senderName = carrierIdFromDB.getSenderName();
//            recipientID = carrierIdFromDB.getRecipientID();
//            recipientName = carrierIdFromDB.getRecipientName();
            carrierID = carrierIdFromDB.getCarrierID();
            carrierName = carrierIdFromDB.getCarrierName();
        }
        JsonArray attributes = (JsonArray) shipmentIdJSON.getAsJsonObject().get("attributes");
        JsonArray identifiers = (JsonArray) shipmentIdJSON.getAsJsonObject().get("identifiers");
        for (JsonElement id : identifiers) {
            carrierIdFromGet = id.getAsJsonObject().get("type").getAsString();
            if (carrierIdFromGet.equalsIgnoreCase("CARRIER_SCAC")) {
                carrierIdFromGet = id.getAsJsonObject().get("value").getAsString();
            }
        }
        for (JsonElement stp : attributes) {
            senderID = stp.getAsJsonObject().get("name").getAsString();
            if (senderID.equalsIgnoreCase("senderID")) {
                JsonArray senderIdArray = (JsonArray) stp.getAsJsonObject().get("values");
                senderID = senderIdArray.get(0).getAsString();
            }
            if (senderID.equalsIgnoreCase("senderName")) {
                JsonArray senderIdArray = (JsonArray) stp.getAsJsonObject().get("values");
                senderName = senderIdArray.get(0).getAsString();
            }
            if (senderID.equalsIgnoreCase("recipientID")) {
                JsonArray senderIdArray = (JsonArray) stp.getAsJsonObject().get("values");
                recipientID = senderIdArray.get(0).getAsString();
            }
            if (senderID.equalsIgnoreCase("recipientName")) {
                JsonArray senderIdArray = (JsonArray) stp.getAsJsonObject().get("values");
                recipientName = senderIdArray.get(0).getAsString();
            }
        }
    
        sender.setSenderID(senderID);
        sender.setName(senderName);
        recepient.setRecipientID(recipientID);
        recepient.setName(recipientName);
        if (carrierID.equalsIgnoreCase("")) {
            carrier.setCarrierID(carrierIdFromGet);
        } else {
            carrier.setCarrierID(carrierID);
        }
        carrier.setCarrierName(carrierName);
    
        contactInformation.setCarrier(carrier);
        contactInformation.setRecepient(recepient);
        contactInformation.setSender(sender);
        contactInformation1.add(contactInformation);
        bmwMapping.setContactInformation(contactInformation1);
    }
}