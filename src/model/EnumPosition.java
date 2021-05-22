package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum EnumPosition {
    COFFEE(150),
    TEA(100),
    BREAD(50),
    SAW(200),
    JIGSAW(250),
    MILK(150),
    PIZZA(500),
    CAT(1500),
    ROBOT_DESTROYER(3000),
    CHOCOLATE(50),
    IMPERIAL_BATTLE_MOTHERSHIP(5000);

    private int time;

    EnumPosition(int time) {
        this.time = time;
    }

    private static final List<EnumPosition> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static EnumPosition randomPosition() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    public int getTime() {
        return time;
    }
}
