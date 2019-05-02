package com.franciscoreina;

import com.franciscoreina.Coordinate;
import com.franciscoreina.Maze;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class MazeTest {

    private static Maze maze;

    @BeforeAll
    static void setUp() {
        maze = new Maze();
    }

    @Test
    @DisplayName("Initializes the size of the maze")
    void testInitSizeMaze() {
        // Given
        String sizeMaze = "5 5";

        // When
        maze.initSizeMaze(sizeMaze);

        // Then
        assertAll("Verify all conditions for a maze that is initialized its size",
                () -> assertTrue(maze.getWidth() == 5),
                () -> assertTrue(maze.getHeight() == 5)
        );
    }

    @Test
    @DisplayName("Initializes the start coordinate of the maze")
    void testInitStartCoordinate() {
        // Given
        String startCoordinate = "1 1";
        Coordinate expectedCoordinate = new Coordinate(1, 1);

        // When
        maze.initStartCoordinate(startCoordinate);

        // Then
        assertAll("Verify all conditions for a maze that is initialized its start coordinate",
                () -> assertTrue(maze.getStart().getX() == expectedCoordinate.getX()),
                () -> assertTrue(maze.getStart().getY() == expectedCoordinate.getY()),
                () -> assertNull(maze.getStart().getPreviousCoordinate())
        );
    }

    @Test
    @DisplayName("Initializes the end coordinate of the maze")
    void testInitEndCoordinate() {
        // Given
        String endCoordinate = "3 3";
        Coordinate expectedCoordinate = new Coordinate(3, 3);

        // When
        maze.initEndCoordinate(endCoordinate);

        // Then
        assertAll("Verify all conditions for a maze that is initialized its end coordinate",
                () -> assertTrue(maze.getEnd().getX() == expectedCoordinate.getX()),
                () -> assertTrue(maze.getEnd().getY() == expectedCoordinate.getY()),
                () -> assertNull(maze.getEnd().getPreviousCoordinate())
        );
    }

    @Test
    @DisplayName("Filling in the input maze and the skeleton of the output maze")
    void testFilledMazeAndOutputMaze() {
        // Given
        maze.initSizeMaze("5 5");
        List<String> mazeDescription = Arrays.asList(
                "5 5",
                "1 1",
                "3 3",
                "1 1 1 1 1",
                "1 0 1 0 1",
                "1 0 1 0 1",
                "1 0 0 0 1",
                "1 1 1 1 1"
        );
        int[][] expectedMaze = {
                {1, 1, 1, 1, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1}
        };
        String[][] expectedOutputMaze = {
                {"#", "#", "#", "#", "#"},
                {"#", " ", "#", " ", "#"},
                {"#", " ", "#", " ", "#"},
                {"#", " ", " ", " ", "#"},
                {"#", "#", "#", "#", "#"}
        };

        // When
        maze.filledMazeAndOutputMaze(mazeDescription);

        // Then
        assertAll("Verify all conditions for a maze that is initialized its matrices",
                () -> IntStream.range(0, maze.getHeight() - 1)
                        .forEach(index -> assertTrue(Arrays.equals(expectedMaze[index], maze.getMaze()[index]))),
                () -> IntStream.range(0, maze.getHeight() - 1)
                        .forEach(index -> assertTrue(Arrays.equals(expectedOutputMaze[index], maze.getOutputMaze()[index])))
        );
    }

}
