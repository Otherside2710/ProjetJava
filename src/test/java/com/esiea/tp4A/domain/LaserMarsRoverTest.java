package com.esiea.tp4A.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashSet;
import java.util.Set;

public class LaserMarsRoverTest {
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
            "'sf', 0,0, NORTH, 0, 1, NORTH",
            "'sff', 0,-1, NORTH, 0, 1, NORTH",
            "'sf', 0,0, SOUTH, 0, -1, SOUTH",
            "'sff', 0,1, SOUTH, 0, -1, SOUTH",
            "'sf', 0,0, EAST, 1, 0, EAST",
            "'sff', -1,0, EAST, 1, 0, EAST",
            "'sf', 1,0, WEST, 0, 0, WEST",
            "'sff', 12, 20, WEST, 10, 20, WEST",


            "'fsf', 15, 50, NORTH, 15, -49, NORTH ",
            "'sf', 1, -49, SOUTH, 1, 50, SOUTH",
            "'sff', 49, 20, EAST, -49, 20, EAST ",
            "'sff', -48, 1, WEST, 50,1,WEST",

        })
    void LaserTest(String commande, int x, int y, Direction direction, int x_result, int y_result, Direction direction_result ){

        listobstacle.add(obs1); listobstacle.add(obs2); listobstacle.add(obs3); listobstacle.add(obs4);
        listobstacle.add(obslim1); listobstacle.add(obslim2); listobstacle.add(obslim3); listobstacle.add(obslim4);
        MarsRover marsRover= new MarsRoverImpl(x,y, direction);
        Position position=marsRover.move(commande,mapTest.getTaille(),listobstacle,2);
        Assertions.assertThat(position).isEqualTo(Position.of(x_result,y_result,direction_result));

    }
}
