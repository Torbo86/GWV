package Services;

import objectives.Labyrinth;
import objectives.Position;
import objectives.internalRepresentation.GoalNode;
import objectives.internalRepresentation.Node;

import java.util.*;

/**
 * Diese Klasse repräsentiert die Tiefensuche
 */
public class DepthFirstSearch {

    private Labyrinth labyrinth;
    private SearchSpaceService searchSpaceService;

    public DepthFirstSearch(final Labyrinth labyrinth) {
        this.labyrinth = labyrinth;
        this.searchSpaceService = new SearchSpaceService();
    }

    public List<Node> startDepthSearch() {
        final Stack<Node> stack = new Stack<>(); //Brauchen ein Stack
        final Map<Position, Position> dictionary = new HashMap<>(); //Diese Map enthält alle genommenen Verbindungen
        stack.push(labyrinth.getGraph().getStartNode()); //Startnode in den Stack

        while (!stack.isEmpty()) {
            Node node = stack.pop();

            if (node instanceof GoalNode) { //Wenn wir das Ziel haben
                searchSpaceService.printAllSearchStates(); //Printe alle bisher erreichten States
                labyrinth.getGraph().resetAllNodes(); //Resette alle Nodes, damit wir uns den Weg erstellen können
                return constructPath(dictionary, node); //Konstruiere den Path
            }

            addNodeToStack(node.getWestNachbar(), stack, dictionary, node); //Füge die Nachbarn dem Stack hinzu
            addNodeToStack(node.getSouthNachbar(), stack, dictionary, node);
            addNodeToStack(node.getEastNachbar(), stack, dictionary, node);
            addNodeToStack(node.getNorthNachbar(), stack, dictionary, node);

            node.setInPath(true); //Die Node wurde vollkommen abgearbeitet

            searchSpaceService.extractSearchStateFromLabyrinth(labyrinth, node.getPosition(), stack.size()); //Erstelle ein SearchState
        }
        searchSpaceService.printAllSearchStates(); // Auch wenn kein Ziel gefunden wurde, zeige alle SearchStates
        return Collections.emptyList(); //Gebe eine leere Liste zurück
    }

    /**
     * Diese Methode konstruiert uns den Weg
     *
     * @param dictionary Die Map mit allen Verbindungen
     * @param goalNode   Der Zielknoten
     * @return eine Liste mit Nodes, die den Weg repräsentieren
     */

    private List<Node> constructPath(Map<Position, Position> dictionary, Node goalNode) {
        List<Node> nodeList = new ArrayList<>();
        while (goalNode != null) {
            goalNode.setInPath(true);
            nodeList.add(goalNode);
            goalNode = labyrinth.getGraph().getNodeAtPosition(dictionary.get(goalNode.getPosition()));
            //Gehe vom GoalNode immer weiter nach oben und finde so den Weg raus. Im Dicitionary sind alle genommenen
            //Verbindungen drin
        }

        System.out.println("Folgender Weg wurde genommen (reversed): \n" + nodeList); // Printe die List aus
        searchSpaceService.extractSearchStateFromLabyrinth(labyrinth, labyrinth.getGraph().getGoalNode().getPosition(), 0);
        System.out.println("Und so sieht der Weg aus: \n");
        searchSpaceService.printAllSearchStates();
        return nodeList; // Wir erstellen ein SearchState der nochmal angibt, welchen Weg man genommen hat.
    }

    /**
     * Diese Methode fügt eine Node zum Stack hinzu
     */
    private void addNodeToStack(Node node, Stack<Node> stack, Map<Position, Position> map, Node parentNode) {
        if (node != null
                && node.isPossible()
                && !node.isAlreadyVisited()) {
            stack.push(node);
            node.setAlreadyVisisted(true);
            map.put(node.getPosition(), parentNode.getPosition()); //Die genommene Verbindung
        }
    }
}
