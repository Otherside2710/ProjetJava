package com.esiea.tp4A.domain;

import java.util.HashSet;
import java.util.Set;

import static java.lang.StrictMath.random;
import static java.lang.StrictMath.round;

public class PlanetMapImpl implements PlanetMap {
    int frontN = 50;
    int frontS = -49;
    int frontE = 50;
    int frontW = -49;
    int taille = 100;

    @Override
    public Set<Position> obstaclePositions() {
        Set<Position> hashSet = new HashSet<>();
        for (int i = 0; i<round(0.15*100*100); i++) {
            int x = (int) (random()*taille)-49;
            int y = (int) (random()*taille)-49;
            if(hashSet.contains(Position.of(x,y,null)) == false) {
                hashSet.add(Position.of(x,y,null));
            }
        }
        return hashSet;
    }
}
