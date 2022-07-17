package com.camelot.p44camelotbmw.jsonmapper;

import com.camelot.p44camelotbmw.bmwentity.BMWMapping;
import com.camelot.p44camelotbmw.bmwentity.Carrier;
import com.camelot.p44camelotbmw.bmwentity.ContactInformation;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class ContactInformationMapper {

    ContactInformation contactInformation = new ContactInformation();

    Carrier carrier = new Carrier();

    public void mapContactInfo(String shipmentJson, BMWMapping bmwMapping) {
        List<ContactInformation> contactInformation1 = new ArrayList<>();
        JsonObject relShipIdentJSON = (JsonObject) JsonParser.parseString(shipmentJson);
        JsonArray relShipIdent = (JsonArray) relShipIdentJSON.get("shipment").getAsJsonObject().get("relatedShipments");
        for (JsonElement relIdent : relShipIdent) {
            JsonElement relShipIdentifiers = relIdent.getAsJsonObject().get("identifiers");
            JsonArray relIndent = relShipIdentifiers.getAsJsonArray();
            for (JsonElement relShipIdentifier : relIndent) {
                if ("CARRIER_SCAC".equals(relShipIdentifier.getAsJsonObject().get("type").getAsString()))
                    carrier.setCarrierID(relShipIdentifier.getAsJsonObject().get("value").getAsString());
            }
        }
        contactInformation.setCarrier(carrier);
        contactInformation1.add(contactInformation);
        bmwMapping.setContactInformation(contactInformation1);
        //System.out.println("contactinfo: " + carrier);
    }
}
