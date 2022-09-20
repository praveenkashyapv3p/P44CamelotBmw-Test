package com.camelot.p44camelotbmw.bmwJsonMapper;

import com.camelot.p44camelotbmw.entity.createShipmentEntity.*;

import java.util.ArrayList;
import java.util.List;

public class CreateShipmentMapper {
    /*
     * Mapping for Request body of Step 1 in create shipment process from BMW to Project44
     */
    public CreateShipmentP44 mapCreateShipment(CreateShipmentP44 createShipmentP44, String containerID, String bmwShipmentId,
                                               String bookingNumber, String billOfLading, String bmwBusinessRelation, String senderId,
                                               String senderName, String recipientID, String recipientName,
                                               String recipientUnloadingPoint, String carrierId, String carrierP44ID,
                                               String carrierName, String planPickUpDate, String planDeliveryDate, String totalWeight,
                                               String totalVolume) {
    
        String p44BookingNumber = "";
        List<Identifier> identifierList = new ArrayList<>();
    
        /*
         * Identifiers
         */
        Identifier identifierBook = new Identifier();
        //with billOfLading as first priority, map @BOOKING_NUMBER for either billOfLading of bookingNumber
        if (!billOfLading.equals("")) {
            p44BookingNumber = billOfLading;
        } else if (!bookingNumber.equals("")) {
            p44BookingNumber = bookingNumber;
        }
        identifierBook.setType("BOOKING_NUMBER");
        identifierBook.setValue(p44BookingNumber);
        identifierList.add(identifierBook);
        Identifier identifierCarrier = new Identifier();
        identifierCarrier.setType("CARRIER_SCAC");
        identifierCarrier.setValue(carrierP44ID);
        identifierList.add(identifierCarrier);
        createShipmentP44.setIdentifiers(identifierList);
    
        /*
         * Attributes
         */
        List<Attribute> attributeList = new ArrayList<>();
        Attribute attributeRecUnloadingPnt = new Attribute();
        attributeRecUnloadingPnt.setName("recipientUnloadingPoint");
        attributeRecUnloadingPnt.setValue(recipientUnloadingPoint);
        attributeList.add(attributeRecUnloadingPnt);
    
        Attribute attributeRecName = new Attribute();
        attributeRecName.setName("recipientName");
        attributeRecName.setValue(recipientName);
        attributeList.add(attributeRecName);
    
        Attribute attributeRecId = new Attribute();
        attributeRecId.setName("recipientID");
        attributeRecId.setValue(recipientID);
        attributeList.add(attributeRecId);
    
        Attribute attributeSenderName = new Attribute();
        attributeSenderName.setName("senderName");
        attributeSenderName.setValue(senderName);
        attributeList.add(attributeSenderName);
    
        Attribute attributeSenderId = new Attribute();
        attributeSenderId.setName("senderID");
        attributeSenderId.setValue(senderId);
        attributeList.add(attributeSenderId);
    
        Attribute attributeBmwShipmentId = new Attribute();
        attributeBmwShipmentId.setName("SAP Carrier details");
        attributeBmwShipmentId.setValue(bmwShipmentId);
        attributeList.add(attributeBmwShipmentId);
    
        Attribute attributeBusinessUnit = new Attribute();
        attributeBusinessUnit.setName("Business unit");
        attributeBusinessUnit.setValue(bmwBusinessRelation);
        attributeList.add(attributeBusinessUnit);
    
        Attribute attributeCarrierName = new Attribute();
        attributeCarrierName.setName("Main haulage carrier name");
        attributeCarrierName.setValue(carrierName);
        attributeList.add(attributeCarrierName);
    
        Attribute attributeCarrierId = new Attribute();
        attributeCarrierId.setName("Main haulage carrier");
        attributeCarrierId.setValue(carrierId);
        attributeList.add(attributeCarrierId);
    
        createShipmentP44.setAttributes(attributeList);
    
        /*
         * relatedShipment
         */
        List<RelatedShipment> relatedShipmentList = new ArrayList<>();
        RelatedShipment relatedShipment = new RelatedShipment();
        List<Identifier_relatedShipment> identifierRelatedShipmentList = new ArrayList<>();
        Identifier_relatedShipment identifierRelatedShipmentContainerID = new Identifier_relatedShipment();
        identifierRelatedShipmentContainerID.setType("CONTAINER_ID");
        identifierRelatedShipmentContainerID.setValue(containerID);
        identifierRelatedShipmentList.add(identifierRelatedShipmentContainerID);
    
        Identifier_relatedShipment identifierRelatedShipmentCarrierID = new Identifier_relatedShipment();
        identifierRelatedShipmentCarrierID.setType("CARRIER_SCAC");
        identifierRelatedShipmentCarrierID.setValue(carrierP44ID);
        identifierRelatedShipmentList.add(identifierRelatedShipmentCarrierID);
        relatedShipment.setIdentifiers(identifierRelatedShipmentList);
        
        List<Attribute_relatedShipments> attributeRelatedShipmentsList = new ArrayList<>();
        Attribute_relatedShipments attribute_relatedShipmentsPickUp = new Attribute_relatedShipments();
        attribute_relatedShipmentsPickUp.setName("planPickUpDate");
        attribute_relatedShipmentsPickUp.setValue(planPickUpDate);
        attributeRelatedShipmentsList.add(attribute_relatedShipmentsPickUp);
        Attribute_relatedShipments attributeRelatedShipmentsDelivery = new Attribute_relatedShipments();
        attributeRelatedShipmentsDelivery.setName("planDeliveryDate");
        attributeRelatedShipmentsDelivery.setValue(planDeliveryDate);
        attributeRelatedShipmentsList.add(attributeRelatedShipmentsDelivery);
        Attribute_relatedShipments attributeRelatedShipmentsVol = new Attribute_relatedShipments();
        attributeRelatedShipmentsVol.setName("totalVolumeCBM");
        attributeRelatedShipmentsVol.setValue(totalVolume);
        attributeRelatedShipmentsList.add(attributeRelatedShipmentsVol);
        Attribute_relatedShipments attributeRelatedShipmentsWeight = new Attribute_relatedShipments();
        attributeRelatedShipmentsWeight.setName("totalWeightKGS");
        attributeRelatedShipmentsWeight.setValue(totalWeight);
        attributeRelatedShipmentsList.add(attributeRelatedShipmentsWeight);
        relatedShipment.setAttributes(attributeRelatedShipmentsList);
        
        relatedShipmentList.add(relatedShipment);
        createShipmentP44.setRelatedShipments(relatedShipmentList);
        return createShipmentP44;
    }
}