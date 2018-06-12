package com.design.chain;

/**
 * 责任链的核心，Manager一环套一环
 */
public abstract class Manager {

    protected Manager successProcessor;

    public abstract void handleRequest(Request request);

    public void setSuccessProcessor(Manager successProcessor) {
        this.successProcessor = successProcessor;
    }
}
