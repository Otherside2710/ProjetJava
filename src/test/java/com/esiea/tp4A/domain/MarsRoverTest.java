package com.esiea.tp4A.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class MarsRoverTest {

    Set<Position> listobstacle = new HashSet<>();
    Set<Position> listobstacle2 = new HashSet<>();
    @ParameterizedTest
    @CsvSource
        ({
        "'fflb', 0, 0, NORTH, 1, 2, WEST",
            "'f', 0, 50, NORTH, 0, -49, NORTH",
            "'ff', 0, 50, NORTH, 0, -48, NORTH",
            "'b', 0, -49, NORTH, 0, 50, NORTH",

            "'f', 0, -49, SOUTH, 0, 50, SOUTH",
            "'b', 0, -49, SOUTH, 0, -48, SOUTH",

            "'f', 50, 0, EAST, -49, 0, EAST",
            "'b', -49, 0, EAST, 50, 0, EAST",
            "'b', -48, 0, EAST, -49, 0, EAST",


            "'f', -49, 0, WEST, 50, 0, WEST",
            "'b', 50, 0, WEST, -49, 0, WEST",
            "'f', 48, 0, WEST, 47, 0, WEST",

      /*  "'b', 0, 0,NORTH, 0, -1, NORTH",
        "'l', 0, 0,NORTH, 0, 0, WEST",
        "'r', 0, 0, NORTH, 0, 0, EAST",

        "'f', 0, 0, SOUTH, 0, -1, SOUTH",
        "'b', 0, 0,SOUTH, 0, 1, SOUTH",
        "'l', 0, 0,SOUTH, 0, 0, EAST",
        "'r', 0, 0, SOUTH, 0, 0, WEST",

        "'f', 0, 0, EAST, 1, 0, EAST",
        "'b', 0, 0,EAST, -1, 0, EAST",
        "'l', 0, 0,EAST, 0, 0, NORTH",
        "'r', 0, 0, EAST, 0, 0, SOUTH",

        "'f', 0, 0, WEST, -1, 0, WEST",
        "'b', 0, 0, WEST, 1, 0, WEST",
        "'l', 0, 0, WEST, 0, 0, SOUTH",
        "'r', 0, 0, WEST, 0, 0, NORTH",
*/
        })
    void MoveTest(String commande, int x, int y, Direction direction, int x_result, int y_result, Direction direction_result ){
        MarsRover marsRover= new MarsRoverImpl(x,y, direction);
        Position position=marsRover.move(commande);
        Assertions.assertThat(position).isEqualTo(Position.of(x_result,y_result,direction_result));

    }

    @Test
    void LaserTest(){
        Position position = Position.of(0,0,Direction.SOUTH);
        listobstacle.add(Position.of(0,-3,Direction.NORTH));
        listobstacle.add(Position.of(1,0,Direction.NORTH));

        listobstacle2.add(Position.of(1,0,Direction.NORTH));

        Set<Position> result =MarsRoverImpl.laser(1,position,listobstacle);

        Assertions.assertThat(result).isEqualTo(listobstacle);

    }
}
