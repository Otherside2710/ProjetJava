package com.esiea.tp4A.ServerHTTP;

import com.esiea.tp4A.domain.Direction;
import com.esiea.tp4A.domain.MarsRoverImpl;
import com.esiea.tp4A.domain.PlanetMapImpl;
import com.esiea.tp4A.domain.Position;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static java.lang.StrictMath.random;


@SpringBootApplication
@RestController
public class Server extends ResponseAPI {
    private final List<Integer> listTaille = Arrays.asList(100,300,600);
    private final Random random=new Random();
    private final int taille=listTaille.get(random.nextInt(2));
    private final Map<String, MarsRoverImpl> ListPlayer = new HashMap<>();
    private final PlanetMapImpl Map = new PlanetMapImpl(taille);
    private  Set<Position> listObstacle= Map.obstaclePositions();
    private final List<Direction> listDirection = Arrays.asList(Direction.NORTH,Direction.SOUTH,Direction.EAST,Direction.WEST);

    public static void main(String[] args) {

        SpringApplication.run(Server.class, args);
    }

    @PostMapping("/api/player/{playerName}")
    public ResponseEntity<?> generatePlayer(@PathVariable("playerName") String name) {
        System.out.println(taille);
        if (ListPlayer.containsKey(name)){
            return ResponseEntity.status(409).body("Ce nom de joueur existe déjà");}
        int x = (int) (random()*taille)-taille/2+1;
        int y = (int) (random()*taille)-taille/2+1;
        Direction direction = listDirection.get(random.nextInt(3));
        int portee=random.nextInt(1000);
        MarsRoverImpl Rover = new MarsRoverImpl(x,y,direction,listObstacle,portee,taille);
        ListPlayer.put(name, Rover);
        Map<String, Object> response = JSONResponse(Rover,name, ListPlayer);
        return ResponseEntity.status(201).body(response);

    }


    @GetMapping("/api/player/{playerName}")
    public ResponseEntity<?> playerStatus(@PathVariable("playerName") String name) {
        if (!ListPlayer.containsKey(name)){
            return ResponseEntity.status(404).body("Ce nom de joueur n'existe pas");}

        MarsRoverImpl Rover = ListPlayer.get(name);

        Map<String, Object> response = JSONResponse(Rover, name, ListPlayer);
        return ResponseEntity.status(200).body(response);
    }


    @PatchMapping("/api/player/{name}/{command}")
    public ResponseEntity<?> executeCommand(@PathVariable("name") String name, @PathVariable("command") String command) {
        if (!ListPlayer.containsKey(name)){
            return ResponseEntity.status(404).body("Ce nom de joueur n'existe pas");}
        MarsRoverImpl Rover = ListPlayer.get(name);
        Position position=Rover.move(command);
        MarsRoverImpl Rovermodif = new MarsRoverImpl(position.getX(),position.getY(),position.getDirection(),Rover.getListobstacle(),Rover.getPortee(),Map.getTaille());
        listObstacle=Rovermodif.getListobstacle();
        ListPlayer.remove(name);
        ListPlayer.put(name,Rovermodif);
        Map<String, Object> response = JSONResponse(Rovermodif, name,ListPlayer);

        return ResponseEntity.status(200).body(response);
    }


}


