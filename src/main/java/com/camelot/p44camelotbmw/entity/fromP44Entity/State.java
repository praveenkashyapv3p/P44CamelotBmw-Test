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
public class State {
    
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("startDateTime")
    @Expose
    private String startDateTime;
    @SerializedName("endDateTime")
    @Expose
    private String endDateTime;
    @SerializedName("stopId")
    @Expose
    private String stopId;
    @SerializedName("routeSegmentId")
    @Expose
    private String routeSegmentId;
    
}