package com.zzx.netty_server.controller;

import com.zzx.netty_server.core.EchoServerHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;

@RestController
public class EchoController {

    @Autowired
    EchoServerHandler handler;
    @GetMapping("/send")
    public String send(String host,String message){
        ByteBuf byteBuffer= Unpooled.copiedBuffer(message, Charset.forName("UTF-8"));
        EchoServerHandler.map.get(host).writeAndFlush(byteBuffer.duplicate());
        return "发送成功！";
    }
}
