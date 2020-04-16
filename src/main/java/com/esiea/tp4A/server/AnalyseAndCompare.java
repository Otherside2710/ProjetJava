package com.esiea.tp4A.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class AnalyseAndCompare {
    public static void ressource (PrintWriter writer, BufferedReader reader, RoverInterface roverInterface) throws IOException {
        String line = reader.readLine();
        if(line == null) return;
        String[] req = line.split(" ");
        analyse(writer, roverInterface, req[0],req[1].split("/"));
    }
    private static void analyse(PrintWriter printWriter, RoverInterface roverInterface, String type,String[] arg) {
        if (type.equals("POST") && arg.length == 4 && arg[1].equals("api") && arg[2].equals("player")) post(printWriter, roverInterface, arg[3]);
        if (type.equals("GET") && arg.length == 4 && arg[1].equals("api") && arg[2].equals("player")) get(printWriter, roverInterface, arg[3]);
        if (type.equals("PATCH") && arg.length == 5 && arg[1].equals("api") && arg[2].equals("player")) patch(printWriter, roverInterface, arg[3], arg[4]);
    }
    private static void post(PrintWriter printWriter, RoverInterface roverInterface, String player) {
        if (roverInterface.player(player)) JSON.notworkingrequest("Code 409");
        else { roverInterface.setRover(player); JSON.workingrequest(printWriter,player, roverInterface); }
    }
    private static void get(PrintWriter printWriter, RoverInterface roverInterface, String player) {
        if (!roverInterface.player(player)) JSON.notworkingrequest("Code 404");
        else { JSON.workingrequest(printWriter,player, roverInterface); }
    }
    private static void patch(PrintWriter printWriter, RoverInterface roverInterface, String player,String commande) {
        if (!roverInterface.player(player)) JSON.notworkingrequest("Code 404");
        else { roverInterface.RoverPos(player).move(commande); JSON.workingrequest(printWriter,player,roverInterface); }
    }
}
