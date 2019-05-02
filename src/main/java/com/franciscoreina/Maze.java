package com.franciscoreina;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Maze {

    private static final Logger LOGGER = LoggerFactory.getLogger(Maze.class);

    private static final int ROW_MAZE_BEGINS_FILE = 3;

    private static final String INPUT_WALL = "1";
    private static final String INPUT_PASSAGE = "0";
    private static final String INPUT_SEPARATOR = " ";

    private static final String OUTPUT_WALL = "#";
    private static final String OUTPUT_PASSAGE = " ";
    private static final String OUTPUT_PATH = "X";
    private static final String OUTPUT_START = "S";
    private static final String OUTPUT_END = "E";

    private int width;
    private int height;

    private Coordinate start;
    private Coordinate end;

    private boolean solved = false;

    private int[][] maze;
    private String[][] outputMaze;
    private boolean[][] visited;

    public Maze() {
    }

    public Maze(final List<String> mazeDescription) {
        initSizeMaze(mazeDescription.get(0));
        initStartCoordinate(mazeDescription.get(1));
        initEndCoordinate(mazeDescription.get(2));

        filledMazeAndOutputMaze(mazeDescription);
    }

    public void initSizeMaze(final String mazeSize) {
        LOGGER.info("Initializing the maze size");

        width = Integer.parseInt(mazeSize.split(INPUT_SEPARATOR)[0]);
        height = Integer.parseInt(mazeSize.split(INPUT_SEPARATOR)[1]);

        // height = rows = coordinate y
        // width = columns = coordinate x
        maze = new int[height][width];
        outputMaze = new String[height][width];
        visited = new boolean[height][width];
    }

    public void initStartCoordinate(final String startCoordinate) {
        LOGGER.info("Initializing the start location");

        start = new Coordinate(Integer.parseInt(startCoordinate.split(INPUT_SEPARATOR)[0]),
                Integer.parseInt(startCoordinate.split(INPUT_SEPARATOR)[1]));
    }

    public void initEndCoordinate(final String endCoordinate) {
        LOGGER.info("Initializing the end location");

        end = new Coordinate(Integer.parseInt(endCoordinate.split(INPUT_SEPARATOR)[0]),
                Integer.parseInt(endCoordinate.split(INPUT_SEPARATOR)[1]));
    }

    public void filledMazeAndOutputMaze(final List<String> mazeDescription) {
        LOGGER.info("Filling in the input maze and the skeleton of the output maze");

        for (int row = 0; row < height; row++) {
            String[] rowMazeString = mazeDescription.get(ROW_MAZE_BEGINS_FILE + row).split(INPUT_SEPARATOR);

            maze[row] = Stream.of(rowMazeString).mapToInt(Integer::parseInt).toArray();

            outputMaze[row] = Stream.of(rowMazeString)
                    .map(element -> element.replaceAll(INPUT_WALL, OUTPUT_WALL).replaceAll(INPUT_PASSAGE, OUTPUT_PASSAGE))
                    .toArray(String[]::new);
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Coordinate getStart() {
        return start;
    }

    public Coordinate getEnd() {
        return end;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public int[][] getMaze() {
        return maze;
    }

    public String[][] getOutputMaze() {
        return outputMaze;
    }

    public boolean[][] getVisited() {
        return visited;
    }

    public boolean isEnd(final Coordinate coordinate) {
        return coordinate.getX() == end.getX() && coordinate.getY() == end.getY();
    }

    public void addVisited(final Coordinate coordinate) {
        visited[coordinate.getY()][coordinate.getX()] = true;
    }

    public boolean isVisited(final Coordinate coordinate) {
        return visited[coordinate.getY()][coordinate.getX()];
    }

    public boolean isInMaze(final Coordinate coordinate) {
        return coordinate.getY() < height && coordinate.getX() < width;
    }

    public boolean isWall(final Coordinate coordinate) {
        return maze[coordinate.getY()][coordinate.getX()] == Integer.parseInt(INPUT_WALL);
    }

    public boolean isPassageOnEdgeGrid(final Coordinate coordinate) {
        int x = coordinate.getX();
        int y = coordinate.getY();
        return maze[y][x] == Integer.parseInt(INPUT_PASSAGE) && (x == 0 || x == width - 1 || y == 0 || y == height - 1);
    }

    private void printStart() {
        outputMaze[start.getY()][start.getX()] = OUTPUT_START;
    }

    private void printEnd() {
        outputMaze[end.getY()][end.getX()] = OUTPUT_END;
    }

    public String buildSolution(final List<Coordinate> path) {
        LOGGER.info("Build the solution");

        path.forEach(coordinate -> outputMaze[coordinate.getY()][coordinate.getX()] = OUTPUT_PATH);
        printStart();
        printEnd();

        return Stream.of(outputMaze)
                .map(row -> (Arrays.toString(row).replaceAll(", |[\\[\\]]", "")))
                .collect(Collectors.joining("\n"));
    }

}
