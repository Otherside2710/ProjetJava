package com.esiea.tp4A.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;

public class Obstacletest {
    Set<Position> obstaclePositions = new HashSet<>();
    Position position = Position.of(0,0,Direction.NORTH);
    Position position2 = Position.of(0,0,Direction.EAST);
    Position position3 = Position.of(0,50,Direction.NORTH);
    Position position4 = Position.of(0,-49,Direction.NORTH);
    Position position5 = Position.of(2,0,Direction.NORTH);
    Position positionobs = Position.of(0, 1, Direction.NORTH);
    Position positionobs2 = Position.of(1, 0, Direction.EAST);
    Position positionobs3 = Position.of(0, -49, Direction.NORTH);
    Position positionobs4 = Position.of(1, 1, Direction.NORTH);
    Position positionobs5 = Position.of(0, 50, Direction.NORTH);
    Position positionobs6 = Position.of(1, 1, Direction.SOUTH);
    Position positionobs7 = Position.of(3, -1, Direction.NORTH);

    @Test
    void forwobsTest() {
        obstaclePositions.add(positionobs);
        obstaclePositions.add(positionobs2);
        obstaclePositions.add(positionobs3);
        obstaclePositions.add(positionobs4);
        obstaclePositions.add(positionobs5);
        obstaclePositions.add(positionobs6);
        obstaclePositions.add(positionobs7);
        int result = MarsRoverImpl.detectobstacle_forward(position, obstaclePositions);
        Assertions.assertThat(result).isEqualTo(1);

        int result2 = MarsRoverImpl.detectobstacle_forward(position2, obstaclePositions);
        Assertions.assertThat(result2).isEqualTo(1);

        int result3 = MarsRoverImpl.detectobstacle_forward(position3, obstaclePositions);
        Assertions.assertThat(result3).isEqualTo(1);

        int result4 = MarsRoverImpl.detectobstacle_forward(position5, obstaclePositions);
        Assertions.assertThat(result4).isEqualTo(0);
    }

    @Test
    void backobsTest() {
        obstaclePositions.add(positionobs);
        obstaclePositions.add(positionobs2);
        obstaclePositions.add(positionobs3);
        obstaclePositions.add(positionobs4);
        obstaclePositions.add(positionobs5);
        obstaclePositions.add(positionobs6);
        obstaclePositions.add(positionobs7);
        int result = MarsRoverImpl.detectobstacle_backward(position, obstaclePositions);
        Assertions.assertThat(result).isEqualTo(0);

        int result2 = MarsRoverImpl.detectobstacle_backward(position2, obstaclePositions);
        Assertions.assertThat(result2).isEqualTo(0);

        int result3 = MarsRoverImpl.detectobstacle_backward(position4, obstaclePositions);
        Assertions.assertThat(result3).isEqualTo(1);

        int result4 = MarsRoverImpl.detectobstacle_forward(position5, obstaclePositions);
        Assertions.assertThat(result4).isEqualTo(0);
    }
}
