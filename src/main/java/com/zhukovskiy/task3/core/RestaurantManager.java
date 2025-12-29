package com.zhukovskiy.task3.core;

import com.zhukovskiy.task3.entity.Kitchen;
import com.zhukovskiy.task3.entity.Order;
import com.zhukovskiy.task3.entity.Table;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RestaurantManager {
    private static final Logger logger = LogManager.getLogger();

    private final List<Table> tables;
    private final Kitchen kitchen;

    public RestaurantManager(List<Table> tables, Kitchen kitchen) {
        this.tables = tables;
        this.kitchen = kitchen;
    }

    public void serveVisitors(List<Order> orders) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(tables.size());
        List<Future<String>> results = new ArrayList<>();

        for (int i = 0; i < orders.size(); i++) {
            Table table = tables.get(i % tables.size());
            results.add(pool.submit(new VisitorTask(orders.get(i), table, kitchen)));
        }

        for (Future<String> f : results) {
            System.out.println(f.get());
        }
        pool.shutdown();
    }
}

