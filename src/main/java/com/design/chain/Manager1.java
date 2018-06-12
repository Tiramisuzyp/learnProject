package com.design.chain;

/**
 * 经理1号
 */
public class Manager1 extends Manager{

    public void handleRequest(Request request) {
        String requestType = request.getRequestType();
        int requestContent = request.getRequestContent();
        if(RequestTypeEnum.QINGJIA.getTypeCd().equals(requestType)){ //请假流程
            if(requestContent<2){ //Manager1：请一天，可以直接批
                System.out.println("Manager1:同意！放假去吧！");
            } else if(requestContent<10){//10天内的可以请示上级
                System.out.println("Manager1:请假得有点多，我得请示领导！");
                successProcessor.handleRequest(request);
            } else {
                System.out.println("Manager1:拒绝！你小子请假请太多！干脆离职算了！");
                return;
            }
        }
        if(RequestTypeEnum.JIAXIN.getTypeCd().equals(requestType)){ //加薪流程
            System.out.println("Manger1:加薪的话得请示上级，抱歉了兄弟！");
            successProcessor.handleRequest(request);
        }
    }
}
