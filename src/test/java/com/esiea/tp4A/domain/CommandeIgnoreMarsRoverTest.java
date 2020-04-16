package com.esiea.tp4A.domain;

import com.esiea.tp4A.domain.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashSet;
import java.util.Set;

public class CommandeIgnoreMarsRoverTest {
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
            "' s;f', 0,0, NORTH, 0, 1, NORTH",
            "'saf f', 0,-1, NORTH, 0, 1, NORTH",
            "'sf', 0,0, SOUTH, 0, -1, SOUTH",
            "'s&w ff', 0,1, SOUTH, 0, -1, SOUTH",
            "'sf', 0,0, EAST, 1, 0, EAST",
            "'sff', -1,0, EAST, 1, 0, EAST",
            "'sf', 1,0, WEST, 0, 0, WEST",
            "'sff', 12, 20, WEST, 10, 20, WEST",


            "'fs;f', 15, 50, NORTH, 15, -49, NORTH ",
            "'s,f', 1, -49, SOUTH, 1, 50, SOUTH",
            "'s?ff', 49, 20, EAST, -49, 20, EAST ",
            "'sf!f', -48, 1, WEST, 50,1,WEST",

        })
    void CommandIgnoreTest(String commande, int x, int y, Direction direction, int x_result, int y_result, Direction direction_result ){

        listobstacle.add(obs1);
        listobstacle.add(obs2);
        listobstacle.add(obs3);
        listobstacle.add(obs4);
        listobstacle.add(obslim1);
        listobstacle.add(obslim2);
        listobstacle.add(obslim3);
        listobstacle.add(obslim4);



        MarsRover marsRover= new MarsRoverImpl(x,y, direction,listobstacle,2,mapTest.getTaille());
        Position position=marsRover.move(commande);
        Assertions.assertThat(position).isEqualTo(Position.of(x_result,y_result,direction_result));

    }
}
