package objectives.internalRepresentation;

import objectives.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Graph {

    private final List<Node> nodeList = new ArrayList<>(); // Liste mit alle Nodes
    private Node goalNode; // Das Ziel
    private Node startNode; // Der Start

    public Graph(char[][] charArray) {
        createGraphFromCharArray(charArray);
    } //Kreieren aus dem übergebenen Char-Array unseren Graphen

    private void createGraphFromCharArray(char[][] charArray) {
        for (int i = 0; i < charArray.length; i++) {
            for (int j = 0; j < charArray[i].length; j++) {
                switch (charArray[i][j]) {
                    case 's':
                        startNode = new StartNode(new Position(j, i)); // Erstelle StartNode
                        nodeList.add(startNode);
                        break;
                    case 'g':
                        goalNode = new GoalNode(new Position(j, i)); // Erstelle GoalNode
                        nodeList.add(goalNode);
                        break;
                    case 'x':
                        nodeList.add(new BoundNode(new Position(j, i))); // Erstelle BoundNodes wenn eine Grenze da ist
                        break;
                    case ' ':
                        nodeList.add(new EmptyNode(new Position(j, i))); // Erstelle für den Rest der Chars im Array nur EmptyNode
                        break;
                    default:
                        nodeList.add(new TeleportationNode(new Position(j, i), charArray[i][j]));
                        break;
                }
            }
        }

        enterNachbarn(); // Erstelle jetzt zu jeder Node die Nachbarn
        setTeleportationBinding();
    }

    private void setTeleportationBinding() {
        List<Node> teleportationNodes = nodeList.stream().filter(node -> node instanceof TeleportationNode).collect(Collectors.toList());
        teleportationNodes.forEach(node -> {
            Optional<Node> teleportationNode = getTeleportationNodeWithName(((TeleportationNode)node), ((TeleportationNode)node).getTeleportName());
            teleportationNode.ifPresent(node1 -> {
                ((TeleportationNode) node).setTeleportNode(node1);
                ((TeleportationNode) node1).setTeleportNode(node);
            });
        });
    }

    public char[][] getCharArrayFromGraph() {
        char[][] charArray = new char[10][20];
        for (Node node : nodeList) {

            if (node instanceof BoundNode) {
                charArray[node.getPosition().getY()][node.getPosition().getX()] = 'x';
            } else if (node instanceof EmptyNode) {
                if (node.isInPath()) {
                    charArray[node.getPosition().getY()][node.getPosition().getX()] = 'o';
                } else {
                    charArray[node.getPosition().getY()][node.getPosition().getX()] = ' ';
                }
            } else if (node instanceof GoalNode) {
                charArray[node.getPosition().getY()][node.getPosition().getX()] = 'g';
            } else if (node instanceof StartNode) {
                charArray[node.getPosition().getY()][node.getPosition().getX()] = 's';
            } else if (node instanceof TeleportationNode){
                charArray[node.getPosition().getY()][node.getPosition().getX()] = ((TeleportationNode)node).getTeleportName();
            }
        }
        return charArray;
    }

    /**
     * Diese Mehtode setzt jeden Nachbarn für alle Nodes
     */
    private void enterNachbarn() {
        nodeList.forEach(node -> {
            node.setEastNachbar(getNodeAtPosition(new Position(node.getPosition().getX() + 1, node.getPosition().getY()))); // Setze als N/E/S/W - Nachbar die jeweilige Node.
            node.setNorthNachbar(getNodeAtPosition(new Position(node.getPosition().getX(), node.getPosition().getY() - 1)));
            node.setSouthNachbar(getNodeAtPosition(new Position(node.getPosition().getX(), node.getPosition().getY() + 1)));
            node.setWestNachbar(getNodeAtPosition(new Position(node.getPosition().getX() - 1, node.getPosition().getY())));
        });
    }

    /**
     * Diese Methode gibt die Node an einer bestimmte Position
     *
     * @param position die Position, von der Node gebraucht wird
     * @return die jeweilige Node oder als Null.
     */
    public Node getNodeAtPosition(Position position) {
        Optional<Node> nodeAtPosition = nodeList.stream().filter(node -> node.getPosition().equals(position)).findFirst();
        return nodeAtPosition.orElse(null);
    }

    public Optional<Node> getTeleportationNodeWithName(TeleportationNode teleportnode, char teleportnodeName) {
        Optional<Node> teleportationNodeWithChar = nodeList.stream().filter(node ->
                node instanceof TeleportationNode && ((TeleportationNode) node).getTeleportName() == teleportnodeName &&
                        !node.getPosition().equals(teleportnode.getPosition())).findFirst();

        return teleportationNodeWithChar;
    }

    /**
     * Returns die GoalNode
     */
    public Node getGoalNode() {
        return goalNode;
    }

    /**
     * Returns the StartNode
     */
    public Node getStartNode() {
        return startNode;
    }

    /**
     * Alle boolschen Felder auf den Nodes, werden auf false gesetzt.
     */
    public void resetAllNodes() {
        nodeList.forEach(node -> {
            node.setInPath(false);
            node.setAlreadyVisisted(false);
        });
    }
}
