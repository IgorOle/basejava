//package com.igorole.basejava.webapp;
//
//
//import com.igorole.basejava.webapp.sql.SqlHelper;
//
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.util.concurrent.Callable;
//import java.util.concurrent.CountDownLatch;
//
//class ThreadMy implements Callable {
//    String name;
//    CountDownLatch cd;
//
//    public ThreadMy(CountDownLatch cd, String name) {
//        this.name = name;
//        this.cd = cd;
//    }
//
//    @Override
//    public Object call() throws Exception {
//        System.out.println("-");
//        cd.countDown();
//        cd.await();
////        Thread.sleep(1000);
//        System.out.println(System.currentTimeMillis());
//        return name;
//    }
//}
//
//public class Test1 {
//    static int inc = 0;
//
//    public static void main(String[] args) {
//        SqlHelper sqlHelper = new SqlHelper(() -> DriverManager.getConnection(Config.get().getUrl(), Config.get().getUser(), Config.get().getPassword()));
//        for (int i = 0; i < 10000; i++) {
//
//            sqlHelper.transactionalExecute(conn -> {
//                try (PreparedStatement ps = conn.prepareStatement("insert into t1(id, dat) values(?,?)")) {
//                    inc++;
//                    ps.setInt(1, inc);
//                    ps.setString(2, "t1_" + inc);
//                    ps.execute();
//
//                }
////                return null;
////
////                try (PreparedStatement ps = addContacts(conn, r)) {
////                    ps.executeBatch();
////                }
////                return null;
////            });
//
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
//
//
//        }
//    }
