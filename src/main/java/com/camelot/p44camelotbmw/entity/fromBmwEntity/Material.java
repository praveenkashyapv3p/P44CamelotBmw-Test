package com.camelot.p44camelotbmw.entity.fromBmwEntity;

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
public class Material {
    
    @SerializedName("materialNumber")
    @Expose
    private String materialNumber;
    @SerializedName("purchaseOrder")
    @Expose
    private String purchaseOrder;
    @SerializedName("deliveryNoteNumber")
    @Expose
    private String deliveryNoteNumber;
    @SerializedName("p44Quantity")
    @Expose
    private String p44Quantity;
    @SerializedName("bmwQuantity")
    @Expose
    private String bmwQuantity;
    @SerializedName("quantityUnit")
    @Expose
    private String quantityUnit;
    
}