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
public class RouteSegment {
    
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("fromStopId")
    @Expose
    private String fromStopId;
    @SerializedName("toStopId")
    @Expose
    private String toStopId;
    @SerializedName("transportationMode")
    @Expose
    private String transportationMode;
    @SerializedName("identifiers")
    @Expose
    private List<Identifier_RouteSegment> identifiers = null;
    
}