package model;

public class Worker implements Runnable {

    private int id;
    private long successfulOrders;
    private long workTime;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public long getSuccessfulOrders() {
        return successfulOrders;
    }
    public void setSuccessfulOrders(long successfulOrders) {
        this.successfulOrders = successfulOrders;
    }

    public long getWorkTime() {
        return workTime;
    }
    public void setWorkTime(long workTime) {
        this.workTime = workTime;
    }

    @Override
    public void run(){
    }
}
