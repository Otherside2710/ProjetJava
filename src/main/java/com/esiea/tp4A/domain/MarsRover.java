package com.esiea.tp4A.domain;

import java.util.List;
import java.util.Set;

public interface MarsRover {

    default MarsRover initialize(Position position) {
        return this;
    }



    default MarsRover updateMap(PlanetMap map) {
        return this;
    }

    default MarsRover configureLaserRange(int range) {
        return this;
    }



    default Position move(String command, int taille_map, Set<Position> listobstacle, Integer portee) {
        return Position.of(0, 0, Direction.NORTH);
    }
}
