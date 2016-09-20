package com.paralleltest;

import org.junit.runners.Parameterized;
import org.junit.runners.model.RunnerScheduler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Irfan Mauludin on 4/18/16.
 */
public class Parallelized extends Parameterized{
    private static class ThreadPoolScheduler implements RunnerScheduler {
        ExecutorService executorService;

        public ThreadPoolScheduler(){
            String threads = System.getProperty("junit.parallel.threads","16");
            int numThreads = Integer.parseInt(threads);
            executorService = Executors.newFixedThreadPool(numThreads);
        }

        @Override
        public void schedule(Runnable childStatement) {

        }

        @Override
        public void finished(){
            executorService.shutdown();
            try{
                executorService.awaitTermination(10, TimeUnit.MINUTES);
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        }
    }


    /**
     * Only called reflectively. Do not use programmatically.
     *
     * @param klass
     */
    public Parallelized(Class<?> klass) throws Throwable {
        super(klass);
    }
}
