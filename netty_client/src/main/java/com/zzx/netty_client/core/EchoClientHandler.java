package com.zzx.netty_client.core;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


@Component
public class EchoClientHandler extends SimpleChannelInboundHandler {
    public static List<Channel> list=new ArrayList<>();


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        ByteBuf in=(ByteBuf)o;
        String message=in.toString(Charset.forName("UTF-8"));
        System.out.println("客户端收到消息："+message);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        list.add(ctx.channel());
        String s="你好!";
        ByteBuf byteBuffer= Unpooled.copiedBuffer(s, Charset.forName("UTF-8"));
        ctx.writeAndFlush(byteBuffer.duplicate());
        System.out.println("adrss:"+ctx.channel().remoteAddress());

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("服务器:"+ctx.channel().remoteAddress()+"断开链接");
    }
}
