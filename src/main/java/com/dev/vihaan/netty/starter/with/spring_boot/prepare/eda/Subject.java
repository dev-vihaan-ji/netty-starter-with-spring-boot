package com.dev.vihaan.netty.starter.with.spring_boot.prepare.eda;


public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String message);
}
