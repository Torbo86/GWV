import Services.BreadthFirstSearch;
import Services.LabyrinthFileReader;
import Services.SearchSpaceService;
import objectives.Labyrinth;
import objectives.internalRepresentation.Node;

import java.io.File;
import java.util.List;

/**
 * This is the main class
 */
public class Main {

    private final static String FIRST_LABYRINTH = "src/main/resources/labyrinths/blatt3_environment.txt";
    private final static String SECOND_LABYRINTH = "src/main/resources/labyrinths/blatt4_environment_a.txt";
    private final static String THIRD_LABYRINTH = "src/main/resources/labyrinths/blatt4_environment_b.txt";


    public static void main(String... args) {
        LabyrinthFileReader reader = new LabyrinthFileReader(); // Wir erstellen unsere File

        Labyrinth labyrinth = reader.readLabyrinthFile(new File(SECOND_LABYRINTH)); //Welche Datei soll gelesen werden ?

        BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch(labyrinth); // Wir stellen unser Breitensuchen-Objekt

        List<Node> nodeList = breadthFirstSearch.startSearch(); // Starten die Breitensuche
    }
}
