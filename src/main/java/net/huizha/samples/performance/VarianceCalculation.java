package net.huizha.samples.performance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.time.StopWatch;

public class VarianceCalculation {

    public static void main(String[] args) throws IOException {
        int times= 10000000; //10M
        List inputs = new ArrayList<Double>();
        Random r = new Random();
        for (int i = 0; i < times; i++) {
            inputs.add(100 + (1000-100) *r.nextDouble());
        }
        bad(inputs);
        good(inputs);
    }


    private static void bad(List<Double> inputs) throws IOException {
        StopWatch watch = new StopWatch();
        watch.start();
        double sum = 0;
        for(int i = 0; i < inputs.size(); i++) {
            sum = sum +  inputs.get(i);
        }
        double average = sum / inputs.size();

        double diff = 0;
        for(int i = 0; i < inputs.size(); i++) {
           diff += (inputs.get(i) - average)*(inputs.get(i) - average);
        }

        double var = diff /(inputs.size());
        watch.stop();
        System.out.println( "result: " + var + " bad Time Elapsed: " + watch.getTime());
    }


    private static void good(List<Double> inputs) throws IOException {
        StopWatch watch = new StopWatch();
        watch.start();
        double sum = 0;
        double sumSquare = 0;
        for(int i = 0; i < inputs.size(); i++) {
            sum = sum +  inputs.get(i);
            sumSquare += inputs.get(i) * inputs.get(i);
        }

        double average = sum/inputs.size();
        double var = (sumSquare/inputs.size() - average * average);
        watch.stop();
        System.out.println( "result: " + var + " good Time Elapsed: " + watch.getTime());
    }

}
