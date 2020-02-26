package com.esiea.tp4A.domain;

public class MarsRoverImpl implements MarsRover {
    Position position;


    public MarsRoverImpl(int x, int y, Direction direction) {
        position = Position.of(x, y, direction);
    }

    @Override
    public Position move(String command) {


        return Position.of(0, 0, Direction.NORTH);
    }
}

