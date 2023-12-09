package com.ariel.javabase.concurrent.future;

import com.ariel.javabase.util.OU;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.*;

/**
 * CompletableFuture使用有哪些注意点
 * - Future需要获取返回值，才能获取异常信息
 * - CompletableFuture的get()方法是阻塞的。
 * - CompletableFuture代码中又使用了默认的线程池，处理的线程个数是电脑CPU核数-1。在大量请求过来的时候，处理逻辑复杂的话，响应会很慢。一般建议使用自定义线程池，优化线程池配置参数。
 * - 自定义线程池时，注意拒绝策略
 */
public class CompletableFutureApi {

    /**
     * supplyAsync执行CompletableFuture任务，支持返回值
     */
    @Test
    public void supplyAsync() {
        Supplier<String> sp = () -> "hello, future";
        // 使用默认内置线程池ForkJoinPool.commonPool()，根据supplier构建执行任务
        CompletableFuture<String> future = OU.obs(CompletableFuture.supplyAsync(sp), false);
        System.out.println("future.join() = " + OU.apd(future.join(), "future.join()", true));
    }

    /**
     * runAsync执行CompletableFuture任务，无返回值
     */
    @Test
    public void runAsync() {
        Runnable r = () -> System.out.println("hello, future");
        // 使用默认内置线程池ForkJoinPool.commonPool()，根据runAsync构建执行任务
        CompletableFuture<Void> future = OU.obs(CompletableFuture.runAsync(r));
        System.out.println("future.join() = " + OU.apd(future.join(), "future.join()", true));
    }

    /**
     * CompletableFuture的thenRun方法，通俗点讲就是，做完第一个任务后，再做第二个任务。
     * 某个任务执行完成后，执行回调方法；但是前后两个任务没有参数传递，第二个任务也没有返回值
     */
    @Test
    public void thenRun() {
        Runnable r = () -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("hello, future");
        };
        CompletableFuture<Void> future = OU.obs(CompletableFuture.runAsync(r).thenRun(r), false);
        System.out.println("future.join() = " + OU.apd(future.join(), "future.join()", true));
    }

    /**
     * thenRun 和thenRunAsync有什么区别呢？
     * 如果执行第一个任务的时候，传入了一个自定义线程池：
     * 调用thenRun方法执行第二个任务时，则第二个任务和第一个任务是共用同一个线程池。
     * 调用thenRunAsync执行第二个任务时，则第一个任务使用的是你自己传入的线程池，第二个任务使用的是ForkJoin线程池
     */
    @Test
    public void thenRunAsync() {
        Runnable r = () -> System.out.println(Thread.currentThread().getName());
        CompletableFuture<Void> future = OU.obs(CompletableFuture.runAsync(r).thenRunAsync(r), false);
        System.out.println("future.join() = " + OU.apd(future.join(), "future.join()", true));
    }

    /**
     * CompletableFuture的thenAccept方法表示，第一个任务执行完成后，执行第二个回调方法任务.
     * 会将该任务的执行结果，作为入参，传递到回调方法中，但是回调方法是没有返回值的。
     */
    @Test
    public void thenAccept() {
        Supplier<String> sp = () -> Thread.currentThread().getName();
        Consumer<String> cs = s -> {
            System.out.println(s);
            System.out.println(Thread.currentThread().getName());
        };
        CompletableFuture<Void> future = OU.obs(CompletableFuture.supplyAsync(sp).thenAccept(cs), false);
        System.out.println("future.join() = " + OU.apd(future.join(), "future.join()", true));
    }

    @Test
    public void thenAcceptAsync() {
        Supplier<String> sp = () -> Thread.currentThread().getName();
        Consumer<String> cs = s -> {
            System.out.println(s);
            System.out.println(Thread.currentThread().getName());
        };
        CompletableFuture<Void> future = OU.obs(CompletableFuture.supplyAsync(sp).thenAcceptAsync(cs), false);
        System.out.println("future.join() = " + OU.apd(future.join(), "future.join()", true));
    }

    /**
     * CompletableFuture的thenApply方法表示，第一个任务执行完成后，
     * 执行第二个回调方法任务，会将该任务的执行结果，作为入参，传递到回调方法中，并且回调方法是有返回值的。
     */
    @Test
    public void thenApply() {
        Supplier<String> sp = () -> Thread.currentThread().getName();
        Function<String, String> cs = s -> {
            System.out.println(s);
            return Thread.currentThread().getName();
        };
        CompletableFuture<String> future = OU.obs(CompletableFuture.supplyAsync(sp).thenApply(cs), false);
        System.out.println("future.join() = " + OU.apd(future.join(), "future.join()", true));
    }

    @Test
    public void thenApplyAsync() {
        Supplier<String> sp = () -> Thread.currentThread().getName();
        Function<String, String> cs = s -> {
            System.out.println(s);
            return Thread.currentThread().getName();
        };
        CompletableFuture<String> future = OU.obs(CompletableFuture.supplyAsync(sp).thenApplyAsync(cs), false);
        System.out.println("future.join() = " + OU.apd(future.join(), "future.join()", true));
    }

    /**
     * exceptionally方法表示，某个任务执行异常时，执行的回调方法;并且有抛出异常作为参数，传递到回调方法。
     */
    @Test
    public void exceptionally() {
        Supplier<String> sp = () -> {
            throw new RuntimeException(Thread.currentThread().getName());
        };
        Function<Throwable, String> cs = e -> {
            e.printStackTrace();
            return Thread.currentThread().getName();
        };
        CompletableFuture<String> future = OU.obs(CompletableFuture.supplyAsync(sp).exceptionally(cs), false);
        System.out.println("future.join() = " + OU.apd(future.join(), "future.join()", true));
    }

    /**
     * whenComplete方法表示，某个任务执行完成后，执行的回调方法，无返回值；
     * 并且whenComplete方法返回的CompletableFuture的result是上个任务的结果。
     */
    @Test
    public void whenComplete() {
        Supplier<String> sp = () -> {
            throw new RuntimeException(Thread.currentThread().getName());
        };
        BiConsumer<String, Throwable> bc = (s, e) -> {
            // s的值是上一个任务的返回值
            System.out.println(s);
            System.out.println(Thread.currentThread().getName());
            e.printStackTrace();
        };
        CompletableFuture<String> future = OU.obs(CompletableFuture.supplyAsync(sp).whenComplete(bc), false);
        System.out.println("future.join() = " + OU.apd(future.join(), "future.join()", true));
    }

    /**
     * handle方法表示，某个任务执行完成后，执行回调方法，并且是有返回值的;
     * 并且handle方法返回的CompletableFuture的result是回调方法执行的结果。
     */
    @Test
    public void handle() {
        Supplier<String> sp = () -> {
            throw new RuntimeException(Thread.currentThread().getName());
        };
        BiFunction<String, Throwable, String> bc = (s, e) -> {
            // s的值是上一个任务的返回值
            System.out.println(s);
            e.printStackTrace();
            return Thread.currentThread().getName();
        };
        CompletableFuture<String> future = OU.obs(CompletableFuture.supplyAsync(sp).handle(bc), false);
        System.out.println("future.join() = " + OU.apd(future.join(), "future.join()", true));
    }

    /**
     * thenCombine / thenAcceptBoth / runAfterBoth都表示：将两个CompletableFuture组合起来，只有这两个都正常执行完了，才会执行某个任务。
     */
    @Test
    public void thenCombine() {
        Supplier<String> sp = () -> Thread.currentThread().getName();
        BiFunction<String, String, String> bc = (s1, s2) -> {
            // s1,s2分别是future1和future2两个方法的结果
            System.out.println("s1 = " + s1);
            System.out.println("s2 = " + s2);
            return Thread.currentThread().getName();
        };
        CompletableFuture<String> first = CompletableFuture.supplyAsync(sp);
        CompletableFuture<String> second = OU.obs(CompletableFuture.supplyAsync(sp).thenCombine(first, bc), false);
        System.out.println("future.join() = " + OU.apd(second.join(), "future.join()", true));
    }

    @Test
    public void thenAcceptBoth() {
        Supplier<String> sp = () -> Thread.currentThread().getName();
        BiConsumer<String, String> bc = (s1, s2) -> {
            // s1,s2分别是future1和future2两个方法的结果
            System.out.println("s1 = " + s1);
            System.out.println("s2 = " + s2);
        };
        CompletableFuture<String> first = CompletableFuture.supplyAsync(sp);
        CompletableFuture<Void> second = OU.obs(CompletableFuture.supplyAsync(sp).thenAcceptBoth(first, bc), false);
        System.out.println("future.join() = " + OU.apd(second.join(), "future.join()", true));
    }

    @Test
    public void runAfterBoth() {
        Supplier<String> sp = () -> Thread.currentThread().getName();
        // 在first和second两个任务都完成以后执行Runnable r
        Runnable r = () -> System.out.println(Thread.currentThread().getName());
        CompletableFuture<String> first = CompletableFuture.supplyAsync(sp);
        CompletableFuture<Void> second = OU.obs(CompletableFuture.supplyAsync(sp).runAfterBoth(first, r), false);
        System.out.println("future.join() = " + OU.apd(second.join(), "future.join()", true));
    }

    /**
     * applyToEither / acceptEither / runAfterEither 都表示：
     * 将两个CompletableFuture组合起来，只要其中一个执行完了,就会执行某个任务。
     */
    @Test
    public void acceptEither() {
        Supplier<String> sp = () -> {
            String name = Thread.currentThread().getName();
            System.out.println(name);
            return name;
        };
        // 两个方法任一个完成后发起回调，s是先完成任务的结果
        Consumer<String> cs = s -> System.out.println(s);
        CompletableFuture<String> first = CompletableFuture.supplyAsync(sp);
        CompletableFuture<Void> second = OU.obs(CompletableFuture.supplyAsync(sp).acceptEither(first, cs), false);
        System.out.println("future.join() = " + OU.apd(second.join(), "future.join()", true));
    }

    @Test
    public void applyToEither() {
        AtomicInteger i = new AtomicInteger(0);
        Supplier<String> sp = () -> {
            String name = Thread.currentThread().getName();
            System.out.println(i.incrementAndGet() + name);
            return name;
        };
        // 两个方法任一个完成后发起回调，s是先完成任务的结果
        Function<String, String> cs = s -> s;
        CompletableFuture<String> first = CompletableFuture.supplyAsync(sp);
        CompletableFuture<String> second = OU.obs(CompletableFuture.supplyAsync(sp).applyToEither(first, cs), false);
        System.out.println("future.join() = " + OU.apd(second.join(), "future.join()", true));
    }

    @Test
    public void runAfterEither() {
        AtomicInteger i = new AtomicInteger(0);
        Supplier<String> sp = () -> {
            String name = Thread.currentThread().getName();
            System.out.println(i.incrementAndGet() + name);
            return name;
        };
        // 两个方法任一个完成后发起回调，r是一个任务
        Runnable r = () -> System.out.println(i + Thread.currentThread().getName());
        CompletableFuture<String> first = CompletableFuture.supplyAsync(sp);
        CompletableFuture<Void> second = OU.obs(CompletableFuture.supplyAsync(sp).runAfterEither(first, r), false);
        System.out.println("future.join() = " + OU.apd(second.join(), "future.join()", true));
    }

    @Test
    public void allOf() {
        AtomicInteger i = new AtomicInteger(0);
        Supplier<String> sp = () -> {
            String name = Thread.currentThread().getName();
            System.out.println(i.incrementAndGet() + name);
            return name;
        };
        BiConsumer<Void, Throwable> bc = (s, e) -> System.out.println(s);
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(sp);
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(sp);
        // 两个方法全部完成后执行回调bc
        CompletableFuture<Void> lastFuture = OU.obs(CompletableFuture.allOf(f1, f2).whenComplete(bc), false);
        System.out.println("future.join() = " + OU.apd(lastFuture.join(), "future.join()", true));
    }

    @Test
    public void anyOf() {
        AtomicInteger i = new AtomicInteger(0);
        Supplier<String> sp = () -> {
            String name = Thread.currentThread().getName();
            System.out.println(i.incrementAndGet() + name);
            return name;
        };
        BiConsumer<Object, Throwable> bc = (s, e) -> System.out.println(s);
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(sp);
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(sp);

        // f1,f2任意一个任务完成或异常终止后，执行回调bc
        CompletableFuture<Object> lastFuture = OU.obs(CompletableFuture.anyOf(f1, f2).whenComplete(bc), false);
        System.out.println("future.join() = " + OU.apd(lastFuture.join(), "future.join()", true));
    }

    /**
     * thenCompose方法会在某个任务执行完成后，将该任务的执行结果,作为方法入参,去执行指定的方法。该方法会返回一个新的CompletableFuture实例
     * 如果该CompletableFuture实例的result不为null，则返回一个基于该result新的CompletableFuture实例；
     * 如果该CompletableFuture实例为null，然后就执行这个新任务
     */
    @Test
    public void thenCompose() {
        AtomicInteger i = new AtomicInteger(0);
        Supplier<String> sp = () -> {
            String name = Thread.currentThread().getName();
            System.out.println(i.incrementAndGet() + name);
            return name;
        };
        Function<String, CompletionStage<String>> fc = s -> CompletableFuture.supplyAsync(sp);

        // 串行化执行任务，last为后一个任务的结果
        CompletableFuture<String> last = OU.obs(CompletableFuture.supplyAsync(sp).thenCompose(fc), false);
        System.out.println("future.join() = " + OU.apd(last.join(), "future.join()", true));
    }
}
