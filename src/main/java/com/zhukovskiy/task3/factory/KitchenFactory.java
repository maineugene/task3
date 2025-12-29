package com.zhukovskiy.task3.factory;

import com.zhukovskiy.task3.entity.Kitchen;

public interface KitchenFactory {
    Kitchen createKitchen(int capacity);
}

