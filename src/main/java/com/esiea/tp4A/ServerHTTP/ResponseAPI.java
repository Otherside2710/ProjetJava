package com.esiea.tp4A.ServerHTTP;

import com.esiea.tp4A.domain.MarsRoverImpl;
import com.esiea.tp4A.domain.Position;

import java.util.*;

public class ResponseAPI {
    public Map<String,Object> JSONResponse(MarsRoverImpl rover, String name, Map<String,MarsRoverImpl> listPlayer){
        Map<String,Object> retour= new HashMap<>();
        Map<String,Object> player= new HashMap<>();
        Map<String,Object> position=new HashMap<>();

        player.put("name", name);
        player.put("status", "alive");

        //Creation objet position associé au rover
        position.put("x", rover.getPosition().getX());
        position.put("y", rover.getPosition().getY());
        position.put("direction", rover.getPosition().getDirection().toString());
        player.put("position", position);

        player.put("laser-range", rover.getPortee());

        //Creation des éléments de la map
        Map<String,Object> local_map= new HashMap<>();
        List<Map<String, Integer>> listObstacle = new ArrayList<>();
        for(Position i : rover.getListobstacle()){
            Map<String,Integer> obstacle = new HashMap<>();
            obstacle.put("x", i.getX());
            obstacle.put("y", i.getY());
            listObstacle.add(obstacle);
        }
        local_map.put("obstacles", listObstacle);

        List<Map<String,Object>> newPlayerList = new ArrayList<>();
        for(Map.Entry<String, MarsRoverImpl> i:listPlayer.entrySet()) {
            if(i.getKey()!= name) {
                Map<String, Object> Otherplayer = new HashMap<>();
                Otherplayer.put("name", i.getKey());
                Otherplayer.put("x", i.getValue().getPosition().getX());
                Otherplayer.put("y", i.getValue().getPosition().getY());
                newPlayerList.add(Otherplayer);
            }

        }
        local_map.put("players", newPlayerList);


        //On ajoute tous les éléments à retour
        retour.put("local-map", local_map);
        retour.put("player", player);

        return retour;



    }
}
