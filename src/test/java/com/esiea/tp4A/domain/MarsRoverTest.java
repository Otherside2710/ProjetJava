package com.esiea.tp4A.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MarsRoverTest {

    @Test
    void forwardTest(){
        MarsRover marsRover= new MarsRoverImpl(0,0, Direction.NORTH);
        Position position=marsRover.move("f");
        Assertions.assertThat(position).isEqualTo(Position.of(0,1,Direction.NORTH));

    }
}
