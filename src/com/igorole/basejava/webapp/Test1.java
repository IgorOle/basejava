package com.igorole.basejava.webapp;


import javafx.collections.ArrayChangeListener;
import org.omg.CORBA.Current;

import java.util.ArrayList;
import java.util.concurrent.*;

class ThreadMy implements Callable {
    String name;
    CountDownLatch cd;
    public ThreadMy(CountDownLatch cd, String name) {
        this.name = name;
        this.cd = cd;
    }

    @Override
    public Object call() throws Exception {
        System.out.println("-");
        cd.countDown();
        cd.await();
//        Thread.sleep(1000);
        System.out.println(System.currentTimeMillis());
        return name;
    }
}

public class Test1 {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);

        CountDownLatch cd = new CountDownLatch(2);
        ArrayList<Future> arr = new ArrayList<>();
        for(int i = 0; i< 30; i++) {
            arr.add(service.submit( new ThreadMy(cd, "t"+i)));
        }
        try {
            for (Future f : arr ) {
                System.out.println(f.get());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("end");
        service.shutdown();
//
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        service.submit(new ThreadMy(cd, "t2"));






    }
}
