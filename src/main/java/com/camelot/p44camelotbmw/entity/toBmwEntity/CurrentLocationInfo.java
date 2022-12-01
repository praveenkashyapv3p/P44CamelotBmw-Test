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
public class CurrentLocationInfo {
    
    @SerializedName("statusCode")
    @Expose
    private String statusCode;
    @SerializedName("statusName")
    @Expose
    private String statusName;
    @SerializedName("timeStamp")
    @Expose
    private String timeStamp;
    @SerializedName("geoDateTimeUtc")
    @Expose
    private String geoDateTimeUtc;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("locationId")
    @Expose
    private String locationId;
    @SerializedName("locationName")
    @Expose
    private String locationName;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("sourceofPositionData")
    @Expose
    private String sourceofPositionData;
    
}