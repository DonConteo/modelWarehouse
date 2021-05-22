package model;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;

public class WorkerManager implements Runnable {

    private ExecutorService exec;
    private Queue<Order> orderQueue;
    private Queue<Worker> workersOnWork = new LinkedList<>();
    private Queue<Worker> workersOnRest = new LinkedList<>();

    public WorkerManager(ExecutorService e, Queue<Order> oq) {
        exec = e;
        orderQueue = oq;

        Worker worker = new Worker(orderQueue);
        exec.execute(worker);
        workersOnWork.add(worker);
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
        adjustWorkerNumber();
        for(Worker worker : workersOnWork)
            System.out.println(worker.getId() + " Работает");
    }
}
