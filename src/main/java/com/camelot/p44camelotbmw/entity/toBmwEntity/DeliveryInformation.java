package com.camelot.p44camelotbmw.entity.toBmwEntity;

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
    
    @SerializedName("etaDateTimeUtc")
    @Expose
    private String etaDateTimeUtc;
    @SerializedName("etaDateRoutePartUtc")
    @Expose
    private String etaDateRoutePartUtc;
    @SerializedName("etdDateNextRoutePartUtc")
    @Expose
    private String etdDateNextRoutePart;
    @SerializedName("eventCreationDateTimeUtc")
    @Expose
    private String eventCreationDateTimeUtc;
    @SerializedName("eventSendingDateTimeUtc")
    @Expose
    private String eventSendingDateTimeUtc;
    @SerializedName("planDeliveryDate")
    @Expose
    private String planDeliveryDate;
    @SerializedName("planPickUpDate")
    @Expose
    private String planPickUpDate;
    
}