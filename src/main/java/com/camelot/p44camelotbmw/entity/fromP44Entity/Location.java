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
public class Location {
    
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("identifiers")
    @Expose
    private List<Identifier_Location> identifiers = null;
    @SerializedName("coordinates")
    @Expose
    private Coordinates coordinates;
    
}