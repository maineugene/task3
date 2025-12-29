package com.zhukovskiy.task3.context;

import com.zhukovskiy.task3.core.RestaurantManager;

public class RestaurantContext {
    private static RestaurantContext instance;
    private final RestaurantManager manager;

    private RestaurantContext(RestaurantManager manager) {
        this.manager = manager;
    }

    public static void init(RestaurantManager manager) {
        if (instance == null) {
            instance = new RestaurantContext(manager);
        }
    }

    public static RestaurantContext get() {
        return instance;
    }

    public RestaurantManager manager() { return manager; }
}

