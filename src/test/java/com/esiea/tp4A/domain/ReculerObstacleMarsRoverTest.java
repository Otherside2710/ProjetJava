package com.esiea.tp4A.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashSet;
import java.util.Set;

public class ReculerObstacleMarsRoverTest {
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
            "'b', 0,0, NORTH, 0, -1, NORTH",
            "'b', 0,0, SOUTH, 0, 0, SOUTH",
            "'b', 0,0, EAST, -1, 0, EAST",
            "'b', 0,0, WEST, 0, 0, WEST",

            "'b', 10, 18, NORTH, 10, 17, NORTH",
            "'b', 10, 21, NORTH, 10, 21, NORTH",
            "'b', 12, 21, SOUTH, 12, 22, SOUTH",
            "'b', 10, 19, SOUTH, 10, 19, SOUTH",
            "'b', 8, 30, EAST, 7, 30, EAST",
            "'b', 16, 1, WEST, 17, 1, WEST",
            "'b', 16, 1, EAST, 16, 1, EAST",

        })
    void ReculerObstacleTest(String commande, int x, int y, Direction direction, int x_result, int y_result, Direction direction_result ){
        listobstacle.add(obs1); listobstacle.add(obs2); listobstacle.add(obs3); listobstacle.add(obs4);
        MarsRover marsRover= new MarsRoverImpl(x,y, direction,listobstacle,null,mapTest.getTaille());
        Position position=marsRover.move(commande);
        Assertions.assertThat(position).isEqualTo(Position.of(x_result,y_result,direction_result));

    }

    @ParameterizedTest
    @CsvSource
        ({
            "'b', 0,0, SOUTH, 0, 1, SOUTH",
            "'b', 0,0, NORTH, 0, -1, NORTH",
            "'b', 1,0, WEST, 2, 0, WEST",
            "'b', 1,0, EAST, 0, 0, EAST",

            "'b', 15, 50, SOUTH, 15, 50, SOUTH",
            "'b', 10, 50, SOUTH, 10, -49, SOUTH",
            "'b', 1, -49, NORTH, 1, -49, NORTH",
            "'b', 2, -49, NORTH, 2, 50, NORTH",
            "'b', 50,20, WEST, 50, 20, WEST",
            "'b', 50,30, WEST, -49, 30, WEST",
            "'b', -49, 1, EAST, -49, 1, EAST",
            "'b', -49, 5, EAST, 50, 5, EAST",

        })
    void ReculerObstacleLimMapTest(String commande, int x, int y, Direction direction, int x_result, int y_result, Direction direction_result ){
        listobstacle.add(obslim1); listobstacle.add(obslim2); listobstacle.add(obslim3); listobstacle.add(obslim4);
        MarsRover marsRover= new MarsRoverImpl(x,y, direction,listobstacle,null,mapTest.getTaille());
        Position position=marsRover.move(commande);
        Assertions.assertThat(position).isEqualTo(Position.of(x_result,y_result,direction_result));

    }
}
