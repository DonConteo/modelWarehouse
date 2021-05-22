package model;

import java.util.*;

import static model.EnumPosition.randomPosition;

public class OrderGenerator implements Runnable {

    private int id = 1;
    Random random = new Random();
    Queue<Order> orderQueue;

    public OrderGenerator(Queue<Order> oq) {
        orderQueue = oq;
    }

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
    public void run() {
        while (!Thread.interrupted()) {
            Order order = generateOrder();
            orderQueue.add(order);
            System.out.println("Поступил заказ " + order.getId() + " " + order.getPositions());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
