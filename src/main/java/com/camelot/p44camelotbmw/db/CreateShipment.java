package com.camelot.p44camelotbmw.db;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "createShipment")
public class CreateShipment {
    @Id
    public String id;
    
    public String masterShipmentId;
    public String containerId;
    public String bmwShipmentId;
    public String transportationNetwork;
    public String senderId;
    public String senderName;
    public String recipientID;
    public String recipientName;
    public String recipientUnloadingPoint;
    public String carrierID;
    public String carrierP44ID;
    public String carrierName;
    public String planPickUpDate;
    public String planDeliveryDate;
    public String totalWeightKGS;
    public String totalVolumeCBM;
    public String materials;
    
    public CreateShipment() {
    }
    
    public CreateShipment(String masterShipmentId, String containerId, String bmwShipmentId, String transportationNetwork, String senderId, String senderName, String recipientID, String recipientName, String recipientUnloadingPoint, String carrierID, String carrierP44ID, String carrierName, String planPickUpDate, String planDeliveryDate, String totalWeightKGS, String totalVolumeCBM, String materials) {
        this.masterShipmentId = masterShipmentId;
        this.containerId = containerId;
        this.bmwShipmentId = bmwShipmentId;
        this.transportationNetwork = transportationNetwork;
        this.senderId = senderId;
        this.senderName = senderName;
        this.recipientID = recipientID;
        this.recipientName = recipientName;
        this.recipientUnloadingPoint = recipientUnloadingPoint;
        this.carrierID = carrierID;
        this.carrierP44ID = carrierP44ID;
        this.carrierName = carrierName;
        this.planPickUpDate = planPickUpDate;
        this.planDeliveryDate = planDeliveryDate;
        this.totalWeightKGS = totalWeightKGS;
        this.totalVolumeCBM = totalVolumeCBM;
        this.materials = materials;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getMasterShipmentId() {
        return masterShipmentId;
    }
    
    public void setMasterShipmentId(String masterShipmentId) {
        this.masterShipmentId = masterShipmentId;
    }
    
    public String getContainerId() {
        return containerId;
    }
    
    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }
    
    public String getBmwShipmentId() {
        return bmwShipmentId;
    }
    
    public void setBmwShipmentId(String bmwShipmentId) {
        this.bmwShipmentId = bmwShipmentId;
    }
    
    public String getTransportationNetwork() {
        return transportationNetwork;
    }
    
    public void setTransportationNetwork(String transportationNetwork) {
        this.transportationNetwork = transportationNetwork;
    }
    
    public String getSenderId() {
        return senderId;
    }
    
    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }
    
    public String getSenderName() {
        return senderName;
    }
    
    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
    
    public String getRecipientID() {
        return recipientID;
    }
    
    public void setRecipientID(String recipientID) {
        this.recipientID = recipientID;
    }
    
    public String getRecipientName() {
        return recipientName;
    }
    
    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }
    
    public String getRecipientUnloadingPoint() {
        return recipientUnloadingPoint;
    }
    
    public void setRecipientUnloadingPoint(String recipientUnloadingPoint) {
        this.recipientUnloadingPoint = recipientUnloadingPoint;
    }
    
    public String getCarrierID() {
        return carrierID;
    }
    
    public void setCarrierID(String carrierID) {
        this.carrierID = carrierID;
    }
    
    public String getCarrierP44ID() {
        return carrierP44ID;
    }
    
    public void setCarrierP44ID(String carrierP44ID) {
        this.carrierP44ID = carrierP44ID;
    }
    
    public String getCarrierName() {
        return carrierName;
    }
    
    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }
    
    public String getPlanPickUpDate() {
        return planPickUpDate;
    }
    
    public void setPlanPickUpDate(String planPickUpDate) {
        this.planPickUpDate = planPickUpDate;
    }
    
    public String getPlanDeliveryDate() {
        return planDeliveryDate;
    }
    
    public void setPlanDeliveryDate(String planDeliveryDate) {
        this.planDeliveryDate = planDeliveryDate;
    }
    
    public String getTotalWeightKGS() {
        return totalWeightKGS;
    }
    
    public void setTotalWeightKGS(String totalWeightKGS) {
        this.totalWeightKGS = totalWeightKGS;
    }
    
    public String getTotalVolumeCBM() {
        return totalVolumeCBM;
    }
    
    public void setTotalVolumeCBM(String totalVolumeCBM) {
        this.totalVolumeCBM = totalVolumeCBM;
    }
    
    public String getMaterials() {
        return materials;
    }
    
    public void setMaterials(String materials) {
        this.materials = materials;
    }
    
    @Override
    public String toString() {
        return "CreateShipment{" + "id='" + id + '\'' + ", masterShipmentId='" + masterShipmentId + '\'' + ", containerId='" + containerId + '\'' + ", bmwShipmentId='" + bmwShipmentId + '\'' + ", transportationNetwork='" + transportationNetwork + '\'' + ", senderId='" + senderId + '\'' + ", senderName='" + senderName + '\'' + ", recipientID='" + recipientID + '\'' + ", recipientName='" + recipientName + '\'' + ", recipientUnloadingPoint='" + recipientUnloadingPoint + '\'' + ", carrierID='" + carrierID + '\'' + ", carrierP44ID='" + carrierP44ID + '\'' + ", carrierName='" + carrierName + '\'' + ", planPickUpDate='" + planPickUpDate + '\'' + ", planDeliveryDate='" + planDeliveryDate + '\'' + ", totalWeightKGS='" + totalWeightKGS + '\'' + ", totalVolumeCBM='" + totalVolumeCBM + '\'' + ", materials='" + materials + '\'' + '}';
    }
}