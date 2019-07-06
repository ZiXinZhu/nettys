package com.zzx.netty_client;

import com.zzx.netty_client.core.EvhoClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NettyClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(NettyClientApplication.class, args);
        try {
            new EvhoClient("192.168.0.106",8456).start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
