package com.camelot.p44camelotbmw.p44JsonMapper;

import com.camelot.p44camelotbmw.db.CreateShipment;
import com.camelot.p44camelotbmw.db.CreateShipmentRepository;
import com.camelot.p44camelotbmw.entity.toBmwEntity.*;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;


public class ContactInformationMapper {
    
    
    public void mapContactInfo(CreateShipmentRepository shipmentRepository, JsonObject shipmentJson, BMWMapping bmwMapping) {
        ContactInformation contactInformation = new ContactInformation();
        Sender sender = new Sender();
        Recepient recepient = new Recepient();
        Carrier carrier = new Carrier();
        String senderID = "", senderName = "", recipientID = "", recipientName = "", carrierID = "", carrierName = "";
        
        List<ContactInformation> contactInformation1 = new ArrayList<>();
        //JsonObject relShipIdentJSON = (JsonObject) JsonParser.parseString(shipmentJson);
        String ShipIdent = shipmentJson.get("shipment").getAsJsonObject().get("id").getAsString();
        List<CreateShipment> shipmentIds = shipmentRepository.findByMasterShipmentId(ShipIdent);
        for (CreateShipment carrierIdFromDB : shipmentIds) {
            senderID = carrierIdFromDB.getSenderId();
            senderName = carrierIdFromDB.getSenderName();
            recipientID = carrierIdFromDB.getRecipientID();
            recipientName = carrierIdFromDB.getRecipientName();
            carrierID = carrierIdFromDB.getCarrierID();
            carrierName = carrierIdFromDB.getCarrierName();
        }
        
        
        sender.setSenderID(senderID);
        sender.setName(senderName);
        recepient.setRecipientID(recipientID);
        recepient.setName(recipientName);
        carrier.setCarrierID(carrierID);
        carrier.setCarrierName(carrierName);
        
        contactInformation.setCarrier(carrier);
        contactInformation.setRecepient(recepient);
        contactInformation.setSender(sender);
        contactInformation1.add(contactInformation);
        bmwMapping.setContactInformation(contactInformation1);
    }
}