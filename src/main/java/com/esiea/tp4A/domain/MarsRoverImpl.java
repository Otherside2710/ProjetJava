package com.esiea.tp4A.domain;

import java.util.Set;

public class MarsRoverImpl implements MarsRover {
    private final Position position;

    private final int taille;
    private final Integer portee;
    private final Set<Position> listobstacle;

    public MarsRoverImpl(int x, int y, Direction direction, Set<Position> listobstacle, Integer portee, int taille) {
        position = Position.of(x, y, direction);
        this.listobstacle=listobstacle;
        this.portee=portee;
        this.taille=taille;
    }


    public Position Avancer(Position rover, int taille){
        switch (rover.getDirection()){
            case NORTH: case SOUTH: return  Position.of(rover.getX(), deplacement_grille_avancer(rover.getY(),rover.getDirection(),taille), rover.getDirection());
            case EAST: case WEST: return  Position.of(deplacement_grille_avancer(rover.getX(),rover.getDirection(),taille), rover.getY(), rover.getDirection());
        } return rover;}

    public Position Reculer(Position rover,int taille){
        switch (rover.getDirection()){
            case NORTH: case SOUTH: return  Position.of(rover.getX(), deplacement_grille_reculer(rover.getY(),rover.getDirection(),taille), rover.getDirection());
            case EAST: case WEST: return  Position.of(deplacement_grille_reculer(rover.getX(),rover.getDirection(),taille), rover.getY(), rover.getDirection());
        } return rover;}

    public Position Rotation_gauche(Position rover){
        switch (rover.getDirection()){
            case NORTH: return  Position.of(rover.getX(), rover.getY(), Direction.WEST);
            case SOUTH: return  Position.of(rover.getX(), rover.getY(), Direction.EAST);
            case EAST:  return  Position.of(rover.getX(), rover.getY(), Direction.NORTH);
            case WEST: return   Position.of(rover.getX(), rover.getY(), Direction.SOUTH);
        } return rover;
    }

    public Position Rotation_droite(Position rover){
        switch (rover.getDirection()){
            case NORTH: return  Position.of(rover.getX(), rover.getY(), Direction.EAST);
            case SOUTH: return  Position.of(rover.getX(), rover.getY(), Direction.WEST);
            case EAST:  return  Position.of(rover.getX(), rover.getY(), Direction.SOUTH);
            case WEST: return   Position.of(rover.getX(), rover.getY(), Direction.NORTH);
        } return rover;}

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
    public Position move(String command) {
        Position new_position=this.position;
        Set<Position> new_listobstacle=listobstacle;
        for ( int i =0; i<command.length(); i++ ) {
            char singlecommand = command.charAt(i);
            switch (singlecommand){
                case 'f': if (detectobstacle_forward(new_position,new_listobstacle,taille)==0){new_position=Avancer(new_position, taille);} break;
                case 'b': if (detectobstacle_backward(new_position,new_listobstacle,taille)==0){new_position=Reculer(new_position, taille);}break;
                case 'l': new_position=Rotation_gauche(new_position); break;
                case 'r': new_position=Rotation_droite(new_position); break;
                case 's':  new_listobstacle = laser(portee,new_position,new_listobstacle,taille);break;
                default: break;
            }      }   return new_position; }
}


