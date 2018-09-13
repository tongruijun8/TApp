package com.trj.tlib.javabean;

import com.google.gson.annotations.SerializedName;

/**
 * @author tong
 * @date 2018/2/22 12:04
 */

public class TBaseRespJB2<T> extends TBaseRespJB {

//    @SerializedName("operationStatus")
//    private String operationStatus;
//    @SerializedName("operationMessage")
//    private String operationMessage;
    @SerializedName("data")
    private T data;

    //以下为页码相关
    @SerializedName("page")
    private int page;
    @SerializedName("pageSize")
    private int pageSize;
    @SerializedName("allCount")
    private int allCount;
    @SerializedName("pageCount")
    private int pageCount;

//    public String getOperationStatus() {
//        return operationStatus;
//    }
//
//    public void setOperationStatus(String operationStatus) {
//        this.operationStatus = operationStatus;
//    }
//
//    public String getOperationMessage() {
//        return operationMessage;
//    }
//
//    public void setOperationMessage(String operationMessage) {
//        this.operationMessage = operationMessage;
//    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getAllCount() {
        return allCount;
    }

    public void setAllCount(int allCount) {
        this.allCount = allCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
