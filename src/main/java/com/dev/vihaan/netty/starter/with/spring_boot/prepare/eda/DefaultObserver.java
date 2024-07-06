package com.dev.vihaan.netty.starter.with.spring_boot.prepare.eda;

public class DefaultObserver implements Observer {

    private String name;

    public DefaultObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " received message " + message);
    }
}
