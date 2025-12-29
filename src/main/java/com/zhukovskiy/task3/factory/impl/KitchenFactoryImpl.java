package com.zhukovskiy.task3.factory.impl;

import com.zhukovskiy.task3.entity.Kitchen;
import com.zhukovskiy.task3.factory.KitchenFactory;

public class KitchenFactoryImpl implements KitchenFactory {
    @Override
    public Kitchen createKitchen(int capacity) {
        return new Kitchen(capacity);
    }
}

