package com.concurrent.pool.db;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

/**
 * 使用动态代理拦截commit方法，模拟数据库连接动作
 */
public class ConnectionDriver {

    public static final Connection createConnection() {
        return (Connection) Proxy.newProxyInstance(ConnectionDriver.class.getClassLoader(), new Class<?>[]{Connection.class}, new ConnectionHandler());
    }

    static class ConnectionHandler implements InvocationHandler{

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if("commit".equals(method.getName())){
                TimeUnit.MILLISECONDS.sleep(100);
            }
            return null;
        }
    }

}
