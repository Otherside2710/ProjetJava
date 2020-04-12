package com.esiea.tp4A.server;

import com.esiea.tp4A.domain.MarsRoverImpl;
import com.esiea.tp4A.domain.PlanetMap;
import com.esiea.tp4A.domain.PlanetMapImpl;

import java.io.IOException;
import java.util.HashMap;

public class ServerHTTP {
    public static void main (String []args) throws IOException {
        Server client = new Server(8080);
        System.out.println("Listening for connection on port 8080 ....");
        client.setRoverInterface(roverInterface);
        client.start();
    }
    private static RoverInterface roverInterface = new RoverInterface() {
        public MarsRoverImpl RoverPos(String player) {
            return null;
        }
        public void setRover(String player) {}
        public HashMap<String, MarsRoverImpl> ListRoverPos() {
            return null;
        }
        public String Status(String player) {
            return null;
        }
        public int LaserRange(String player) {
            return 0;
        }
        public PlanetMapImpl obstaclePositions() {
            return null;
        }
        public boolean player(String player) {
            return false;
        }
    };
}
