package com.network.plain;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * JDK原生的NIO接口
 */
public class PlainNioServer {

    public void server(int port) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false); //非阻塞IO
        ServerSocket serverSocket = serverSocketChannel.socket(); //创建套接字，监听指定端口
        serverSocket.bind(new InetSocketAddress(6666));
        Selector selector = Selector.open(); //创建Selector
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT); //注册连接事件

        final ByteBuffer msg = ByteBuffer.wrap("曾总真是666啊".getBytes());

        for(;;) {

            try {
                selector.select(); //阻塞，等待事件
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();

                try {
                    if(selectionKey.isAcceptable()) { //连接成功通知，说明连接可用
                        ServerSocketChannel serverChannel = (ServerSocketChannel) selectionKey.channel();
                        SocketChannel client = serverChannel.accept();
                        client.configureBlocking(false);
                        client.register(selector, SelectionKey.OP_WRITE|SelectionKey.OP_READ, msg.duplicate()); //注册写事件
                        System.out.println("注册了一个读/写通知事件");
                    }

                    if(selectionKey.isWritable()) { //socket已经准备好写了
                        SocketChannel client = (SocketChannel) selectionKey.channel();
                        ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
                        while(buffer.hasRemaining()) {
                            if(client.write(buffer) == 0) {
                                break;
                            }
                        }
                    }
                } catch (IOException e) {
                    selectionKey.cancel();
                    try {
                        selectionKey.channel().close();
                    } catch (IOException ex) {
                        //ignore
                    }
                }

            }
        }


    }

}
