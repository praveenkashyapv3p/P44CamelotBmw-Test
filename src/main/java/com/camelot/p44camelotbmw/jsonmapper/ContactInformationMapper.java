package com.camelot.p44camelotbmw.jsonmapper;

import com.camelot.p44camelotbmw.bmwentity.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class ContactInformationMapper {
    
    
    public void mapContactInfo(String shipmentJson, BMWMapping bmwMapping) {
        ContactInformation contactInformation = new ContactInformation();
        Sender sender = new Sender();
        Recepient recepient = new Recepient();
        Carrier carrier = new Carrier();
        String senderID = "", senderName = "", recipientID = "", recipientName = "", carrierID = "", carrierName = "";
        
        List<ContactInformation> contactInformation1 = new ArrayList<>();
        JsonObject relShipIdentJSON = (JsonObject) JsonParser.parseString(shipmentJson);
        JsonArray relShipIdent = (JsonArray) relShipIdentJSON.get("shipment").getAsJsonObject().get("relatedShipments");
        for (JsonElement relIdent : relShipIdent) {
            JsonElement relShipIdentifiers = relIdent.getAsJsonObject().get("identifiers");
            JsonArray relIndent = relShipIdentifiers.getAsJsonArray();
            for (JsonElement relShipIdentifier : relIndent) {
                if ("CARRIER_SCAC".equals(relShipIdentifier.getAsJsonObject().get("type").getAsString())) {
                    carrierID = relShipIdentifier.getAsJsonObject().get("value").getAsString();
                    carrier.setCarrierID(carrierID);
                }
            }
        }
        
        sender.setSenderID(senderID);
        sender.setName(senderName);
        recepient.setRecipientID(recipientID);
        recepient.setName(recipientName);
        carrier.setCarrierName(carrierName);
        
        
        contactInformation.setCarrier(carrier);
        contactInformation.setRecepient(recepient);
        contactInformation.setSender(sender);
        contactInformation1.add(contactInformation);
        bmwMapping.setContactInformation(contactInformation1);
    }
}