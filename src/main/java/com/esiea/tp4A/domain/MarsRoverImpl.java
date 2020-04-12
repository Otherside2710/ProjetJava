package com.esiea.tp4A.domain;

import java.util.Set;

public class MarsRoverImpl implements MarsRover {
   private Position position;

    public MarsRoverImpl(int x, int y, Direction direction) {
        position = Position.of(x, y, direction);
    }

    public Position Avancer(Direction direction, int taille){
        switch (direction){
            case NORTH: case SOUTH: return  Position.of(position.getX(), deplacement_grille_avancer(position.getY(),position.getDirection(),taille), position.getDirection());
            case EAST: case WEST: return  Position.of(deplacement_grille_avancer(position.getX(),position.getDirection(),taille), position.getY(), position.getDirection());
        } return position;}

    public Position Reculer(Direction direction,int taille){
        switch (direction){
            case NORTH: case SOUTH: return  Position.of(position.getX(), deplacement_grille_reculer(position.getY(),position.getDirection(),taille), position.getDirection());
            case EAST: case WEST: return  Position.of(deplacement_grille_reculer(position.getX(),position.getDirection(),taille), position.getY(), position.getDirection());
        } return position;}

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
        } return position;}

    public int deplacement_grille_avancer(int i, Direction direction, int taille){
        switch (direction){
            case NORTH: case EAST: if(i==taille/2){return -taille/2+1;} else return i+1;
            case SOUTH: case WEST: if(i==-taille/2+1){return taille/2;} else return i-1;
        } return i;}

    public int deplacement_grille_reculer(int i, Direction direction, int taille){
        switch (direction){
            case SOUTH: case WEST: if(i==taille/2){return -taille/2+1;} else return i+1;
            case NORTH: case EAST: if(i==-taille/2+1){return taille/2;} else return i-1;
        } return i;}

    public static Set<Position> laser(int portee, Position rover, Set<Position> listobstacle,int taille) {
        if(listobstacle!= null){ for (Position i : listobstacle) { for (int j = 1; j <= portee; j++) { int j_modif=j;
                switch (rover.getDirection()) {
                    case NORTH: if(rover.getY() + j>taille/2){j_modif = j-taille;}
                                if (rover.getY() + j_modif == i.getY() && rover.getX()==i.getX()) { listobstacle.remove(i); return listobstacle;} break;
                    case SOUTH: if(rover.getY() - j<-taille/2+1){j_modif = j-taille;}
                                if (rover.getY() - j_modif == i.getY() && rover.getX()==i.getX()) {listobstacle.remove(i); return listobstacle;} break;
                    case WEST:  if(rover.getX() - j<-taille/2+1){j_modif = j-taille;}
                                if (rover.getX() - j_modif == i.getX() && rover.getY()==i.getY()) {listobstacle.remove(i);return listobstacle;}break;
                    case EAST:  if(rover.getX() + j>taille/2){j_modif = j-taille;}
                                if (rover.getX() + j_modif == i.getX() && rover.getY()==i.getY()) {listobstacle.remove(i); return listobstacle;}break;}}}
        }return listobstacle;}

    public static int detectobstacle_forward(Position rover, Set<Position> listobstacle, int taille){
        if(listobstacle!= null){
        for (Position i : listobstacle) {
            switch(rover.getDirection()) {
                case NORTH: if (((rover.getY() == taille/2 && i.getY() == -taille/2+1) || (rover.getY() + 1 == i.getY())) && rover.getX() == i.getX()) { return 1; } break;
                case SOUTH: if (((rover.getY() == -taille/2+1 && i.getY() == taille/2) || (rover.getY() - 1 == i.getY())) && rover.getX() == i.getX()) { return 1; } break;
                case WEST: if (((rover.getX() == -taille/2+1 && i.getX() == taille/2) || (rover.getX() - 1 == i.getX())) && rover.getY() == i.getY()) { return 1; } break;
                case EAST: if (((rover.getX() == taille/2 && i.getX() == -taille/2+1) || (rover.getX() + 1 == i.getX())) && rover.getY() == i.getY()) { return 1; } break;}}}
        return 0;}

    public static int detectobstacle_backward(Position rover, Set<Position> listobstacle,int taille) {
        if(listobstacle!= null){
        for (Position y : listobstacle) {
            switch (rover.getDirection()) {
                case NORTH: if (((rover.getY() == -taille/2+1 && y.getY() == taille/2) || (rover.getY() - 1 == y.getY())) && rover.getX() == y.getX()) {return 1;} break;
                case SOUTH: if (((rover.getY() == taille/2 && y.getY() == -taille/2+1) || (rover.getY() + 1 == y.getY())) && rover.getX() == y.getX()) {return 1;} break;
                case WEST: if (((rover.getX() == taille/2 && y.getX() == -taille/2+1) || (rover.getX() + 1 == y.getX())) && rover.getY() == y.getY()) {return 1;}  break;
                case EAST: if (((rover.getX() == -taille/2+1 && y.getX() == taille/2) || (rover.getX() - 1 == y.getX())) && rover.getY() == y.getY()) {return 1;}  break;}}}
        return 0;}

    @Override
    public Position move(String command, int taille_map, Set<Position> listobstacle, Integer portee) {
        for ( int i =0; i<command.length(); i++ ) {
            char singlecommand = command.charAt(i);
            switch (singlecommand){
                case 'f': if (detectobstacle_forward(position,listobstacle,taille_map)==0){position=Avancer(position.getDirection(), taille_map);} break;
                case 'b': if (detectobstacle_backward(position,listobstacle,taille_map)==0){position=Reculer(position.getDirection(), taille_map);}break;
                case 'l': position=Rotation_gauche(position.getDirection()); break;
                case 'r': position=Rotation_droite(position.getDirection()); break;
                case 's': Set<Position> new_listobstacle = laser(portee,position,listobstacle,taille_map);
                          listobstacle = new_listobstacle;break;
                default:
            }}   return position; }
}


