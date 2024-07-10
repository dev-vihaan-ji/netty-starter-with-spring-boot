package com.dev.vihaan.netty.starter.with.spring_boot;

import com.dev.vihaan.netty.starter.with.spring_boot.discard.DiscardServer;
import com.dev.vihaan.netty.starter.with.spring_boot.echo.client.EchoClient;
import com.dev.vihaan.netty.starter.with.spring_boot.echo.server.EchoServer;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * yml에 적은 type에 따라 discard server, 또는 echo server를 실핼할 수 있도록 한다.
 */
@Slf4j
@Setter
@Getter
@Component
public class ApplicationStartupTask implements ApplicationListener<ApplicationReadyEvent> {

    @Value("${netty.server.type}")
    private String type;
    @Value("${netty.server.port}")
    private int port;

    private final DiscardServer discardServer;
    private final EchoServer echoServer;
    private final EchoClient echoClient;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public ApplicationStartupTask(DiscardServer discardServer, EchoServer echoServer, EchoClient echoClient) {
        this.discardServer = discardServer;
        this.echoServer = echoServer;
        this.echoClient = echoClient;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("onApplicationEvent start");
        switch (type) {
            // discard server start
            case NettyConstants.DISCARD_SERVER -> discardServer.start(port);
            case NettyConstants.ECHO_SERVER -> {
                // main Thread가 여기서 잡혀있기 때문.
                // executorService 이용하여 별도의 스레드로 실행
                executorService.execute(() -> echoServer.start(port));
                echoClient.start(port);
            }
        }
    }
}
