package com.dev.vihaan.netty.starter.with.spring_boot.prepare;

public class BitOperationExample {
    public static void main(String[] args) {
        // 비트 연산 실습
        int a = 0b1010;// 10진수로 10
        int b = 0b0110;// 10진수로 6// AND 연산
        int andResult = a & b;// 결과: 0b0010 (2)
        System.out.println("AND 연산 결과: " + andResult);

        // OR 연산
        int orResult = a | b;// 결과: 0b1110 (14)
        System.out.println("OR 연산 결과: " + orResult);

        // XOR 연산
        int xorResult = a ^ b;// 결과: 0b1100 (12)
        System.out.println("XOR 연산 결과: " + xorResult);

        // NOT 연산
        int notResult = ~a;// 결과: 0b0101 (-11)
        System.out.println("NOT 연산 결과: " + notResult);
    }
}
