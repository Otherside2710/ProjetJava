package com.esiea.tp4A.domain;

import java.util.Set;

public class MarsRoverImpl implements MarsRover {
    Position position;

    public MarsRoverImpl(int x, int y, Direction direction) {
        position = Position.of(x, y, direction);
    }

    public Position Avancer(Direction direction){
        switch (direction){
            case NORTH: case SOUTH:
                return  Position.of(position.getX(), deplacement_grille_avancer(position.getY(),position.getDirection()), position.getDirection());
            case EAST: case WEST:
                return  Position.of(deplacement_grille_avancer(position.getX(),position.getDirection()), position.getY(), position.getDirection());
        } return position;
    }

    public Position Reculer(Direction direction){
        switch (direction){
            case NORTH: case SOUTH:
                return  Position.of(position.getX(), deplacement_grille_reculer(position.getY(),position.getDirection()), position.getDirection());
            case EAST: case WEST:
                return  Position.of(deplacement_grille_reculer(position.getX(),position.getDirection()), position.getY(), position.getDirection());
        } return position;
    }

    public Position Rotation_gauche(Direction direction){
        switch (direction){
            case NORTH: return  Position.of(position.getX(), position.getY(), Direction.WEST);
            case SOUTH: return  Position.of(position.getX(), position.getY(), Direction.EAST);
            case EAST:  return  Position.of(position.getX(), position.getY(), Direction.NORTH);
            case WEST: return   Position.of(position.getX(), position.getY(), Direction.SOUTH);
        } return position;
    }

    public Position Rotation_droite(Direction direction){
        switch (direction){
            case NORTH: return  Position.of(position.getX(), position.getY(), Direction.EAST);
            case SOUTH: return  Position.of(position.getX(), position.getY(), Direction.WEST);
            case EAST:  return  Position.of(position.getX(), position.getY(), Direction.SOUTH);
            case WEST: return   Position.of(position.getX(), position.getY(), Direction.NORTH);
        } return position;
    }

    public int deplacement_grille_avancer(int i, Direction direction){
        switch (direction){
            case NORTH: case EAST: if(i==50){return -49;} else return i+1;
            case SOUTH: case WEST: if(i==-49){return 50;} else return i-1;
        } return i;
    }

    public int deplacement_grille_reculer(int i, Direction direction){
        switch (direction){
            case SOUTH: case WEST: if(i==50){return -49;} else return i+1;
            case NORTH: case EAST: if(i==-49){return 50;} else return i-1;
        } return i;
    }

    public static int detectobstacle_forward(Position rover, Set<Position> listobstacle){
        for (Position i : listobstacle) {
            switch(rover.getDirection()) {
                case NORTH: if (((rover.getY() == 50 && i.getY() == -49) || (rover.getY() + 1 == i.getY())) && rover.getX() == i.getX()) { return 1; } break;
                case SOUTH: if (((rover.getY() == -49 && i.getY() == 50) || (rover.getY() - 1 == i.getY())) && rover.getX() == i.getX()) { return 1; } break;
                case WEST: if (((rover.getX() == -49 && i.getX() == 50) || (rover.getX() - 1 == i.getX())) && rover.getY() == i.getY()) { return 1; } break;
                case EAST: if (((rover.getX() == 50 && i.getX() == -49) || (rover.getX() + 1 == i.getX())) && rover.getY() == i.getY()) { return 1; } break;
            }
        }
        return 0;
    }

    @Override
    public Position move(String command) {
        for ( int i =0; i<command.length(); i++ ) {
            char singlecommand = command.charAt(i);
            switch (singlecommand){
                case 'f': position=Avancer(position.getDirection()); break;
                case 'b': position=Reculer(position.getDirection()); break;
                case 'l': position=Rotation_gauche(position.getDirection()); break;
                case 'r': position=Rotation_droite(position.getDirection()); break;
            }}   return position;
    }
}


