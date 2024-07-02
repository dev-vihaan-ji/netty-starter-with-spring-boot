package com.dev.vihaan.netty.starter.with.spring_boot.prepare;

public class BitOperationExample {
    public static void main(String[] args) {
        // 비트 연산 실습
        // java 2진수 표기법 : 0b
        // java 8진수 표기법 : 0
        // java 16진수 표기법 : 0x or 0X
        int a = 0b1010;// 10진수로 10
        int b = 0b0110;// 10진수로 6

        // 두 비트가 모두 1인 경우에만 결과가 1이 되고, 나머지는 모두 0이됩니다.
        // AND 연산
        int andResult = a & b;// 결과: 0b0010 (2)
        System.out.println("AND 연산 결과: " + andResult);

        // 두 비트 중 하나라도 1이라면 결과가 1이 됩니다.
        // OR 연산
        int orResult = a | b;// 결과: 0b1110 (14)
        System.out.println("OR 연산 결과: " + orResult);

        // 두 비트가 다르면 결과가 1이 되고, 같으면 0이 됩니다
        // XOR 연산
        int xorResult = a ^ b;// 결과: 0b1100 (12)
        System.out.println("XOR 연산 결과: " + xorResult);

        // 0을 1로, 1을 0으로 반전시킵니다.
        // NOT 연산
        int notResult = ~a;// 결과: 0b0101 (-11)
        System.out.println("NOT 연산 결과: " + notResult);
    }
}