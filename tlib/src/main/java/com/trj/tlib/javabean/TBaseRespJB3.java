package com.trj.tlib.javabean;

import com.google.gson.annotations.SerializedName;

/**
 * @author tong
 * @date 2018/2/22 12:04
 */

public class TBaseRespJB3<T> extends TBaseRespJB {

//    @SerializedName("operationStatus")
//    private String operationStatus;
//    @SerializedName("operationMessage")
//    private String operationMessage;
    @SerializedName("data")
    private T data;

    //以下为页码相关
    @SerializedName("first")
    private boolean first;
    @SerializedName("last")
    private boolean last;
    @SerializedName("currentPageNumber")
    private int currentPageNumber;
    @SerializedName("itemsInPage")
    private int itemsInPage;
    @SerializedName("pageSize")
    private int pageSize;
    @SerializedName("totalPages")
    private int totalPages;
    @SerializedName("totalItems")
    private int totalItems;


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public int getCurrentPageNumber() {
        return currentPageNumber;
    }

    public void setCurrentPageNumber(int currentPageNumber) {
        this.currentPageNumber = currentPageNumber;
    }

    public int getItemsInPage() {
        return itemsInPage;
    }

    public void setItemsInPage(int itemsInPage) {
        this.itemsInPage = itemsInPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }
}
