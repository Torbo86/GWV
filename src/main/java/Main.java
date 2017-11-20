import Services.*;
import com.sun.org.apache.xpath.internal.operations.Bool;
import objectives.Labyrinth;
import objectives.internalRepresentation.Node;

import java.io.File;
import java.util.ArrayList;
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

        Labyrinth labyrinth = reader.readLabyrinthFile(new File(THIRD_LABYRINTH)); //Welche Datei soll gelesen werden ?

//        BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch(labyrinth); // Wir stellen unser Breitensuchen-Objekt
//        List<Node> nodeList = breadthFirstSearch.startSearch(); // Starten die Breitensuche

//        DepthFirstSearch depthFirstSearch = new DepthFirstSearch(labyrinth);
//
//        List<Node> nodeList = depthFirstSearch.startDepthSearch();

//        HeuristicSearch heuristicSearch = new HeuristicSearch(labyrinth);
//
//        List<Node> nodeList = heuristicSearch.startSearch();

        AStarSearch aStarSearch = new AStarSearch(labyrinth);
        List<Node> nodeList = aStarSearch.startSearch(1);

        if (nodeList.isEmpty()) {
            System.out.println("Es wurde kein Weg gefunden");
        }
    }
}

// Aufgabe 4.2.3:
/*
    Wenn man mal das Labyrinth von Blatt3 benutzt, dann ist es möglich mit der Tiefensuche den selben Weg zu finden,
    wie die Breitensuche (kürzester Weg). Nur wenn man die Prioritäten der Auswahl von den Weg ändert, dann kann man
    auch zu einem viel längeren Weg kommen. Die Tiefensuche geht ja immer weiter vorwärts und findet dadurch den Weg
    eventuell schneller, während die Breitensuche sich immer breite aufstellt mit jedem Durchgang, so aber den kürzesten
    Weg am Ende bekommt.


    Aufgabe 4.2.4:

    Einfach mit SECOND_LABYRINTH in der Main-Klasse das Programm starten.
 */