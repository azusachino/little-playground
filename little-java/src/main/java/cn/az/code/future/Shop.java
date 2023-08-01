package cn.az.code.future;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author Liz
 */
public class Shop {

    private String product;

    public Shop(String product) {
        this.product = product;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    private static final ExecutorService SERVICE = Executors.newCachedThreadPool();

    private static final List<Shop> SHOPS = Arrays.asList(new Shop("num1"),
            new Shop("num2"),
            new Shop("num3"),
            new Shop("num4"));

    public static void main(String[] args) {
        Shop shop = new Shop("BestShop");
        long start = System.nanoTime();

        Future<Double> future = shop.getPriceAsync("ya");
        System.out.println("invocation = " + (System.nanoTime() - start) / 1_000_000);
        doSomethingElse();
        try {
            System.out.println("future.get() = " + future.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("return = " + (System.nanoTime() - start) / 1_000_000);

        System.out.println(shop.findPricesAsync("ok"));

    }

    public List<String> findPricesAsync(String product) {
        return SHOPS.stream()
                .map(s -> CompletableFuture.supplyAsync(() -> s.getProduct() + " price is " + s.getPrice(product),
                        SERVICE))

                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    public List<String> findPrices(String product) {
        return SHOPS.parallelStream()
                .map(s -> String.format("%s  price is %.2f", s.getProduct(), s.getPrice(product)))
                .collect(Collectors.toList());
    }

    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        SERVICE.submit(() -> futurePrice.complete(calculatePrice(product)));
        return futurePrice;
    }

    public Future<Double> getPriceAsync2(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    public double getPrice(String product) {
        return calculatePrice(product);
    }

    private double calculatePrice(String product) {
        delay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void doSomethingElse() {
        for (int i = 0; i < 1000; i++) {
            i++;
        }
    }

    public void calculateSeconds(Consumer<Object> consumer, Object o) {
        long start = System.nanoTime();
        consumer.accept(o);
        System.out.println(consumer + " : " + (System.nanoTime() - start) / 1_000_000 + " msecs");
    }
}
