package com.design.chain;

/**
 * 请求类型
 */
public enum RequestTypeEnum {

    QINGJIA("QJ","请假申请"),
    JIAXIN("JX","加薪申请");

    private String typeCd;

    private String typeName;

    RequestTypeEnum(String typeCd, String typeName) {
        this.typeCd = typeCd;
        this.typeName = typeName;
    }

    public String getTypeCd() {
        return typeCd;
    }

    public String getTypeName() {
        return typeName;
    }
}
