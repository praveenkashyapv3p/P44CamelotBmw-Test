package com.camelot.p44camelotbmw.db.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "bmwCreateShipment")
public class BmwCreateShipmentModel {
    @Id
    private String id;

//    @Field
//    @Indexed(name = "timestampIndex", expireAfterSeconds = 2592000)
//    private String timestamp;
    
    private String shipmentId;
    
    private String bookingNumber;
    
    private String billOfLading;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getShipmentId() {
        return shipmentId;
    }
    
    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }
    
    public String getBookingNumber() {
        return bookingNumber;
    }
    
    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }
    
    public String getBillOfLading() {
        return billOfLading;
    }
    
    public void setBillOfLading(String billOfLading) {
        this.billOfLading = billOfLading;
    }
    
    @Override
    public String toString() {
        return "BmwCreateShipmentModel{" +
                "id='" + id + '\'' +
                ", shipmentId='" + shipmentId + '\'' +
                ", bookingNumber='" + bookingNumber + '\'' +
                ", billOfLading='" + billOfLading + '\'' +
                '}';
    }
}