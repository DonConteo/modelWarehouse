import model.Order;
import model.OrderGenerator;
import model.WorkerManager;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WarehouseModel {

    public static void main(String args[]) throws ExecutionException, InterruptedException {

        ExecutorService exec = Executors.newCachedThreadPool();
        Queue<Order> orderQueue = new LinkedList<>();
        exec.execute(new OrderGenerator(orderQueue));
        Thread.sleep(10000);
        exec.execute(new WorkerManager(exec, orderQueue));
    }
}
