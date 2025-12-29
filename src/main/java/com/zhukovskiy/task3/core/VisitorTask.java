package com.zhukovskiy.task3.core;

import com.zhukovskiy.task3.entity.Kitchen;
import com.zhukovskiy.task3.entity.Order;
import com.zhukovskiy.task3.entity.Table;
import com.zhukovskiy.task3.state.EatingState;
import com.zhukovskiy.task3.state.FinishedState;
import com.zhukovskiy.task3.state.VisitorState;
import com.zhukovskiy.task3.state.WaitingState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class VisitorTask implements Callable<String> {
    private static final Logger logger = LogManager.getLogger();

    private final Order order;
    private final Table table;
    private final Kitchen kitchen;
    private VisitorState state = new WaitingState();

    public VisitorTask(Order order, Table table, Kitchen kitchen) {
        this.order = order;
        this.table = table;
        this.kitchen = kitchen;
    }

    @Override
    public String call() throws Exception {
        if (!table.occupy()) {
            logger.warn("Visitor {} could not get table {}", order.getVisitorId(), table.getId());
            return "Visitor " + order.getVisitorId() + " failed: no table";
        }

        state = new EatingState();
        int dishesTaken = 0;
        for (int i = 0; i < order.getDishCount(); i++) {
            if (kitchen.takeDish()) {
                dishesTaken++;
                TimeUnit.MILLISECONDS.sleep(500);
                kitchen.returnDish();
            }
        }
        state = new FinishedState();
        table.release();
        logger.info("Visitor {} finished at table {} with {} dishes", order.getVisitorId(), table.getId(), dishesTaken);
        return "Visitor " + order.getVisitorId() + " state=" + state.name();
    }
}

