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
public class ContainerDimension {
    
    @SerializedName("totalVolume")
    @Expose
    private String totalVolume;
    @SerializedName("totalVolumeUnit")
    @Expose
    private String totalVolumeUnit;
    @SerializedName("totalWeight")
    @Expose
    private String totalWeight;
    @SerializedName("totalWeightUnit")
    @Expose
    private String totalWeightUnit;
    
}