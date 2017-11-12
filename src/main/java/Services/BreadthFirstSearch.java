package Services;

import objectives.Labyrinth;
import objectives.Position;
import objectives.internalRepresentation.GoalNode;
import objectives.internalRepresentation.Node;

import java.util.*;

public class BreadthFirstSearch {

    private Labyrinth labyrinth;
    private SearchSpaceService searchSpaceService;

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
                labyrinth.getGraph().resetAllNodes();
                return constructPath(path, parentNode); // Und gebe den konstruierten Path zurück
            }

            addNodeToQueue(queue, parentNode.getNorthNachbar(), path, parentNode); // Fügen alle möglichen Nachbarn hinzu
            addNodeToQueue(queue, parentNode.getEastNachbar(), path, parentNode);
            addNodeToQueue(queue, parentNode.getWestNachbar(), path, parentNode);
            addNodeToQueue(queue, parentNode.getSouthNachbar(), path, parentNode);

            parentNode.setInPath(true); // Bereits abgearbeitet
//            if (queue.size() != queueSize) { // Wenn sich die Queuesize geändert hat, haben wir einen neuen State erreicht der uns auch was bringt.
            searchSpaceService.extractSearchStateFromLabyrinth(labyrinth, parentNode.getPosition());
//            }
        }
        searchSpaceService.printAllSearchStates();
        return Collections.emptyList();
    }

    private List<Node> constructPath(Map<Position, Position> path, Node goalNode) {
        List<Node> nodeList = new ArrayList<>();
        while (goalNode != null) {
            goalNode.setInPath(true);
            nodeList.add(goalNode);
            goalNode = labyrinth.getGraph().getNodeAtPosition(path.get(goalNode.getPosition()));
        }
        System.out.println(nodeList);
        searchSpaceService.extractSearchStateFromLabyrinth(labyrinth, labyrinth.getGraph().getGoalNode().getPosition());
        searchSpaceService.printAllSearchStates();
        return nodeList;
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
