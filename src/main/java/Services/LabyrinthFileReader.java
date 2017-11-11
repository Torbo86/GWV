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
 * Diese Klasse ließt Files und erstellt daraus Labyrinthe
 */
public class LabyrinthFileReader {

    /**
     * Diese Methode ließt die File die übergeben wurde
     *
     * @param file Die File die gelesen werden soll
     * @return Tha Labyrinth aus der File
     */
    public Labyrinth readLabyrinthFile(File file) {
        char playGround[][] = new char[1][1]; // Erstellen einmal unser char Array mit einer Pseudo-größe

        try {
            List<String> stringList = Files.readAllLines(file.toPath()); // Lesen alle Lines als String raus
            playGround = new char[stringList.size()][]; // Wir kennen nun die Größe der Y-Achse

            for (int i = 0; i < stringList.size(); i++) { // Für jede Line
                playGround[i] = stringList.get(i).toCharArray(); // Mach daraus ein Char-Array und füge das für die jeweilige Line ein
            }

        } catch (IOException e) {
            e.getStackTrace();
        }
        return new Labyrinth(playGround, getGoalPosition(playGround), getStartPosition(playGround)); // Erstelle ein neues Labyrinth mit Start und Ziel
    }


    /**
     * Diese Methode findet die Goal-Position
     * @param charArray in welchem Char-Array soll geschaut werden
     * @return die Position des Goal
     */
    private Position getGoalPosition(char[][] charArray) {
        List<Position> goalPositionList = lookForSpecificCharInCharArray(charArray, 'g'); //Hol dir eine Liste mit allen Goals
        if (goalPositionList.size() == 1) { // Es darf aber nur ein Goal vorhanden sein
            return goalPositionList.get(0);
        } else {
            throw new RuntimeException("There isnt 1 Goalposition, there are: " + goalPositionList.size());
        }
    }

    /**
     * Diese Methode gibt uns im übergebenen Char-Array die Start Position
     * @param charArray das Char-Array wodrin geschaut werden soll
     * @return die Position des Starts
     */
    private Position getStartPosition(char[][] charArray) {
        List<Position> startPositionList = lookForSpecificCharInCharArray(charArray, 's'); //Bekomme eine Liste von Startpositionen
        if (startPositionList.size() == 1) { // Es darf aber nur einer sein
            return startPositionList.get(0);
        } else {
            throw new RuntimeException("There isnt 1 Startposition, there are: " + startPositionList.size());
        }
    }

    /**
     * Diese Methode liefer für einen spezifischen Character die Positionen zurück
     * @param charArray In welchem Char-Array soll geschaut werden
     * @param specificChar Nach welchen Char soll gesucht werden
     * @return eine Liste von allen Positionen
     */
    private List<Position> lookForSpecificCharInCharArray(char[][] charArray, final char specificChar) {
        List<Position> positionList = new ArrayList<>();

        // Normale for-schleife in einer for-schleife
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
