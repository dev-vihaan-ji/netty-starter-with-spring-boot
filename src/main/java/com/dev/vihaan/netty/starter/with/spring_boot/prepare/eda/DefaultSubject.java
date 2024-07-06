package com.dev.vihaan.netty.starter.with.spring_boot.prepare.eda;

import java.util.ArrayList;
import java.util.List;

public class DefaultSubject implements Subject {
    private final List<Observer> observerList = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        System.out.println("notifyObservers: " + message);
        for (Observer observer : observerList) {
            observer.update(message);
        }
    }
}
