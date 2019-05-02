package com.franciscoreina;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class MazeSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(MazeSolver.class);

    private static final String FILE_EXTENSION = ".txt";
    private static final String RESOURCES_DIRECTORY = ".\\src\\main\\resources\\";

    public static void main(String[] args) {

        try {
            String fullPath = consoleMenu();

            Instant startTime = Instant.now();
            BufferedReader br = Files.newBufferedReader(Paths.get(fullPath));

            System.out.print("\n=== INPUT ===\n\n");
            List<String> list = br.lines().collect(Collectors.toList());
            list.forEach(System.out::println);

            Maze maze = new Maze(list);
            solveByBFS(maze);

            System.out.println("Total execution time: " + (Instant.now().toEpochMilli() - startTime.toEpochMilli()) + " ms");
        } catch (IOException e) {
            LOGGER.error(e.toString());
            LOGGER.error("Supplied file or path is not corrected");
            System.exit(-1);
        } catch (Exception e) {
            LOGGER.error(e.toString());
            System.exit(-1);
        }

    }


    public static String consoleMenu() throws IOException {
        LOGGER.info("Running the console menu");

        String fullPath;
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nSelect the file selection mode: \n 1. From the \"Resources\" folder \n 2. From a supplied directory");
        int fileSelectionMode = Integer.parseInt(scanner.nextLine());

        if (fileSelectionMode == 1) {
            List<Path> pathStream = Files.walk(Paths.get(RESOURCES_DIRECTORY))
                    .map(Path::getFileName).filter(fileName -> fileName.toString().endsWith(FILE_EXTENSION)).collect(Collectors.toList());
            pathStream.forEach(System.out::println);

            System.out.println("\nIndicate the position of the file you want to choose");
            int fileSelected = Integer.parseInt(scanner.nextLine()) - 1;
            fullPath = RESOURCES_DIRECTORY + pathStream.get(fileSelected);
            System.out.println("\nSelected file: " + pathStream.get(fileSelected));
        } else {
            System.out.println("\nSpecify the full path of the file, including the extension");

            fullPath = scanner.nextLine();
            System.out.println("\nSelected full path: " + fullPath);
        }

        return fullPath;
    }

    public static void solveByBFS(final Maze maze) {
        LOGGER.info("Solving the maze by means of the Breading First Search Algorithm");

        LinkedList<Coordinate> coordinatesToVisit = new LinkedList<>(Arrays.asList(maze.getStart()));

        while (!coordinatesToVisit.isEmpty()) {
            Coordinate current = coordinatesToVisit.removeFirst();
            LOGGER.debug("Processing the coordinate (" + current.getX() + "," + current.getY() + ")");

            if (!maze.isInMaze(current) || maze.isVisited(current)) {
                continue;
            }

            if (maze.isWall(current)) {
                maze.addVisited(current);
                continue;
            }

            if (maze.isEnd(current)) {
                maze.setSolved(true);
                System.out.println("\n=== OUTPUT ===\n\n" + maze.buildSolution(backtrackPath(current)));
                break;
            }

            coordinatesToVisit.addAll(
                    (maze.isPassageOnEdgeGrid(current))
                            ? Coordinate.getContiguousCoordinates(current, maze)
                            : Coordinate.getContiguousCoordinates(current)
            );

            maze.addVisited(current);
        }

        if (!maze.isSolved()) {
            System.out.println("\n=== OUTPUT ===\n\n" + "No solution found");
        }

    }

    private static List<Coordinate> backtrackPath(final Coordinate end) {
        LOGGER.info("Getting the coordinates of the way back");

        List<Coordinate> path = new ArrayList<>();
        Coordinate current = end;

        while (current != null) {
            path.add(current);
            current = current.getPreviousCoordinate();
        }

        return path;
    }

}
