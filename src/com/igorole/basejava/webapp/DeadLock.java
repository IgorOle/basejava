package com.igorole.basejava.webapp;

import java.util.Random;

class Account {
    private String AccName;
    private String Account;
    private float Balance;

    public Account(String accName, String account, float balance) {
        AccName = accName;
        Account = account;
        Balance = balance;
    }

    public float getBalance() {
        return Balance;
    }

    public String getAccount() {
        return Account;
    }

    public void deduct(Float val) {
        this.Balance -= val;
    }

    public void add(Float val) {
        this.Balance += val;
    }
}

class Operations implements Runnable {
    private final Account Dt;
    private final Account Kt;
    private final Float Val;
    private final int Sleep;
    private String name;

    public Operations(Account Dt, Account Kt, float Val, int Sleep, String name) {
        this.Dt = Dt;
        this.Kt = Kt;
        this.Val = Val;
        this.Sleep = Sleep;
        this.name = name;
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
        System.out.println("---" + this.name + " " + Dt.getAccount() + " " + Kt.getAccount());
    }

    @Override
    public void run() {
        todo(Dt, Kt, Val);
        System.out.println(this);
    }


}

public class DeadLock {
    public static void main(String[] args) {
        Account acc1 = new Account("acc1", "40802810..1", 100f);
        Account acc2 = new Account("acc2", "40802810..2", 100f);

        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                (new Operations(acc1, acc2, 10, new Random().nextInt(3), "thread" + i)).run();
            } else {
                (new Operations(acc2, acc1, 10, new Random().nextInt(4), "thread" + i)).run();
            }
        }

        System.out.println("sum = " + (acc1.getBalance() + acc2.getBalance()));
    }
}
