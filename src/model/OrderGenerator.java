package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

import static model.EnumPosition.randomPosition;

public class OrderGenerator implements Callable<Order> {

    Random random = new Random();

    public Position generatePosition() {
        EnumPosition enumPosition = randomPosition();
        Position position = new Position();

        int quantity = random.nextInt(9 + 1);

        position.setName(String.valueOf(enumPosition));
        position.setQuantity(quantity);
        position.setTime(enumPosition.getTime() * position.getQuantity());
        return position;
    }

    public Order generateOrder() {
        int id = 1;
        Order order = new Order();
        List<Position> positions = new ArrayList<>();
        int quantityOfPositions = random.nextInt(9+1);

        for(int i = 1; i <= quantityOfPositions; i++) {
            positions.add(generatePosition());
        }

        int fullTime = 0;
        for(Position position : positions) {
            fullTime = fullTime + position.getTime();
        }

        order.setId(id);
        id++;
        order.setPositions(positions);
        order.setFullTime(fullTime);
        return order;
    }

    @Override
    public Order call() throws InterruptedException {
        Thread.sleep(5000);
        Order order = generateOrder();
        return order;
    }
}
