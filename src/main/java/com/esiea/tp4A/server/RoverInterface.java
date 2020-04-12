package com.esiea.tp4A.server;

import com.esiea.tp4A.domain.MarsRoverImpl;
import com.esiea.tp4A.domain.PlanetMap;
import com.esiea.tp4A.domain.PlanetMapImpl;

import java.util.HashMap;

public interface RoverInterface {
    MarsRoverImpl RoverPos(String player);
    void setRover(String player);
    HashMap<String,MarsRoverImpl> ListRoverPos();
    String Status(String player);
    int LaserRange(String player);
    PlanetMapImpl obstaclePositions();
    boolean player(String player);
}
