package com.camelot.p44camelotbmw.entity.loadEntity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderIdentifierReference {
    
    @SerializedName("orderType")
    @Expose
    private String orderType;
    @SerializedName("orderIdentifier")
    @Expose
    private String orderIdentifier;
    
}