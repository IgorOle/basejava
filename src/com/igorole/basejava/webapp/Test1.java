package com.igorole.basejava.webapp;


import com.igorole.basejava.webapp.sql.SqlHelper;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

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
        SqlHelper sqlHelper = new SqlHelper(() -> DriverManager.getConnection(Config.get().getUrl(), Config.get().getUser(), Config.get().getPassword()));

        sqlHelper.transactionalExecute(conn -> {

            try (PreparedStatement ps = conn.prepareStatement("insert into t1(id, dat) values(?,?)")) {
                for (int i = 0; i < 1000_000; i++) {
                    ps.setInt(1, i);
                    ps.setString(2, "t1_" + i);
                    ps.execute();
                }
            }

            try (PreparedStatement ps = conn.prepareStatement("insert into t2(id, dat222) values(?,?)")) {
                for (int i = 0; i < 1000_000; i++) {
                    ps.setInt(1, i);
                    ps.setString(2, "t2_" + i);
                    ps.execute();
                }
            }
            return null;

        });

//            inc = 0;
//            for (int i = 0; i < 1000; i++) {
//                sqlHelper.execute("insert into t2(id, dat) values(?,?)", ps -> {
//                    inc++;
//                    ps.setInt(1, inc);
//                    ps.setString(2, "t2_" + inc);
//                    ps.execute();
//                    return null;
//                });
//            }


    }
}
