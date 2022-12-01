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
public class RouteInfo {
    
    @SerializedName("stops")
    @Expose
    private List<Stop> stops = null;
    @SerializedName("routeSegments")
    @Expose
    private List<RouteSegment> routeSegments = null;
    
}