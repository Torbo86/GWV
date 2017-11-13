package Services;

import objectives.Labyrinth;
import objectives.Position;
import objectives.internalRepresentation.GoalNode;
import objectives.internalRepresentation.Node;

import java.util.*;

/**
 * Dies hier ist die Breitensuche
 */
public class BreadthFirstSearch {

    private Labyrinth labyrinth; // Das Labyrinth dazu
    private SearchSpaceService searchSpaceService; // Ein Service der unsere SearchStates besitzt

    public BreadthFirstSearch(final Labyrinth labyrinth) {
        this.labyrinth = labyrinth; //Setzen uns unser Labyrinth
        searchSpaceService = new SearchSpaceService();
    }

    public List<Node> startSearch() {
        final Queue<Node> queue = new LinkedList<>(); // Erstellen eine Queue für die Breitensuche
        final Map<Position, Position> path = new HashMap<>(); // Erstellen eine Map um später den Weg zu bekommen

        queue.add(labyrinth.getGraph().getStartNode()); // Wir fügen die erste Node hinein (StartNode)
        queue.peek().setAlreadyVisisted(true); // Die Startnode haben wir damit bereits besucht
        queue.peek().setInPath(true); // Haben wir bereits abgearbeitet

        while (!queue.isEmpty()) { // Mache solange, bis die Queue leer ist
            Node parentNode = queue.remove(); // Entferne das erste Element aus der Queue

            int queueSize = queue.size(); // Wir merken uns die Queuesize, damit wir nicht unnötig viele Searchstates haben

            if (parentNode instanceof GoalNode) { // Wenn wir das Goal haben
                searchSpaceService.printAllSearchStates(); // Dann printe alle Searchstates
                labyrinth.getGraph().resetAllNodes(); //Wir resetten einmal den Graphen um einen neuen zu basteln, wo nur der Weg eingezeichnet ist
                return constructPath(path, parentNode); // Und gebe den konstruierten Path zurück
            }

            addNodeToQueue(queue, parentNode.getNorthNachbar(), path, parentNode); // Fügen alle möglichen Nachbarn hinzu
            addNodeToQueue(queue, parentNode.getEastNachbar(), path, parentNode);
            addNodeToQueue(queue, parentNode.getWestNachbar(), path, parentNode);
            addNodeToQueue(queue, parentNode.getSouthNachbar(), path, parentNode);

            parentNode.setInPath(true); // Bereits abgearbeitet

            searchSpaceService.extractSearchStateFromLabyrinth(labyrinth, parentNode.getPosition()); //Erstellen uns ein Searchstate von dem jetzigen State

        }
        searchSpaceService.printAllSearchStates(); // Am Ende printe alle States
        return Collections.emptyList(); // und returne eine leere Liste, da man nicht zum Goal kam
    }

    /**
     * Diese Methode konstruiert uns den Weg zum Ziel
     *
     * @param path     Das Dictionary mit allen Verbindungen die genommen wurden
     * @param goalNode Das Ziel
     * @return eine sortierte Liste mit dem Weg bis zum Ziel
     */
    private List<Node> constructPath(Map<Position, Position> path, Node goalNode) {
        List<Node> nodeList = new ArrayList<>();
        while (goalNode != null) {
            goalNode.setInPath(true);
            nodeList.add(goalNode); //Fügen die GoalNode hinzu
            goalNode = labyrinth.getGraph().getNodeAtPosition(path.get(goalNode.getPosition()));
            // Setzen für jede Node auf True, wenn dieser auf dem Weg liegt. Und aus der Map können wir sehen, welche Verbindungen genommen wurden
        }
        System.out.println("Folgender Weg wurde genommen (reversed): \n" + nodeList); // Printe die List aus
        searchSpaceService.extractSearchStateFromLabyrinth(labyrinth, labyrinth.getGraph().getGoalNode().getPosition());
        System.out.println("Und so sieht der Weg aus: \n");
        searchSpaceService.printAllSearchStates();
        return nodeList; // Wir erstellen ein SearchState der nochmal angibt, welchen Weg man genommen hat.
    }

    /**
     * Diese Methode fügt einen Nachbarn der Queue hinzu wenn dieses überhaupt möglich ist (Keine Boundnode)
     * oder wenn dieser nicht bereits besucht wurde und ungleich null ist.
     * <p>
     * Weiterhin werden die Nachbarn dann auch als bereits besucht markiert und in die Map mit den parentNodes
     * hinzugefügt
     *
     * @param queue      Die Queue der Breitensuche
     * @param nachbar    Der Nachbar der untersucht wird
     * @param map        Die Map mit den Wegen
     * @param parentNode Der ParentNode
     */
    private void addNodeToQueue(Queue<Node> queue, Node nachbar, Map<Position, Position> map, Node parentNode) {
        if (nachbar != null
                && nachbar.isPossible()
                && !nachbar.isAlreadyVisited()) {
            queue.add(nachbar);
            nachbar.setAlreadyVisisted(true);
            map.put(nachbar.getPosition(), parentNode.getPosition());
        }
    }

}
