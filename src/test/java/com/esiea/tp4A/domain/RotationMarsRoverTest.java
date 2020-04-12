package com.esiea.tp4A.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RotationMarsRoverTest {
    PlanetMapImpl mapTest= new PlanetMapImpl(100);

    @ParameterizedTest
    @CsvSource
        ({
            "'r', 0,0, NORTH, 0, 0, EAST",
            "'r', 0,0, SOUTH, 0, 0, WEST",
            "'r', 0,0, EAST, 0, 0, SOUTH",
            "'r', 0,0, WEST, 0, 0, NORTH",

            "'r', 12, 30, NORTH, 12, 30, EAST",
            "'r', 12, 30, SOUTH, 12, 30, WEST",
            "'r', 12,30, EAST, 12, 30, SOUTH",
            "'r', 12,30, WEST, 12, 30, NORTH",
        })
    void RotationDroiteTest(String commande, int x, int y, Direction direction, int x_result, int y_result, Direction direction_result ){
        MarsRover marsRover= new MarsRoverImpl(x,y, direction);
        Position position=marsRover.move(commande,mapTest.getTaille(),null,null);
        Assertions.assertThat(position).isEqualTo(Position.of(x_result,y_result,direction_result));

    }

    @ParameterizedTest
    @CsvSource
        ({
            "'l', 0,0, NORTH, 0, 0, WEST",
            "'l', 0,0, SOUTH, 0, 0, EAST",
            "'l', 0,0, EAST, 0, 0, NORTH",
            "'l', 0,0, WEST, 0, 0, SOUTH",

            "'l', 12, 30, NORTH, 12, 30, WEST",
            "'l', 12, 30, SOUTH, 12, 30, EAST",
            "'l', 12,30, EAST, 12, 30, NORTH",
            "'l', 12,30, WEST, 12, 30, SOUTH",
        })
    void RotationGaucheTest(String commande, int x, int y, Direction direction, int x_result, int y_result, Direction direction_result ){
        MarsRover marsRover= new MarsRoverImpl(x,y, direction);
        Position position=marsRover.move(commande,mapTest.getTaille(),null,null);
        Assertions.assertThat(position).isEqualTo(Position.of(x_result,y_result,direction_result));

    }
}
