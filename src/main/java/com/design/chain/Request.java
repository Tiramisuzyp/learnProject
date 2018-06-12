package com.design.chain;

/**
 * 请求类：责任链的发起者
 * 包含：请求类型，请求内容
 */
public class Request {

    private String requestType;

    private int requestContent;

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public int getRequestContent() {
        return requestContent;
    }

    public void setRequestContent(int requestContent) {
        this.requestContent = requestContent;
    }
}
