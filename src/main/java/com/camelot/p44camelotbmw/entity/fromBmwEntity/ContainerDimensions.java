package com.camelot.p44camelotbmw.entity.fromBmwEntity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContainerDimensions {
    
    @SerializedName("totalWeightKGS")
    @Expose
    private String totalWeightKGS;
    @SerializedName("totalVolumeCBM")
    @Expose
    private String totalVolumeCBM;
    
    public String getTotalWeightKGS() {
        return totalWeightKGS;
    }
    
    public void setTotalWeightKGS(String totalWeightKGS) {
        this.totalWeightKGS = totalWeightKGS;
    }
    
    public String getTotalVolumeCBM() {
        return totalVolumeCBM;
    }
    
    public void setTotalVolumeCBM(String totalVolumeCBM) {
        this.totalVolumeCBM = totalVolumeCBM;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ContainerDimensions.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("totalWeightKGS");
        sb.append('=');
        sb.append(((this.totalWeightKGS == null) ? "<null>" : this.totalWeightKGS));
        sb.append(',');
        sb.append("totalVolumeCBM");
        sb.append('=');
        sb.append(((this.totalVolumeCBM == null) ? "<null>" : this.totalVolumeCBM));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
    
}