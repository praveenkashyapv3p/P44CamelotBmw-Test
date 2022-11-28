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
@Document(collection = "p44incoming")
public class P44IncomingModel {
    @Id
    private String id;
    
    private String internalP44Identifier;
    
    @Field
    @Indexed(name = "timeStampP44pIndex", expireAfterSeconds = 2592000)
    private String timestamp;
    
    private String cause;
}