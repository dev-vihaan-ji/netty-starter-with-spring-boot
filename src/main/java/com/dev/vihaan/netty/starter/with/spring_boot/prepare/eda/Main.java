package com.dev.vihaan.netty.starter.with.spring_boot.prepare.eda;

public class Main {

    public static void main(String[] args) {
        DefaultSubject subject = new DefaultSubject();

        DefaultObserver observer1 = new DefaultObserver("test-observer-1");
        DefaultObserver observer2 = new DefaultObserver("test-observer-2");

        subject.registerObserver(observer1);
        subject.registerObserver(observer2);

        subject.notifyObservers("observer pattern test");

        subject.removeObserver(observer1);
        subject.removeObserver(observer2);

    }
}
