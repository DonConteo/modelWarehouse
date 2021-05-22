package model;

import java.util.Queue;

public class Worker implements Runnable {

    private static int count = 1;
    private final int id = count++;
    private long servedOrders = 0;
    private long workTime = 0;
    private Queue<Order> orderQueue;
    private boolean servingOrderQueue = true;

    public Worker(Queue<Order> oq) {
        orderQueue = oq;
    }

    public int getId() {
        return id;
    }

    public long getSuccessfulOrders() {
        return servedOrders;
    }
    public void setSuccessfulOrders(long successfulOrders) {
        this.servedOrders = successfulOrders;
    }

    public long getWorkTime() {
        return workTime;
    }
    public void setWorkTime(long workTime) {
        this.workTime = workTime;
    }

    @Override
    public void run(){
        while(!Thread.interrupted()) {
            Order order = orderQueue.poll();
            try {
                Thread.sleep(order.getFullTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Заказ номер " + order.getId() + " выполнен");
            synchronized (this) {
                servedOrders++;
                workTime += order.getFullTime();
                System.out.println("Выполненные заказы " + servedOrders + " Время работы" + workTime);
                while (!servingOrderQueue){
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public synchronized void doSomethingElse() {
        servingOrderQueue = false;
    }

    public synchronized void serveOrderLine() {
        servingOrderQueue = true;
        notifyAll();
    }
}
