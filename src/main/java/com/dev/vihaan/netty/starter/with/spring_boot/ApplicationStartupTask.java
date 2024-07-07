package com.dev.vihaan.netty.starter.with.spring_boot;

import com.dev.vihaan.netty.starter.with.spring_boot.discard.DiscardServer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * yml에 적은 type에 따라 discard server, 또는 echo server를 실핼할 수 있도록 한다.
 */
@Setter
@Getter
@Component
public class ApplicationStartupTask implements ApplicationListener<ApplicationReadyEvent> {

    @Value("${netty.server.type}")
    private String type;
    @Value("${netty.server.port}")
    private int port;

    private final DiscardServer discardServer;

    public ApplicationStartupTask(DiscardServer discardServer) {
        this.discardServer = discardServer;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        switch (type) {
            // discard server start
            case NettyConstants.DISCARD_SERVER -> discardServer.start(port);
        }
    }
}
