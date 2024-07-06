package com.dev.vihaan.netty.starter.with.spring_boot.prepare.ionio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class NioExample {

    public static void main(String[] args) {
        // 여기서 두 개의 메서드 nioInputFile()과 nioOutputFile()을 호출합니다.
        nioInputFile();
        nioOutputFile();
    }

    // nioInputFile() 메서드는 "input.txt" 파일을 읽어서 콘솔에 출력합니다.
    public static void nioInputFile() {
        String fileName = "input.txt";
        Path filePath = Paths.get(fileName);
        if (Files.exists(filePath)) {
            // FileChannel을 사용하여 파일을 읽습니다.
            try (FileChannel channel = FileChannel.open(filePath, StandardOpenOption.READ)) {
                // 파일 크기만큼의 ByteBuffer 생성
                ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
                // 파일을 읽어서 ByteBuffer에 저장
                while (channel.read(byteBuffer) != -1) {
                    // ByteBuffer의 위치를 리셋
                    byteBuffer.flip();
                    // ByteBuffer의 내용을 콘솔에 출력
                    System.out.write(byteBuffer.array(), 0, byteBuffer.limit());
                    // ByteBuffer 초기화
                    byteBuffer.clear();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // nioOutputFile() 메서드는 "output.txt" 파일에 아래 내용을 씁니다.
    public static void nioOutputFile() {
        String fileName = "output.txt";
        Path filePath = Paths.get(fileName);
        if (Files.exists(filePath)) {
            // FileChannel을 사용하여 파일에 쓰기
            try (FileChannel channel = FileChannel.open(filePath, StandardOpenOption.WRITE)) {
                String outputText = "Hello Nio ByteBuffer Output Channel";
                // 문자열을 바이트 배열로 변환
                byte[] outputBytes = outputText.getBytes(StandardCharsets.UTF_8);
                // 바이트 배열을 ByteBuffer로 래핑
                ByteBuffer byteBuffer = ByteBuffer.wrap(outputBytes);
                // ByteBuffer의 내용을 파일에 쓰기
                int writeSize = channel.write(byteBuffer);
            } catch (IOException e) {
                e.printStackTrace(); // 예외 처리
            }
        }
    }
}
