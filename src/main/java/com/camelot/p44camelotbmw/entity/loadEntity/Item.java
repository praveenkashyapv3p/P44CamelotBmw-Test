package com.camelot.p44camelotbmw.entity.loadEntity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.List;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Item {
    
    @SerializedName("orderIdentifierReferences")
    @Expose
    private List<OrderIdentifierReference> orderIdentifierReferences = null;
    @SerializedName("unitQuantity")
    @Expose
    private String unitQuantity;
    @SerializedName("unitType")
    @Expose
    private String unitType;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("identifiers")
    @Expose
    private List<Identifier_items> identifiers = null;
    
}