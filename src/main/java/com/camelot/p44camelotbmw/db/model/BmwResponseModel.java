package com.camelot.p44camelotbmw.db.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "bmwResponse")
public class BmwResponseModel {
    @Id
    private String id;
    
    private String responseCode;
    
    private String responseMessage;
    
    @Field
    @Indexed(name = "timestampIndex", expireAfterSeconds = 2592000)
    private String timestamp;
    
    private String correlationId;
    
}