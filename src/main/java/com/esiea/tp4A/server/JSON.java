package com.esiea.tp4A.server;

import com.esiea.tp4A.domain.MarsRoverImpl;
import com.esiea.tp4A.domain.PlanetMap;
import com.esiea.tp4A.domain.Position;
import com.esiea.tp4A.domain.MarsRover;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class JSON {
    private static String listplayer(RoverInterface roverServer) {
        HashMap<String, MarsRoverImpl> rovers = roverServer.ListRoverPos();
        return listplayer(rovers, 0, rovers.size()-1);
    }
    private static String listplayer(HashMap<String, MarsRoverImpl> rovers, int increment, int size) {
        String message = "";
        for (Map.Entry<String, MarsRoverImpl> entry : rovers.entrySet()) {
            Position position = entry.getValue().move("");
            message += "{\"name\":\"" + entry.getKey() + "\",\"x\":" + position.getX() + ",\"y\":" + position.getY() + "}";
            if(increment != size) message += ",";
        }
        return "[" + message + "]";
    }
    private static String position(MarsRoverImpl rover) {
        System.out.println(rover.move("").getX());
        Position position = rover.move("");
        return position(position.getX(),position.getY(),position.getDirection().name());
    }
    private static String position(int x, int y, String direction) {
        return "{\"x\":"+x+",\"y\":"+y+",\"direction\":\""+direction+"\"}";
    }
    private static String listobstacle(RoverInterface roversServer) {
        PlanetMap map = roversServer.obstaclePositions();
        int max = map.obstaclePositions().size() - 1, i = 0;
        String message = "";
        for (Position each : map.obstaclePositions()) {
            message += position(each.getX(),each.getY(),each.getDirection().name());
            if(max != i) message += ",";
        }
        return "[" + message + "]";
    }
    public static void notworkingrequest (String code) {
        String message = "HTTP/1.1 " + code + "\n\n";
    }
    public static void workingrequest (PrintWriter writer, String player, RoverInterface roverInterface) {
        MarsRoverImpl rover = roverInterface.RoverPos(player);
        String message = "HTTP/1.1 200 OK\nContent-Type: application/json\n\n";
        message += "{\"player\":{\"name\":\"" + player + "\",\"status\":\"" + roverInterface.Status(player) +  "\",\"position\":" + position(rover) + ",\"laser-range\":" + roverInterface.LaserRange(player) + "},\"local-map\":{\"obstacle\":" + listobstacle(roverInterface) +",\"players\":" + listplayer(roverInterface) + "}}";;
        send(writer, message);
    }
    private static void send(PrintWriter writer, String message) {
        writer.print(message);
        writer.flush();
    }
}
