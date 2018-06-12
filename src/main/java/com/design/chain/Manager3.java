package com.design.chain;

/**
 * 经理1号
 */
public class Manager3 extends Manager{

    public void handleRequest(Request request) {
        String requestType = request.getRequestType();
        int requestContent = request.getRequestContent();
        if(RequestTypeEnum.QINGJIA.getTypeCd().equals(requestType)){ //请假流程
            if(requestContent<=10){ //Manager3：请10天，可以直接批
                System.out.println("Manager3:同意！放假去吧！");
            } else {
                System.out.println("Manager3:拒绝！请个屁，来我办公室一趟！");
                return;
            }
        }
        if(RequestTypeEnum.JIAXIN.getTypeCd().equals(requestType)){ //加薪流程
            if(requestContent<=1000){
                System.out.println("Manger3:同意！小伙子我觉得你很有前途，好好干！");
                return;
            } else {
                System.out.println("Manger3:拒绝！垃圾还想要加薪？！");
                return;
            }
        }
    }
}
