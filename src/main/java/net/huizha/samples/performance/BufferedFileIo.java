package net.huizha.samples.performance;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.lang3.time.StopWatch;

public class BufferedFileIo {

    public static void main(String[] args) throws IOException {
        int times= 100000; //10M
        nonBufferredFileWriter(times);
        nonBufferredFileWriterImproved(times);
        bufferredFileWriter(times);
        bufferredFileWriterImproved(times);
    }

    private static void nonBufferredFileWriter(int loop) throws IOException {
        StopWatch watch = new StopWatch();
        watch.start();
        try (FileWriter fw = new FileWriter("nonBufferredFileWriter.txt")) {
            for (int i = 0; i < loop; i++) {
                fw.write("hello");
                fw.write(" John");
                fw.write("\n");
            }
        }
        watch.stop();
        System.out.println("nonBufferredFileWriter Time Elapsed: " + watch.getTime());
    }

    private static void nonBufferredFileWriterImproved(int loop) throws IOException {
        StopWatch watch = new StopWatch();
        watch.start();
        try (FileWriter fw = new FileWriter("nonBufferredFileWriterImproved.txt")) {
            for (int i = 0; i < loop; i++) {
                fw.write("hello John\n");
                //fw.flush();
            }
        }
        watch.stop();
        System.out.println("nonBufferredFileWriterImproved Time Elapsed: " + watch.getTime());
    }

    private static void bufferredFileWriter(int loop) throws IOException {
        StopWatch watch = new StopWatch();
        watch.start();
        try (FileWriter fw = new FileWriter("bufferredFileWriter.txt")) {
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                for (int i = 0; i < loop; i++) {
                    bw.write("hello");
                    bw.write(" John");
                    bw.write("\n");
                }
            }
        }
        watch.stop();
        System.out.println("bufferredFileWriter Time Elapsed: " + watch.getTime());
    }
    private static void bufferredFileWriterImproved(int loop) throws IOException {
        StopWatch watch = new StopWatch();
        watch.start();
        try (FileWriter fw = new FileWriter("bufferredFileWriterImproved.txt")) {
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                for (int i = 0; i < loop; i++) {
                    bw.write("hello John\n");
                    //bw.flush();
                }
            }
        }
        watch.stop();
        System.out.println("bufferredFileWriterImproved Time Elapsed: " + watch.getTime());
    }

}
