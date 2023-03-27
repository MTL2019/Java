package AdvancedJavaProgramming.Chapter32MultipleThreading;

import java.util.concurrent.Semaphore;


/**
 *   using Semaphore class to simulate a mutually exclusive lock互斥锁
 */
public class AccountWithSemaphore {

    private static Semaphore semaphore = new Semaphore(1);
    private int balance = 0;

    public int getBalance(){
        return balance;
    }

    public void deposit(int amount){

        try{
            semaphore.acquire(); // Acquire a permit
            int newBalance = balance + amount;

            Thread.sleep(5);
            balance = newBalance;

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            semaphore.release();// Release a permit
        }

    }
}
