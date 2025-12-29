package com.zhukovskiy.task3;

import com.zhukovskiy.task3.context.RestaurantContext;
import com.zhukovskiy.task3.core.RestaurantManager;
import com.zhukovskiy.task3.entity.Kitchen;
import com.zhukovskiy.task3.entity.Order;
import com.zhukovskiy.task3.entity.Table;
import com.zhukovskiy.task3.factory.KitchenFactory;
import com.zhukovskiy.task3.factory.TableFactory;
import com.zhukovskiy.task3.factory.impl.KitchenFactoryImpl;
import com.zhukovskiy.task3.factory.impl.TableFactoryImpl;
import com.zhukovskiy.task3.parser.OrderParser;
import com.zhukovskiy.task3.parser.impl.OrderParserImpl;
import com.zhukovskiy.task3.reader.FileDataReader;
import com.zhukovskiy.task3.reader.impl.FileDataReaderImpl;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        FileDataReader reader = new FileDataReaderImpl();
        String data = reader.readData("data/orders.txt");

        OrderParser parser = new OrderParserImpl();
        List<Order> orders = parser.parseOrders(data);

        TableFactory tableFactory = new TableFactoryImpl();
        KitchenFactory kitchenFactory = new KitchenFactoryImpl();

        List<Table> tables = Arrays.asList(
                tableFactory.createTable(1),
                tableFactory.createTable(2),
                tableFactory.createTable(3)
        );
        Kitchen kitchen = kitchenFactory.createKitchen(5); // кухня может готовить 5 блюд одновременно

        RestaurantManager manager = new RestaurantManager(tables, kitchen);

        RestaurantContext.init(manager);

        RestaurantContext.get().manager().serveVisitors(orders);
    }
}


