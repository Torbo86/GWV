package Services;

import objectives.Labyrinth;
import objectives.Position;
import objectives.internalRepresentation.GoalNode;
import objectives.internalRepresentation.Node;
import objectives.internalRepresentation.TeleportationNode;

import java.util.*;

public class AStarSearch
{

    private Labyrinth labyrinth;
    private SearchSpaceService searchSpaceService;

    public AStarSearch(final Labyrinth labyrinth)
    {
        this.labyrinth = labyrinth;
        this.searchSpaceService = new SearchSpaceService();
    }

    public List<Node> startSearch(final int searchMode){
        final List<Node> frontierList = new ArrayList<>();
        final Map<Position, Position> path = new HashMap<>();

        frontierList.add(labyrinth.getGraph().getStartNode()); // Wir fügen die erste Node hinein (StartNode)
        frontierList.get(0).setAlreadyVisisted(true); // Die Startnode haben wir damit bereits besucht

        while (!frontierList.isEmpty()) { // Mache solange, bis die Liste leer ist
            Node parentNode;

            if(searchMode == 0){ // Auswahl welcher Strategie
                parentNode = getBestNode(frontierList);
            } else if(searchMode == 1){
                parentNode = getBestNodeWithPortals(frontierList);
            } else {
                parentNode = getBestNodeWithMoreGoalNodes(frontierList);
            }
            frontierList.remove(parentNode); // Entferne den genommenen Knoten aus der Frontier

            if (parentNode instanceof GoalNode) { // Wenn wir das Goal haben
                labyrinth.getGraph().resetAllNodes(); //Wir resetten einmal den Graphen um einen neuen zu basteln, wo nur der Weg eingezeichnet ist
                return constructPath(path, parentNode); // Und gebe den konstruierten Path zurück
            }

            addNodeToList(frontierList, parentNode.getNorthNachbar(), path, parentNode); // Fügen alle möglichen Nachbarn hinzu
            addNodeToList(frontierList, parentNode.getEastNachbar(), path, parentNode);
            addNodeToList(frontierList, parentNode.getWestNachbar(), path, parentNode);
            addNodeToList(frontierList, parentNode.getSouthNachbar(), path, parentNode);

            parentNode.setInPath(true); // Bereits abgearbeitet

            searchSpaceService.extractSearchStateFromLabyrinth(labyrinth, parentNode.getPosition(), frontierList.size()); //Erstellen uns ein Searchstate von dem jetzigen State

        }
        searchSpaceService.printAllSearchStates(); // Am Ende printe alle States
        return Collections.emptyList(); // und returne eine leere Liste, da man nicht zum Goal kam
    }

    /**
     * Diese Methode gibt dir den besten Node raus, wenn es mehrere Goals gibt (Mit Teleportknoten möglich !)
     * @param frontiertList Die Frontier
     * @return den besten Knoten
     */
    private Node getBestNodeWithMoreGoalNodes(List<Node> frontiertList){
        Node bestNode = null;
        double bestNodeDistance = Double.MAX_VALUE; // Max value weil wir den zum vergleich ziemlich groß haben müssen anfangs

        for(Node node : frontiertList){ // Für jeden Knoten in meiner Frontier
            double distance = Double.MAX_VALUE;

            for(Node goalNode : labyrinth.getGraph().getGoalNodeList()) { // Und für jeden Zielknoten

                double goalDistance = getEuclideanDistance(node, goalNode); // Berechne die Distanz zwischen node und goalNode

                for (TeleportationNode teleportationNode : labyrinth.getGraph().getTeleportationNodes()) { // und auch die Distanzen mit TeleportNodes zu den jeweiligen Goals
                    double distanceWithTeleporters = getEuclideanDistance(node, teleportationNode) + getEuclideanDistance(teleportationNode.getConnectedTeleportNode(), goalNode);

                    if (distanceWithTeleporters < goalDistance) { // Wenn ein Weg mit dem Teleporter besser ist,
                        goalDistance = distanceWithTeleporters; // setze diesen
                    }
                }

                if(goalDistance < distance) { // Wenn der Weg für den Zielknoten kleine ist, als die mom. Distanz, nehme den
                    distance = goalDistance;
                }
            }

                if (distance < bestNodeDistance) { // Wenn der Knoten eine kleinere Distanz halt, als die momentane beste Distanz
                    bestNode = node; // nehme den
                    bestNodeDistance = distance;
                }
            }
        return bestNode;
    }

    /**
     * Diese methode ermittelt den besten Knoten in deiner Frontier ohne TeleportNodes und nur einem Zielknoten
     */
    private Node getBestNode(List<Node> nodeList) {
        Node bestNode = null;
        double bestNodeDistance = Double.MAX_VALUE;

        for(Node node : nodeList){
            double distance = getEuclideanDistance(node, labyrinth.getGraph().getGoalNode());

            if(distance < bestNodeDistance){
                bestNode = node;
                bestNodeDistance = distance;
            }
        }
        return bestNode;
    }

    /**
     * Diese Methode ermittelt den besten Knoten mit TeleportNodes und einem Zielknoten
     */
    private Node getBestNodeWithPortals(List<Node> nodeList){
        Node bestNode = null;
        double bestNodeDistance = Double.MAX_VALUE;

        for(Node node : nodeList){
            double distance = getEuclideanDistance(node, labyrinth.getGraph().getGoalNode());

            for(TeleportationNode teleportationNode : labyrinth.getGraph().getTeleportationNodes()){
                double distanceWithTeleporters = getEuclideanDistance(node, teleportationNode) + getEuclideanDistance(teleportationNode.getConnectedTeleportNode(), labyrinth.getGraph().getGoalNode());

                if(distanceWithTeleporters < distance){
                    distance = distanceWithTeleporters;
                }
            }

            if(distance < bestNodeDistance){
                bestNode = node;
                bestNodeDistance = distance;
            }
        }
        return bestNode;
    }

    /**
     * Diese Methode berechnte die euklidische Distanz
     */
    private double getEuclideanDistance(Node node, Node node2) {
        double ycoord = Math.abs (node.getPosition().getY() - node2.getPosition().getY());
        double xcoord = Math.abs (node.getPosition().getX() - node2.getPosition().getX());
        return Math.sqrt((ycoord)*(ycoord) +(xcoord)*(xcoord));
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
        searchSpaceService.extractSearchStateFromLabyrinth(labyrinth, labyrinth.getGraph().getGoalNode().getPosition(), 0);
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
     * @param nachbar    Der Nachbar der untersucht wird
     * @param map        Die Map mit den Wegen
     * @param parentNode Der ParentNode
     */
    private void addNodeToList(List<Node> nodeList, Node nachbar, Map<Position, Position> map, Node parentNode) {
        if (nachbar != null
                && nachbar.isPossible()
                && !nachbar.isAlreadyVisited()) {
            nodeList.add(nachbar);
            nachbar.setAlreadyVisisted(true);
            map.put(nachbar.getPosition(), parentNode.getPosition());
        }
    }
}
