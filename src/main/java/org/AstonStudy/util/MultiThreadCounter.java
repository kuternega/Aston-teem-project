package org.AstonStudy.util;

import org.AstonStudy.model.Car;
import org.AstonStudy.Collection.MyArrayList;
import java.util.ArrayList;
import java.util.List;

public class MultiThreadCounter {

    public static int countOccurrences(MyArrayList<Car> list, Car target, int threadCount) {
        if (threadCount <= 0) {
            throw new IllegalArgumentException("threadCount must be positive");
        }
        int size = list.size();
        if (size == 0) return 0;

        int chunkSize = size / threadCount;
        int remainder = size % threadCount;

        List<Thread> threads = new ArrayList<>();
        List<CounterTask> tasks = new ArrayList<>();

        int start = 0;
        for (int i = 0; i < threadCount; i++) {
            int end = start + chunkSize + (i < remainder ? 1 : 0);
            if (start >= size) break;

            CounterTask task = new CounterTask(list, target, start, end);
            tasks.add(task);
            Thread thread = new Thread(task);
            thread.start();
            threads.add(thread);

            start = end;
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted: " + e.getMessage());
            }
        }

        int total = 0;
        for (CounterTask task : tasks) {
            total += task.getCount();
        }
        return total;
    }

    private static class CounterTask implements Runnable {
        private final MyArrayList<Car> list;
        private final Car target;
        private final int start;
        private final int end;
        private int count;

        CounterTask(MyArrayList<Car> list, Car target, int start, int end) {
            this.list = list;
            this.target = target;
            this.start = start;
            this.end = end;
            this.count = 0;
        }

        @Override
        public void run() {
            for (int i = start; i < end; i++) {
                Car car = list.get(i);
                if (car == null ? target == null : car.equals(target)) {
                    count++;
                }
            }
        }

        public int getCount() {
            return count;
        }
    }
}

