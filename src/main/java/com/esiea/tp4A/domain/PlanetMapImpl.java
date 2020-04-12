package com.esiea.tp4A.domain;

import java.util.HashSet;
import java.util.Set;

import static java.lang.StrictMath.random;
import static java.lang.StrictMath.round;

public class PlanetMapImpl implements PlanetMap {
     private int taille;

    public PlanetMapImpl(int i) {
        taille = i;
    }

    public  int getTaille() {
        return taille;
    }

    @Override
    public Set<Position> obstaclePositions() {
        Set<Position> hashSet = new HashSet<>();
        for (int i = 0; i<round(0.15*taille*taille); i++) {
            int x = (int) (random()*taille)-taille/2+1;
            int y = (int) (random()*taille)-taille/2+1;
            if(!hashSet.contains(Position.of(x, y, null))) {
                hashSet.add(Position.of(x,y,null));
            }
        }
        return hashSet;
    }
}
