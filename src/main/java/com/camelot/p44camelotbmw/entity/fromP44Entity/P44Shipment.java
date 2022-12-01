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
public class P44Shipment {
    
    @SerializedName("shipment")
    @Expose
    private Shipment shipment;
    @SerializedName("states")
    @Expose
    private List<State> states = null;
    @SerializedName("events")
    @Expose
    private List<Event> events = null;
    @SerializedName("positions")
    @Expose
    private List<Object> positions = null;
    
}