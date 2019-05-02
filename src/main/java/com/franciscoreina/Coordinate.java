package com.franciscoreina;

import java.util.Arrays;
import java.util.List;

public class Coordinate {

    private int x;
    private int y;
    private Coordinate previousCoordinate;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
        previousCoordinate = null;
    }

    public Coordinate(int x, int y, Coordinate previousCoordinate) {
        this.x = x;
        this.y = y;
        this.previousCoordinate = previousCoordinate;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Coordinate getPreviousCoordinate() {
        return previousCoordinate;
    }

    public static List<Coordinate> getContiguousCoordinates(final Coordinate current) {
        return getContiguousCoordinates(current, null);
    }

    public static List<Coordinate> getContiguousCoordinates(final Coordinate current, final Maze maze) {
        int up = current.getY() - 1;
        int right = current.getX() + 1;
        int down = current.getY() + 1;
        int left = current.getX() - 1;

        if (maze != null) {
            if (current.getX() == 0) { // Left edge
                left = maze.getWidth() - 1;
            } else if (current.getY() == 0) { // Top edge
                up = maze.getHeight() - 1;
            } else if (current.getX() == maze.getWidth() - 1) { // Right edge
                right = 0;
            } else if (current.getY() == maze.getHeight() - 1) { // Bottom edge
                down = 0;
            }
        }

        return Arrays.asList(
                new Coordinate(current.getX(), up, current), // Up
                new Coordinate(right, current.getY(), current), // Right
                new Coordinate(current.getX(), down, current), // Down
                new Coordinate(left, current.getY(), current)  // Left
        );
    }

}
