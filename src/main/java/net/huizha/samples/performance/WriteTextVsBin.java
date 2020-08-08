package net.huizha.samples.performance;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.lang3.time.StopWatch;

public class WriteTextVsBin {

    public static void main(String[] args) throws IOException {
        int times= 1;//100000000; //10M
        textWriter(times);
        binWriter(times);
    }


    private static void textWriter(int loop) throws IOException {
        StopWatch watch = new StopWatch();
        watch.start();
        try (FileWriter fw = new FileWriter("textWriter.txt")) {
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                for (int i = 0; i < loop; i++) {
                    bw.write("1000 ");
                }
            }
        }
        watch.stop();
        System.out.println("textWriter Time Elapsed: " + watch.getTime());
    }
    private static void binWriter(int loop) throws IOException {
        StopWatch watch = new StopWatch();
        watch.start();
        try (FileOutputStream fw = new FileOutputStream("binWriter.bin")) {
            try (BufferedOutputStream bw = new BufferedOutputStream(fw)) {
                for (int i = 0; i < loop; i++) {
                    bw.write(1000);
                }
            }
        }
        watch.stop();
        System.out.println("binWriter Time Elapsed: " + watch.getTime());
    }

}
