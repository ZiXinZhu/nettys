package com.zzx.netty_server.core;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.CharsetUtil;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@Component
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    public static Map<String, Channel> map=new HashMap<>();


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String client= String.valueOf(ctx.channel().remoteAddress());
        Channel channel=ctx.channel();
        map.put(client,channel);
        System.out.println("client:"+client);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf in=(ByteBuf)msg;
        String message=in.toString(Charset.forName("UTF-8"));
        System.out.println("服务器收到消息："+message);



    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        map.remove(ctx.channel().remoteAddress());
        System.out.println("host:"+ctx.channel().remoteAddress()+"链接断开");
    }

}
