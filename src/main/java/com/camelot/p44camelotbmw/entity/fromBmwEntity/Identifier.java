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
public class Identifier {
    
    @SerializedName("containerId")
    @Expose
    private String containerId;
    @SerializedName("billOfLading")
    @Expose
    private String billOfLading;
    @SerializedName("bmwShipmentId")
    @Expose
    private String bmwShipmentId;
    @SerializedName("bookingNumber")
    @Expose
    private String bookingNumber;
    
}