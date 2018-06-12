package com.design.chain;

/**
 * 经理1号
 */
public class Manager2 extends Manager{

    public void handleRequest(Request request) {
        String requestType = request.getRequestType();
        int requestContent = request.getRequestContent();
        if(RequestTypeEnum.QINGJIA.getTypeCd().equals(requestType)){ //请假流程
            if(requestContent<=3){ //Manager2：请一天，可以直接批
                System.out.println("Manager2:同意！放假去吧！");
            } else {
                System.out.println("Manager2:请假得有点多，我得请示领导！");
                successProcessor.handleRequest(request);
            }
        }
        if(RequestTypeEnum.JIAXIN.getTypeCd().equals(requestType)){ //加薪流程
            System.out.println("Manger2:加薪的话得请示上级，抱歉了兄弟！");
            successProcessor.handleRequest(request);
        }
    }
}
