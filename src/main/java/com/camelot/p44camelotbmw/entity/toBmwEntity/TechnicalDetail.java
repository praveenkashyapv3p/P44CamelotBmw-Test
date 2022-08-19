package com.camelot.p44camelotbmw.entity.toBmwEntity;

public class TechnicalDetail {

//    @SerializedName("lifecycleStatus")
//    @Expose
//    private String lifecycleStatus;
//    @SerializedName("lifecycleStatusVerbose")
//    @Expose
//    private String lifecycleStatusVerbose;
//    @SerializedName("correlationId")
//    @Expose
//    private String correlationId;

//    public String getLifecycleStatus() {
//        return lifecycleStatus;
//    }
//
//    public void setLifecycleStatus(String lifecycleStatus) {
//        this.lifecycleStatus = lifecycleStatus;
//    }
//
//    public String getLifecycleStatusVerbose() {
//        return lifecycleStatusVerbose;
//    }
//
//    public void setLifecycleStatusVerbose(String lifecycleStatusVerbose) {
//        this.lifecycleStatusVerbose = lifecycleStatusVerbose;
//    }

//    public String getCorrelationId() {
//        return correlationId;
//    }
//
//    public void setCorrelationId(String correlationId) {
//        this.correlationId = correlationId;
//    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(TechnicalDetail.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
//        sb.append("lifecycleStatus");
//        sb.append('=');
//        sb.append(((this.lifecycleStatus == null) ? "<null>" : this.lifecycleStatus));
//        sb.append(',');
//        sb.append("lifecycleStatusVerbose");
//        sb.append('=');
//        sb.append(((this.lifecycleStatusVerbose == null) ? "<null>" : this.lifecycleStatusVerbose));
//        sb.append(',');
//        sb.append("correlationId");
//        sb.append('=');
//        sb.append(((this.correlationId == null) ? "<null>" : this.correlationId));
//        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
    
}