package model;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ExecutorService;

public class WorkerManager implements Runnable {

    private ExecutorService exec;
    private Queue<Order> orderQueue;
    private PriorityQueue<Worker> workersOnWork = new PriorityQueue<>();
    private Queue<Worker> workersOnRest = new LinkedList<>();

    public WorkerManager(ExecutorService exec, Queue<Order> orderQueue) {
        this.exec = exec;
        this.orderQueue = orderQueue;
    }

    public void adjustWorkerNumber() {
        if(orderQueue.size() / workersOnWork.size() > 2) {
            if(workersOnRest.size() > 0) {
                Worker worker = workersOnRest.remove();
                worker.serveOrderLine();
                workersOnWork.offer(worker);
                System.out.println(worker.getId() + " Работает");
                return;
            }
            Worker worker = new Worker(orderQueue);
            exec.execute(worker);
            workersOnWork.add(worker);
            System.out.println(worker.getId() + " Нанят");
            System.out.println(worker.getId() + " Работает");
            return;
        }
        if(workersOnWork.size() > 1 && orderQueue.size() / workersOnWork.size() < 2) {
            reassignOneWorker();
        }
        if(orderQueue.size() == 0) {
            while(workersOnWork.size() > 1)
                reassignOneWorker();
        }
    }

    private void reassignOneWorker() {
        Worker worker = workersOnWork.poll();
        worker.doSomethingElse();
        workersOnRest.offer(worker);
        System.out.println(worker.getId() + " Отдыхает");
    }

    public void run() {
        Worker worker = new Worker(orderQueue);
        exec.execute(worker);
        workersOnWork.add(worker);
        while(!Thread.interrupted()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            adjustWorkerNumber();
        }
    }
}
