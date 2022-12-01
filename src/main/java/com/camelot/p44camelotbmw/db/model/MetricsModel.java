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
@Document(collection = "testmetrics")
public class MetricsModel {
    @Id
    private String id;
    
    private String date;
    private long received;
    private long mapped;
    private long sent;
    
}