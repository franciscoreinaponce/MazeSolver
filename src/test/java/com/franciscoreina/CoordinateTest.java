package com.franciscoreina;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class CoordinateTest {

    private static Maze maze;
    private static int sizeMaze = 5;
    private static Coordinate coordinate;
    private static List<Coordinate> expectedCoordinates;

    @BeforeAll
    static void setUp() {
        maze = new Maze();
        maze.initSizeMaze(sizeMaze + " " + sizeMaze);
    }

    @Test
    @DisplayName("Get contiguous coordinates given another")
    void testGetContiguousCoordinates() {
        // Given
        coordinate = new Coordinate(1, 1);
        expectedCoordinates = Arrays.asList(
                new Coordinate(1, 0, coordinate),
                new Coordinate(2, 1, coordinate),
                new Coordinate(1, 2, coordinate),
                new Coordinate(0, 1, coordinate)
        );

        // When
        List<Coordinate> contiguousCoordinates = Coordinate.getContiguousCoordinates(coordinate);

        // Then
        assertAll("Verify all conditions for a coordinate that is not on the edges of grid",
                () -> assertTrue(!contiguousCoordinates.isEmpty()),
                () -> assertTrue(contiguousCoordinates.size() == expectedCoordinates.size()),
                () -> assertEquals(contiguousCoordinates.stream().map(Coordinate::getX).collect(Collectors.toList()),
                        expectedCoordinates.stream().map(Coordinate::getX).collect(Collectors.toList())),
                () -> assertEquals(contiguousCoordinates.stream().map(Coordinate::getY).collect(Collectors.toList()),
                        expectedCoordinates.stream().map(Coordinate::getY).collect(Collectors.toList())),
                () -> assertEquals(contiguousCoordinates.stream().map(Coordinate::getPreviousCoordinate).collect(Collectors.toList()),
                        expectedCoordinates.stream().map(Coordinate::getPreviousCoordinate).collect(Collectors.toList()))
        );
    }

    @Test
    @DisplayName("Get contiguous coordinates given another and a maze")
    void testGetContiguousCoordinatesWithMaze() {
        // Given
        coordinate = new Coordinate(0, 1);
        expectedCoordinates = Arrays.asList(
                new Coordinate(0, 0, coordinate),
                new Coordinate(1, 1, coordinate),
                new Coordinate(0, 2, coordinate),
                new Coordinate(sizeMaze - 1, 1, coordinate)
        );

        // When
        List<Coordinate> contiguousCoordinates = Coordinate.getContiguousCoordinates(coordinate, maze);

        // Then
        assertAll("Verify all conditions for a coordinate that is on the edges of grid",
                () -> assertTrue(!contiguousCoordinates.isEmpty()),
                () -> assertTrue(contiguousCoordinates.size() == expectedCoordinates.size()),
                () -> assertEquals(contiguousCoordinates.stream().map(Coordinate::getX).collect(Collectors.toList()),
                        expectedCoordinates.stream().map(Coordinate::getX).collect(Collectors.toList())),
                () -> assertEquals(contiguousCoordinates.stream().map(Coordinate::getY).collect(Collectors.toList()),
                        expectedCoordinates.stream().map(Coordinate::getY).collect(Collectors.toList())),
                () -> assertEquals(contiguousCoordinates.stream().map(Coordinate::getPreviousCoordinate).collect(Collectors.toList()),
                        expectedCoordinates.stream().map(Coordinate::getPreviousCoordinate).collect(Collectors.toList()))

        );

    }

}
