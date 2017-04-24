package com.javarush.task.task28.task2805;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Rumata on 21.04.2017.
 */
public class MyThread extends Thread {
    public static AtomicInteger counter = new AtomicInteger(1);

    public MyThread() {
        if (counter.get() < 10) setPriority(counter.getAndIncrement());
        else {
            setPriority(counter.get());
            counter.set(1);
        }
    }

    public MyThread(Runnable target) {
        super(target);
        if (counter.get() < 10) setPriority(counter.getAndIncrement());
        else {
            setPriority(counter.get());
            counter.set(1);
        }

    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        if (counter.get() < 10) setPriority(counter.getAndIncrement());
        else {
            setPriority(counter.get());
            counter.set(1);
        }
    }

    public MyThread(String name) {
        super(name);
        if (counter.get() < 10) setPriority(counter.getAndIncrement());
        else {
            setPriority(counter.get());
            counter.set(1);
        }
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        if (counter.get() < 10) setPriority(counter.getAndIncrement());
        else {
            setPriority(counter.get());
            counter.set(1);
        }
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        if (counter.get() < 10) setPriority(counter.getAndIncrement());
        else {
            setPriority(counter.get());
            counter.set(1);
        }
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        if (counter.get() < 10) setPriority(counter.getAndIncrement());
        else {
            setPriority(counter.get());
            counter.set(1);
        }
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        if (counter.get() < 10) setPriority(counter.getAndIncrement());
        else {
            setPriority(counter.get());
            counter.set(1);
        }
    }


}

