package com.trj.tlib.javabean;

import com.google.gson.annotations.SerializedName;
import com.trj.tlib.javabean.TBaseJB;

/**
 * @author tong
 * @date 2018/2/22 12:04
 */

public class TBaseRespJB extends TBaseJB {

    @SerializedName("operationStatus")
    protected String operationStatus;
    @SerializedName("operationMessage")
    protected String operationMessage;

    public String getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(String operationStatus) {
        this.operationStatus = operationStatus;
    }

    public String getOperationMessage() {
        return operationMessage;
    }

    public void setOperationMessage(String operationMessage) {
        this.operationMessage = operationMessage;
    }
}
