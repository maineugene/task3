package com.zhukovskiy.task3.parser;

import com.zhukovskiy.task3.entity.Order;

import java.util.List;

public interface OrderParser {
    List<Order> parseOrders(String data);
}

