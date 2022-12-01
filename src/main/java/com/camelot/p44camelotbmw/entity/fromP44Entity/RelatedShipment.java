package com.camelot.p44camelotbmw.entity.fromP44Entity;

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
    private List<Identifier_RelatedShipment> identifiers = null;
    @SerializedName("attributes")
    @Expose
    private List<Object> attributes = null;
    @SerializedName("shipmentShareLink")
    @Expose
    private String shipmentShareLink;
    
}