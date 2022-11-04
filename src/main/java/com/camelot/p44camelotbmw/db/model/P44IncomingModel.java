package com.camelot.p44camelotbmw.db.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "p44incoming")
public class P44IncomingModel {
    @Id
    private String id;
    private String internalP44Identifier;
    private String timestamp;
    private String cause;
    
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getInternalP44Identifier() {
        return internalP44Identifier;
    }
    
    public void setInternalP44Identifier(String internalP44Identifier) {
        this.internalP44Identifier = internalP44Identifier;
    }
    
    public String getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    
    public String getCause() {
        return cause;
    }
    
    public void setCause(String cause) {
        this.cause = cause;
    }
    
    @Override
    public String toString() {
        return "Model{" +
                "id='" + id + '\'' +
                ", internalP44Identifier='" + internalP44Identifier + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", cause='" + cause + '\'' +
                '}';
    }
}