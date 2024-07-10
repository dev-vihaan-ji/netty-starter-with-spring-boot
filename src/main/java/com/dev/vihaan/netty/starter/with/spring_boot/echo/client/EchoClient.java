package com.dev.vihaan.netty.starter.with.spring_boot.echo.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Echo Server와 연결할 수 있는 클라이언트
 */

@Slf4j
@Component
public class EchoClient {
    private final EventLoopGroup workerGroup;

    public EchoClient() {
        this.workerGroup = new NioEventLoopGroup();
    }

    public void start(int port) {
        try {
            log.info("echo-client start()");
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new EchoChannelInitializer());

            ChannelFuture future = bootstrap.connect("localhost", port).syncUninterruptibly();

            // close() Function 바로 종료 IOException 해당 메소드를 호출하며누 pipeline에 추가된 handler의 close 메소드가 호출된다.
//            future.channel().close().syncUninterruptibly();

            // closeFuture() Function channel이 닫힐 때 알림을 받을 수 있는 ChannelFuture를 반환.
            future.channel().closeFuture().syncUninterruptibly();
        } catch (Exception e) {
            log.error("start() error", e);
        } finally {
            // shutdown
            workerGroup.shutdownGracefully();
        }
    }

    static class EchoChannelInitializer extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ChannelPipeline pipeline = ch.pipeline();
            pipeline.addLast(new EchoClientHandler());
        }
    }
}
