package com.dev.vihaan.netty.starter.with.spring_boot.prepare.ionio;

import java.io.*;

public class IOExample {

    public static void main(String[] args) {
        // 입력 파일 처리 메서드 호출
        inputFile();
        // 출력 파일 처리 메서드 호출
        outputFile();
    }

    private static void inputFile() {
        // 입력 파일 객체 생성
        File file = new File("input.txt");
        // 파일이 존재하는 경우
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                // 파일 읽기 변수
                int bytesRead;
                // 파일 끝까지 읽기 -1인 경우 더 이상 읽을 데이터가 없습니다.
                while ((bytesRead = fis.read()) != -1) {
                    // 콘솔에 문자 출력
                    System.out.print((char) bytesRead);
                }
            } catch (IOException e) {
                // 예외 처리
                e.printStackTrace();
            }
        }
    }

    private static void outputFile() {
        // 출력 파일 객체 생성
        File file = new File("output.txt");
        // 파일이 존재하는 경우
        if (file.exists()) {
            try (FileOutputStream fos = new FileOutputStream(file)) {
                // 출력할 문자열
                String outputText = "Hello Output Stream";
                // 문자열을 바이트로 변환
                byte[] outputBytes = outputText.getBytes();
                // 파일에 바이트 데이터 쓰기
                fos.write(outputBytes);
            } catch (IOException e) {
                // 예외 처리
                e.printStackTrace();
            }
        }
    }
}
