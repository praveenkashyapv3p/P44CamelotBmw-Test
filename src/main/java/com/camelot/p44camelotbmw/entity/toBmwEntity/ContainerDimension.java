package com.camelot.p44camelotbmw.entity.toBmwEntity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
    
    public String getTotalVolume() {
        return totalVolume;
    }
    
    public void setTotalVolume(String totalVolume) {
        this.totalVolume = totalVolume;
    }
    
    public String getTotalVolumeUnit() {
        return totalVolumeUnit;
    }
    
    public void setTotalVolumeUnit(String totalVolumeUnit) {
        this.totalVolumeUnit = totalVolumeUnit;
    }
    
    public String getTotalWeight() {
        return totalWeight;
    }
    
    public void setTotalWeight(String totalWeight) {
        this.totalWeight = totalWeight;
    }
    
    public String getTotalWeightUnit() {
        return totalWeightUnit;
    }
    
    public void setTotalWeightUnit(String totalWeightUnit) {
        this.totalWeightUnit = totalWeightUnit;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ContainerDimension.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("totalVolume");
        sb.append('=');
        sb.append(((this.totalVolume == null) ? "<null>" : this.totalVolume));
        sb.append(',');
        sb.append("totalVolumeUnit");
        sb.append('=');
        sb.append(((this.totalVolumeUnit == null) ? "<null>" : this.totalVolumeUnit));
        sb.append(',');
        sb.append("totalWeight");
        sb.append('=');
        sb.append(((this.totalWeight == null) ? "<null>" : this.totalWeight));
        sb.append(',');
        sb.append("totalWeightUnit");
        sb.append('=');
        sb.append(((this.totalWeightUnit == null) ? "<null>" : this.totalWeightUnit));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
    
}