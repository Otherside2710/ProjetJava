package com.esiea.tp4A.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashSet;
import java.util.Set;

public class AvancerObstacleMarsRoverTest {
    PlanetMapImpl mapTest= new PlanetMapImpl(100);
    Set<Position> listobstacle = new HashSet<>();

    Position obs1 = Position.of(1,0,null);
    Position obs2 = Position.of(0,1,null);
    Position obs3 = Position.of(10,20,null);
    Position obs4 = Position.of(15,1,null);
    Position obslim1 = Position.of(50,1,null);
    Position obslim2 = Position.of(1,50,null);
    Position obslim3 = Position.of(-49,20,null);
    Position obslim4 = Position.of(15,-49,null);

    @ParameterizedTest
    @CsvSource
        ({
            "'f', 0,0, NORTH, 0, 0, NORTH",
            "'f', 0,0, SOUTH, 0, -1, SOUTH",
            "'f', 0,0, EAST, 0, 0, EAST",
            "'f', 0,0, WEST, -1, 0, WEST",

            "'f', 10, 18, NORTH, 10, 19, NORTH",
            "'f', 10, 19, NORTH, 10, 19, NORTH",
            "'f', 12, 21, SOUTH, 12, 20, SOUTH",
            "'f', 10, 21, SOUTH, 10, 21, SOUTH",
            "'f', 8,30, EAST, 9, 30, EAST",
            "'f', 16, 1, WEST, 16, 1, WEST",
            "'f', 16, 30, WEST, 15, 30, WEST",

        })
    void AvancerObstacleTest(String commande, int x, int y, Direction direction, int x_result, int y_result, Direction direction_result ){
        listobstacle.add(obs1);
        listobstacle.add(obs2);
        listobstacle.add(obs3);
        listobstacle.add(obs4);
        MarsRover marsRover= new MarsRoverImpl(x,y, direction,listobstacle,null,mapTest.getTaille());
        Position position=marsRover.move(commande);
        Assertions.assertThat(position).isEqualTo(Position.of(x_result,y_result,direction_result));

    }


    @ParameterizedTest
    @CsvSource
        ({
            "'f', 0,0, NORTH, 0, 1, NORTH",
            "'f', 0,0, SOUTH, 0, -1, SOUTH",
            "'f', 1,0, EAST, 2, 0, EAST",
            "'f', 1,0, WEST, 0, 0, WEST",

            "'f', 15, 50, NORTH, 15, 50, NORTH",
            "'f', 10, 50, NORTH, 10, -49, NORTH",
            "'f', 1, -49, SOUTH, 1, -49, SOUTH",
            "'f', 2, -49, SOUTH, 2, 50, SOUTH",
            "'f', 50,20, EAST, 50, 20, EAST",
            "'f', 50,30, EAST, -49, 30, EAST",
            "'f', -49, 1, WEST, -49, 1, WEST",
            "'f', -49, 5, WEST, 50, 5, WEST",

        })
    void AvancerObstacleLimMapTest(String commande, int x, int y, Direction direction, int x_result, int y_result, Direction direction_result ){
        listobstacle.add(obslim1); listobstacle.add(obslim2); listobstacle.add(obslim3); listobstacle.add(obslim4);
        MarsRover marsRover= new MarsRoverImpl(x,y, direction,listobstacle,null,mapTest.getTaille());
        Position position=marsRover.move(commande);
        Assertions.assertThat(position).isEqualTo(Position.of(x_result,y_result,direction_result));

    }
}
