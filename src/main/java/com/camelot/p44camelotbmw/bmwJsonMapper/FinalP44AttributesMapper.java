package com.camelot.p44camelotbmw.bmwJsonMapper;

import com.camelot.p44camelotbmw.consumer.KafkaConsumerBMW;
import com.camelot.p44camelotbmw.entity.loadEntity.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class FinalP44AttributesMapper {
    
    private static final Logger logger = LogManager.getLogger(KafkaConsumerBMW.class);
    
    public void getAllAttributes(String shipmentId, String origin, String destination, String bookingNum, String carrierId, String materials) {
        RestTemplate restTemplate = new RestTemplate();
        LoadEntity loadEntity = new LoadEntity();
        loadEntity.setMasterShipmentId(shipmentId);
        PickupStopReference pickupStopReference = new PickupStopReference();
        pickupStopReference.setStopId(origin);
        loadEntity.setPickupStopReference(pickupStopReference);
        DeliveryStopReference deliveryStopReference = new DeliveryStopReference();
        deliveryStopReference.setStopId(destination);
        loadEntity.setDeliveryStopReference(deliveryStopReference);
        List<Identifier> identifierList = new ArrayList<>();
        Identifier identifierBook = new Identifier();
        identifierBook.setType("BOOKING_NUMBER");
        identifierBook.setValue(bookingNum);
        identifierList.add(identifierBook);
        Identifier identifierCarrier = new Identifier();
        identifierCarrier.setType("CARRIER_SCAC");
        identifierCarrier.setValue(carrierId);
        identifierList.add(identifierCarrier);
        loadEntity.setIdentifiers(identifierList);
        loadEntity.setDescription("");
        List<Item> itemList = new ArrayList<>();
        
        JsonArray materialsJsonArray = JsonParser.parseString(materials).getAsJsonArray();
        for (JsonElement material : materialsJsonArray) {
            Item item = new Item();
            List<OrderIdentifierReference> orderIdentifierReferenceList = new ArrayList<>();
            OrderIdentifierReference orderIdentifierReference = new OrderIdentifierReference();
            orderIdentifierReference.setOrderType("PURCHASE_ORDER");
            orderIdentifierReference.setOrderIdentifier(material.getAsJsonObject().get("purchaseOrder").getAsString());
            orderIdentifierReferenceList.add(orderIdentifierReference);
            item.setOrderIdentifierReferences(orderIdentifierReferenceList);
            item.setUnitQuantity(material.getAsJsonObject().get("quantity").getAsString());
            item.setUnitType("EACH");
            item.setDescription(material.getAsJsonObject().get("deliverNoteNumber").getAsString());
            List<Identifier_items> identifierItemsList = new ArrayList<>();
            Identifier_items identifier_items = new Identifier_items();
            identifier_items.setType("STOCK_KEEPING_UNIT");
            identifier_items.setValue(material.getAsJsonObject().get("materialNumber").getAsString());
            identifierItemsList.add(identifier_items);
            item.setIdentifiers(identifierItemsList);
            itemList.add(item);
        }
        loadEntity.setItems(itemList);
        
        Gson gson = new Gson();
        String requestBody = gson.toJson(loadEntity);
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("Camelot_Test@bmwsandbox.com", "c3hx>7U9^Y");
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("https://na12.api.project44.com/api/v4/loads", entity, String.class);
    }
}