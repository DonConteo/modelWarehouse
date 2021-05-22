package model;

public class Position {

    private String name;
    private int quantity;
    private int time;

    public Position(String name, int quantity, int time) {
        this.name = name;
        this.quantity = quantity;
        this.time = time;
    }

    public Position() {
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTime() {
        return time;
    }
    public void setTime(int time) {
        this.time = time;
    }
}
