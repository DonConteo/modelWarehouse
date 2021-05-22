package model;

import java.util.List;

public class Order {

    private int id;
    private List<Position> positions;
    private int fullTime;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public List<Position> getPositions() {
        return positions;
    }
    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public int getFullTime() {
        return fullTime;
    }
    public void setFullTime(int fullTime) {
        this.fullTime = fullTime;
    }
}
