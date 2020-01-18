package cn.az.code.concurrent;

import cn.hutool.core.thread.ExecutorBuilder;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Liz
 * @date 1/18/2020
 */
public class CallableDemo<T> {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TicketCallable<String> ticketCallable = new TicketCallable<>();
        FutureTask<String> futureTask = new FutureTask<>(ticketCallable);
        ExecutorBuilder.create().build().execute(futureTask);
        System.out.println(futureTask.get());
    }


    public static class TicketCallable<T> implements Callable<T> {

        private int tickets = 10;
        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         */
        @Override
        public T call() {
            while (tickets > 0) {
                System.out.println("left : "+tickets);
                tickets--;
            }
            String res =  "all sold";
            return (T) res;
        }
    }
}
