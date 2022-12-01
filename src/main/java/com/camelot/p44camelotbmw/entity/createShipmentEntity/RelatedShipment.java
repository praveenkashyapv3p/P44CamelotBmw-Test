package com.camelot.p44camelotbmw.entity.createShipmentEntity;

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
public class RelatedShipment {
    
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("identifiers")
    @Expose
    private List<Identifier_relatedShipment> identifiers = null;
    @SerializedName("attributes")
    @Expose
    private List<Attribute_relatedShipments> attributes = null;
    
}