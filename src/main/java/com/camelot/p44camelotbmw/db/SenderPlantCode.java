package com.camelot.p44camelotbmw.db;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "senderPlantCode")
public class SenderPlantCode {
    @Id
    public String id;
    
    String packingPlantCode;
    String p44DisplayPackingPlant;
    String packingPlantName;
    String preCarriageFromLocation;
    
    
    public SenderPlantCode(String packingPlantCode, String p44DisplayPackingPlant, String packingPlantName, String preCarriageFromLocation) {
        this.packingPlantCode = packingPlantCode;
        this.p44DisplayPackingPlant = p44DisplayPackingPlant;
        this.packingPlantName = packingPlantName;
        this.preCarriageFromLocation = preCarriageFromLocation;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getPackingPlantCode() {
        return packingPlantCode;
    }
    
    public void setPackingPlantCode(String packingPlantCode) {
        this.packingPlantCode = packingPlantCode;
    }
    
    public String getP44DisplayPackingPlant() {
        return p44DisplayPackingPlant;
    }
    
    public void setP44DisplayPackingPlant(String p44DisplayPackingPlant) {
        this.p44DisplayPackingPlant = p44DisplayPackingPlant;
    }
    
    public String getPackingPlantName() {
        return packingPlantName;
    }
    
    public void setPackingPlantName(String packingPlantName) {
        this.packingPlantName = packingPlantName;
    }
    
    public String getPreCarriageFromLocation() {
        return preCarriageFromLocation;
    }
    
    public void setPreCarriageFromLocation(String preCarriageFromLocation) {
        this.preCarriageFromLocation = preCarriageFromLocation;
    }
    
    @Override
    public String toString() {
        return "SenderPlantCode{" +
                "id='" + id + '\'' +
                ", packingPlantCode='" + packingPlantCode + '\'' +
                ", p44DisplayPackingPlant='" + p44DisplayPackingPlant + '\'' +
                ", packingPlantName='" + packingPlantName + '\'' +
                ", preCarriageFromLocation='" + preCarriageFromLocation + '\'' +
                '}';
    }
}