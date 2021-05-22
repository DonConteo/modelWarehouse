import model.Order;
import model.OrderGenerator;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class WarehouseModel {

    public static void main(String args[]) throws ExecutionException, InterruptedException {

        OrderGenerator or = new OrderGenerator();
        Queue<Order> orderQueue = new LinkedList<>();

        ExecutorService executor = Executors.newSingleThreadExecutor();

        while(true) {
            Future<Order> order = executor.submit(() -> or.call());
            orderQueue.add(order.get());
            System.out.println(orderQueue);
        }
    }
}
