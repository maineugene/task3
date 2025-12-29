package com.zhukovskiy.task3.parser.impl;

import com.zhukovskiy.task3.entity.Order;
import com.zhukovskiy.task3.parser.OrderParser;

import java.util.ArrayList;
import java.util.List;

public class OrderParserImpl implements OrderParser {
    private final String SPLIT_REGEX = "\\n";

    @Override
    public List<Order> parseOrders(String data) {
        List<Order> orders = new ArrayList<>();
        int id = 1;
        for (String line : data.split(SPLIT_REGEX)) {
            int dishes = Integer.parseInt(line.strip());
            orders.add(new Order(id++, dishes));
        }
        return orders;
    }
}

