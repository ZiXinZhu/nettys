package com.zzx.netty_client.controller;


import com.zzx.netty_client.core.EchoClientHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;

@RestController
public class ClientController {

    @Autowired
    EchoClientHandler handler;

    @GetMapping("/send")
    public String send(String message){
        ByteBuf byteBuffer= Unpooled.copiedBuffer(message, Charset.forName("UTF-8"));
        EchoClientHandler.list.get(0).writeAndFlush(byteBuffer.duplicate());

        return "success";
    }
}
