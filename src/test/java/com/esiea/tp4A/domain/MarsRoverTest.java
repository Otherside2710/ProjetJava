package com.esiea.tp4A.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class MarsRoverTest {
    PlanetMapImpl mapTest= new PlanetMapImpl(100);


    @ParameterizedTest
    @CsvSource
        ({
            "'f', 0,0, NORTH, 0, 1, NORTH",
            "'f', 0,0, SOUTH, 0, -1, SOUTH",
            "'f', 0,0, EAST, 1, 0, EAST",
            "'f', 0,0, WEST, -1, 0, WEST",

            "'f', 12, 30, NORTH, 12, 31, NORTH",
            "'f', 12, 30, SOUTH, 12, 29, SOUTH",
            "'f', 12,30, EAST, 13, 30, EAST",
            "'f', 12,30, WEST, 11, 30, WEST",

        })
    void AvancerTest(String commande, int x, int y, Direction direction, int x_result, int y_result, Direction direction_result ){
        MarsRover marsRover= new MarsRoverImpl(x,y, direction,null,null,mapTest.getTaille());
        Position position=marsRover.move(commande);
        Assertions.assertThat(position).isEqualTo(Position.of(x_result,y_result,direction_result));

    }

}
