package com.zhukovskiy.task3.entity;

import java.util.concurrent.Semaphore;

public class Kitchen {
    private final Semaphore dishes;

    public Kitchen(int capacity) {
        this.dishes = new Semaphore(capacity, true);
    }

    public boolean takeDish() {
        return dishes.tryAcquire();
    }

    public void returnDish() {
        dishes.release();
    }
}

