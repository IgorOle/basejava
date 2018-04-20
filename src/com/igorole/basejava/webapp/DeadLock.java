package com.igorole.basejava.webapp;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Account {
    private String AccName;
    private String Account;
    private float Balance;

    public Account(String accName, String account, float balance) {
        AccName = accName;
        Account = account;
        Balance = balance;
    }

    public void deduct(Float val) {
        this.Balance -= val;
    }

    public void add(Float val) {
        this.Balance += val;
    }

    public float getBalance() {
        return Balance;
    }
}

class Operations extends Thread {
    private final Account Dt;
    private final Account Kt;
    private final Float Val;


    public Operations(Account Dt, Account Kt, float Val) {
        this.Dt = Dt;
        this.Kt = Kt;
        this.Val = Val;
    }

    void todo(Account Dt, Account Kt, Float sum) {
        synchronized (Dt) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Dt.deduct(sum);
            synchronized (Kt) {
                Kt.add(sum);
            }
        }
    }

    @Override
    public void run() {
        System.out.println(this);
        todo(Dt, Kt, Val);
    }
}

public class DeadLock {
    public static void main(String[] args) {
        Account acc1 = new Account("acc1", "40802810..1", 100f);
        Account acc2 = new Account("acc2", "40802810..2", 100f);

        for (int i = 0; i < 20000; i++) {
            if (i % 2 == 0) {
                (new Operations(acc1, acc2, 10)).start();
            } else {
                (new Operations(acc2, acc1, 10)).start();
            }
        }
        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(acc1.getBalance() + acc2.getBalance());
    }
}
