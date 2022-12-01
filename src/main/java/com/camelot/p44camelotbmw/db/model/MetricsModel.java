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
@Document(collection = "metrics")
public class MetricsModel {
    @Id
    private String id;
    
    
    
}