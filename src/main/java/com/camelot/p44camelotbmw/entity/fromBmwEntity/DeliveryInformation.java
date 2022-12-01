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
public class DeliveryInformation {
    
    @SerializedName("planDeliveryDate")
    @Expose
    private String planDeliveryDate;
    @SerializedName("planPickUpDate")
    @Expose
    private String planPickUpDate;
    
}