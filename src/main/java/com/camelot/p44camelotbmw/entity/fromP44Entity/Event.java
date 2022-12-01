package com.camelot.p44camelotbmw.entity.fromP44Entity;

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
public class Event {
    
    @SerializedName("dateTime")
    @Expose
    private String dateTime;
    @SerializedName("receivedDateTime")
    @Expose
    private String receivedDateTime;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("stopId")
    @Expose
    private String stopId;
    @SerializedName("plannedDateTime")
    @Expose
    private String plannedDateTime;
    @SerializedName("routeSegmentId")
    @Expose
    private String routeSegmentId;
    @SerializedName("estimateDateTime")
    @Expose
    private String estimateDateTime;
    @SerializedName("estimateLastCalculatedDateTime")
    @Expose
    private String estimateLastCalculatedDateTime;
    
}