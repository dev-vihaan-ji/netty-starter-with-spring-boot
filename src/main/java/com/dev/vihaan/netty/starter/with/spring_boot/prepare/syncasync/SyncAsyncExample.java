package com.dev.vihaan.netty.starter.with.spring_boot.prepare.syncasync;


import java.util.concurrent.CompletableFuture;

public class SyncAsyncExample {

    public static void main(String[] args) throws InterruptedException {

        // 음식을 주문합니다.
        Order order = new Order("pizza", 3000);

        // 레스토랑에서 음식을 주문하고 대기합니다.
        // 음식이 나올 때까지 일을 하지 못 합니다.
        // 동기 방식의 주문 처리
        Restaurant.processSyncOrder(order);

        // 레스토랑에서 음식을 주문하고 다른 일을 할 수 있습니다.
        Restaurant.processAsyncOrder(order);

        // is synchronous == true don't anything else until the food is served.
        // is asynchronous == true do anything else until the food is served.
        String threadName = Thread.currentThread().getName();
        System.out.println("[" + threadName +"] 음식이 나올 때까지 다른 일을 하고 싶어.");
        System.out.println("[" + threadName +"] 의자에 앉아서 책을 읽을래.");

        // 음식이 나올 때까지 기다립니다.
        Thread.sleep(15000);
    }
}

class Restaurant {
    private Order order;

    // 동기식으로 음식 준비
    public static void processSyncOrder(Order order) {
        String threadName = Thread.currentThread().getName();
        try {
            // 현재 쓰레드 이름 출력
            System.out.println("[" + threadName + "] processSyncOrder() start order [" + order + "] default interval 10000 seconds.");
            Thread.sleep(10000);
            System.out.println("[" + threadName + "] processSyncOrder() finish!");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // 비동기식으로 음식 준비
    public static void processAsyncOrder(Order order) {
        CompletableFuture.runAsync(() -> {
            String threadName = Thread.currentThread().getName();
            try {
                // 현재 쓰레드 이름 출력
                System.out.println("[" + threadName + "] processAsyncOrder() start order [" + order + "] default interval 10000 seconds.");
                Thread.sleep(10000);
                System.out.println("[" + threadName + "] processAsyncOrder() finish!");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }
}

/**
 * Order Object
 */
class Order {
    private final String foodName;
    private final int foodPrice;

    public Order(String foodName, int foodPrice) {
        this.foodName = foodName;
        this.foodPrice = foodPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "foodName='" + foodName + '\'' +
                ", foodPrice=" + foodPrice +
                '}';
    }
}
