package com.dev.vihaan.netty.starter.with.spring_boot.discard;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DiscardServer {

    // EventLoopGroup은 Netty의 핵심 구성 요소로, 이벤트 루프를 관리합니다.
    // bossGroup은 클라이언트의 연결을 수락하고 workerGroup은 연결된 클라이언트를 처리합니다.
    private final EventLoopGroup bossGroup;
    private final EventLoopGroup workerGroup;

    public DiscardServer() {
        // NioEventLoopGroup은 Non-Blocking I/O 이벤트 루프를 사용합니다.
        // 첫 번째 인자는 스레드 개수를 지정하며, 값이 없으면 CPU 코어 개수와 동일하게 설정됩니다.
        this.bossGroup = new NioEventLoopGroup(1);
        this.workerGroup = new NioEventLoopGroup();
    }

    public void start(int port) {
        log.info("discard-server start()");
        try {
            // ServerBootstrap은 Netty의 서버 구현을 위한 핵심 클래스입니다.
            ServerBootstrap bootstrap = new ServerBootstrap();

            // 이벤트 루프 그룹을 설정합니다.
            bootstrap.group(bossGroup, workerGroup)
                    // 채널 타입을 NioServerSocketChannel로 지정합니다.
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new CustomChannelInitializer());

            // bind() 접속할 포트를 지정 8888
            ChannelFuture future = bootstrap.bind(port).syncUninterruptibly();
            // sync() throw new InterruptedException vs syncUninterruptibly() is Thread.currentThread().interrupt();
            future.channel().closeFuture().syncUninterruptibly();
        } catch (Exception e) {
            log.error("start() error", e);
        } finally {
            // 서버 종료 시 이벤트 루프 그룹을 정상적으로 종료합니다.
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    private static class CustomChannelInitializer extends ChannelInitializer<Channel> {
        @Override
        protected void initChannel(Channel ch) throws Exception {
            ChannelPipeline pipeline = ch.pipeline();
            // handler 추가
            pipeline.addLast(new DiscardServerHandler());
        }
    }
}