package com.network.plain;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * bug：使用netty.client.EchoClient发起客户段请求时，本服务的响应数据在客户端无法获取
 */
public class PlanOioServer {

    public void serve(int port) throws IOException {
        //serverSocket绑定到指定端口
        ServerSocket serverSocket = new ServerSocket(port);
        for(;;){
            //阻塞监听连接，返回连接套接字
            final Socket clientSocket = serverSocket.accept();
            System.out.println("接收到连接请求:" + clientSocket);
            new Thread(new Runnable() {
                public void run() {
                    OutputStream os;
                    try {
                        //从套接字中获取输出流，写入，并冲刷
                        os = clientSocket.getOutputStream();
                        os.write("这是服务端响应".getBytes("UTF-8"));
                        os.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            clientSocket.close();
                        } catch (IOException e) {

                        }
                    }
                }
            });
        }
    }

    public static void main(String[] args) throws IOException {
        new PlanOioServer().serve(99);
    }

}
