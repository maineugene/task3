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
        logger.info("Начало обслуживания {} заказов, доступно {} столов, вместимость кухни {}",
                orders.size(), tables.size(), kitchen);

        ExecutorService pool = Executors.newFixedThreadPool(tables.size());
        List<Future<String>> results = new ArrayList<>();

        for (int i = 0; i < orders.size(); i++) {
            Table table = tables.get(i % tables.size());
            logger.debug("Назначаем заказ {} посетителя {} на стол {}", orders.get(i).getDishCount(),
                    orders.get(i).getVisitorId(), table.getId());

            VisitorTask task = new VisitorTask(orders.get(i), table, kitchen);
            Future<String> future = pool.submit(task);
            results.add(future);
        }

        for (Future<String> f : results) {
            String result = f.get();
            logger.info("Результат выполнения задачи: {}", result);
            System.out.println(result);
        }
        pool.shutdown();
        logger.info("Все заказы обработаны, пул потоков закрыт.");
    }
}

