package com.esiea.tp4A.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashSet;
import java.util.Set;

public class CommandeComplexeMarsRoverTest {
    PlanetMapImpl mapTest= new PlanetMapImpl(100);
    Set<Position> listobstacle = new HashSet<>();

    Position obs1 = Position.of(1,0,null);
    Position obs2 = Position.of(0,1,null);
    Position obs3 = Position.of(10,20,null);
    Position obs4 = Position.of(15,1,null);
    Position obs5 = Position.of(1,1,null);

    Position obslim1 = Position.of(50,1,null);
    Position obslim2 = Position.of(1,50,null);
    Position obslim3 = Position.of(-49,20,null);
    Position obslim4 = Position.of(15,-49,null);

    @ParameterizedTest
    @CsvSource
        ({
            "'sfrb', 0,0, NORTH, -1, 1, EAST",
            "'sfflllf', 0,-1, NORTH, 0, 1, EAST",
            "'sfflllsf', 0,-1, NORTH, 1, 1, EAST",
            "'lbblffr', 5, -3, WEST, 7, -1, SOUTH"

        })
    void CmdComplexeTest(String commande, int x, int y, Direction direction, int x_result, int y_result, Direction direction_result ){

        listobstacle.add(obs1); listobstacle.add(obs2); listobstacle.add(obs3); listobstacle.add(obs4);  listobstacle.add(obs5);
        listobstacle.add(obslim1); listobstacle.add(obslim2); listobstacle.add(obslim3); listobstacle.add(obslim4);
        MarsRover marsRover= new MarsRoverImpl(x,y, direction);
        Position position=marsRover.move(commande,mapTest.getTaille(),listobstacle,2);
        Assertions.assertThat(position).isEqualTo(Position.of(x_result,y_result,direction_result));

    }
}
