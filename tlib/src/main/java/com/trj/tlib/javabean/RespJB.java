package com.trj.tlib.javabean;

import com.google.gson.annotations.SerializedName;

public class RespJB<T extends TBaseJB>{

    @SerializedName("operationStatus")
    private String operationStatus;
    @SerializedName("operationMessage")
    private String operationMessage;
    @SerializedName("data")
    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
