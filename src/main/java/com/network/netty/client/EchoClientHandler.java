package com.network.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * 泛型参数类型为ByteBuf，表示该Handler中读取的数据类型为ByteBuf型
 */
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf>{

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client与Server成功创建连接！");
        ctx.writeAndFlush(Unpooled.copiedBuffer("First Netty Application!", CharsetUtil.UTF_8));
    }

    /**
     * 业务逻辑
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        System.out.println("接受服务端的响应" + byteBuf.toString(CharsetUtil.UTF_8));
    }

    /**
     * 异常处理
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
