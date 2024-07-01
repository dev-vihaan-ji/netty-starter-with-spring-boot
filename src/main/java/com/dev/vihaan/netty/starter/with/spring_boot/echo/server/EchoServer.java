package com.dev.vihaan.netty.starter.with.spring_boot.echo.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EchoServer {

    private final EventLoopGroup bossGroup;
    private final EventLoopGroup workerGroup;

    public EchoServer() {
        this.bossGroup = new NioEventLoopGroup(1);
        this.workerGroup = new NioEventLoopGroup();
    }

    public void start(int port) {
        log.info("echo-server start()");
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            // handler 추가
                            pipeline.addLast(new EchoServerHandler());
                        }
                    });
            
            // bind() 접속할 포트를 지정 8888
            ChannelFuture future = bootstrap.bind(port).syncUninterruptibly();
            // sync() throw new InterruptedException vs syncUninterruptibly() is Thread.currentThread().interrupt();
            future.channel().closeFuture().syncUninterruptibly();
        } catch (Exception e) {
            log.error("start() error", e);
        } finally {
            // shutdown
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}