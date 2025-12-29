package com.zhukovskiy.task3.factory.impl;

import com.zhukovskiy.task3.entity.Table;
import com.zhukovskiy.task3.factory.TableFactory;

public class TableFactoryImpl implements TableFactory {
    @Override
    public Table createTable(int id) {
        return new Table(id);
    }
}

