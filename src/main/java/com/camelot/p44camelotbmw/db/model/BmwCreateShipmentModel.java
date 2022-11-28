package com.camelot.p44camelotbmw.db.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
    
    private String relatedShipmentId;
    
}