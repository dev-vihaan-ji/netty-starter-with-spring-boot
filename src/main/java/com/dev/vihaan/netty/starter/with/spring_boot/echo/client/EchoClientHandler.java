package com.dev.vihaan.netty.starter.with.spring_boot.echo.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

@Slf4j
public class EchoClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("channel active remote_address# [{}]", ctx.channel().remoteAddress());
//        String sendMessage = "hello, echo server";
        String sendMessage = "나는 지민이야, echo server";

        // 새로운 ByteBuf 생성
        ByteBuf buf = Unpooled.buffer();
        // sendMessage.getBytes 전달
        buf.writeBytes(sendMessage.getBytes());
        ctx.writeAndFlush(buf)
                .addListener(future -> {
                    if (future.isSuccess()) {
                        log.info("send message {}", sendMessage);
                    }
                });
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String readMessage = ((ByteBuf) msg).toString(Charset.defaultCharset());
        log.info("read message: {}", readMessage);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
