package Services;

import objectives.Labyrinth;
import objectives.Player;
import objectives.Position;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * This class can read nats labyrinths
 */
public class LabyrinthFileReader {

    /**
     * This method reads the given file and creates a labyrinth
     *
     * @param file the file which should be read
     * @return the labyrinth object which is in the file
     */
    public Labyrinth readLabyrinthFile(File file) {
        char playGround[][] = new char[1][1];

        try {
            List<String> stringList = Files.readAllLines(file.toPath());
            playGround = new char[stringList.size()][];

            for (int i = 0; i < stringList.size(); i++) {
                playGround[i] = stringList.get(i).toCharArray();
            }

        } catch (IOException e) {
            e.getStackTrace();
        }
        return new Labyrinth(playGround, getGoalPosition(playGround), getStartPosition(playGround));
    }


    private Position getGoalPosition(char[][] playGround) {
        List<Position> goalPositionList = lookForSpecificCharInCharArray(playGround, 'g');
        if (goalPositionList.size() == 1) {
            return goalPositionList.get(0);
        } else {
            throw new RuntimeException("There isnt 1 Goalposition, there are: " + goalPositionList.size());
        }
    }

    private Position getStartPosition(char[][] charArray) {
        List<Position> startPositionList = lookForSpecificCharInCharArray(charArray, 's');
        if (startPositionList.size() == 1) {
            return startPositionList.get(0);
        } else {
            throw new RuntimeException("There isnt 1 Startposition, there are: " + startPositionList.size());
        }
    }

    private List<Position> lookForSpecificCharInCharArray(char[][] charArray, final char specificChar) {
        List<Position> positionList = new ArrayList<>();

        for (int i = 0; i < charArray.length; i++) {
            for (int j = 0; j < charArray[i].length; j++) {
                if (charArray[i][j] == specificChar) {
                    positionList.add(new Position(j, i));
                }
            }
        }
        return positionList;
    }
}
