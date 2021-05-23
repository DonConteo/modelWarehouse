package model;

import java.util.Queue;

public class Worker implements Runnable, Comparable<Worker> {

    private static int count = 1;
    private final int id = count++;
    private long servedOrders = 0;
    private long workTime = 0;
    private Queue<Order> orderQueue;
    private boolean servingOrderQueue = true;
    private Order order;

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

    public void results() {
        System.out.println("Выполненные заказы " + servedOrders + " Время работы" + workTime);
    }

    @Override
    public void run(){
        while(!Thread.interrupted()) {
            order = orderQueue.element();
            orderQueue.poll();
            try {
                Thread.sleep(order.getFullTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Заказ номер " + order.getId() + " выполнен");
            servedOrders++;
            workTime += order.getFullTime();
            order = null;
        }
    }

    public synchronized void doSomethingElse() {
        servedOrders = 0;
        servingOrderQueue = false;
    }

    public synchronized void serveOrderLine() {
        servingOrderQueue = true;
        notifyAll();
    }

    public synchronized int compareTo(Worker worker) {
        return Long.compare(servedOrders, worker.servedOrders);
    }
}
