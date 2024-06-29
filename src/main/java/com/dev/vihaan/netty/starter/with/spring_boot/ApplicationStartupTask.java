package com.dev.vihaan.netty.starter.with.spring_boot;

import com.dev.vihaan.netty.starter.with.spring_boot.server.DiscardServer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ApplicationStartupTask implements ApplicationListener<ApplicationReadyEvent> {

    private final DiscardServer discardServer;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        discardServer.start();
    }
}
