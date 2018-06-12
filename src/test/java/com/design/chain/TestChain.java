package com.design.chain;


import org.junit.Test;

public class TestChain {

    @Test
    public void testChain(){
        Manager manager1 = new Manager1();
        Manager manager2 = new Manager2();
        Manager manager3 = new Manager3();
        manager1.setSuccessProcessor(manager2);
        manager2.setSuccessProcessor(manager3);

        Request qingjia = new Request();
        qingjia.setRequestType(RequestTypeEnum.QINGJIA.getTypeCd());
        qingjia.setRequestContent(2);
        manager1.handleRequest(qingjia);

        Request jiaxin = new Request();
        jiaxin.setRequestType(RequestTypeEnum.JIAXIN.getTypeCd());
        jiaxin.setRequestContent(500);
        manager1.handleRequest(jiaxin);
    }

}
