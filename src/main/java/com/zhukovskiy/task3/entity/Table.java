package com.zhukovskiy.task3.entity;

import java.util.concurrent.locks.ReentrantLock;

public class Table {
    private final int id;
    private final ReentrantLock lock = new ReentrantLock(true);
    public Table(int id) { this.id = id; }
    public boolean occupy() { return lock.tryLock(); }
    public void release() { lock.unlock(); }
    public int getId() { return id; }
}
