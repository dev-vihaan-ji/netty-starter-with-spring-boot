package com.dev.vihaan.netty.starter.with.spring_boot.prepare;

public class ByteOperationExample {
    public static void main(String[] args) {
        // 바이트 단위 데이터 처리 실습
        byte b1 = 0b01000001;// 65 (ASCII 'A')
        byte b2 = 0b00110100;// 52 (ASCII '4')// 바이트 값 확인
        System.out.println("b1: " + b1);
        System.out.println("b2: " + b2);

        // 바이트 배열 생성
        byte[] bytes = {b1, b2};

        // 바이트 배열 출력
        for (byte b : bytes) {
            System.out.print((char) b);// 출력: A4
        }
        System.out.println();
    }

}
