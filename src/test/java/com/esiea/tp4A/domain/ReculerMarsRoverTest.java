package com.esiea.tp4A.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ReculerMarsRoverTest {
    PlanetMapImpl mapTest= new PlanetMapImpl(100);

    @ParameterizedTest
    @CsvSource
        ({
            "'b', 0,0, NORTH, 0, -1, NORTH",
            "'b', 0,0, SOUTH, 0, 1, SOUTH",
            "'b', 0,0, EAST, -1, 0, EAST",
            "'b', 0,0, WEST, 1, 0, WEST",

            "'b', 12, 30, NORTH, 12, 29, NORTH",
            "'b', 12, 30, SOUTH, 12, 31, SOUTH",
            "'b', 12,30, EAST, 11, 30, EAST",
            "'b', 12,30, WEST, 13, 30, WEST",
        })
    void ReculerTest(String commande, int x, int y, Direction direction, int x_result, int y_result, Direction direction_result ){
        MarsRover marsRover= new MarsRoverImpl(x,y, direction,null,null,mapTest.getTaille());
        Position position=marsRover.move(commande);
        Assertions.assertThat(position).isEqualTo(Position.of(x_result,y_result,direction_result));

    }
}
