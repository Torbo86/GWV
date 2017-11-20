package objectives.internalRepresentation;

import objectives.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Graph {

    private final List<Node> nodeList = new ArrayList<>(); // Liste mit alle Nodes
    private Node goalNode; // Das Ziel / Sollte nicht genutzt wenn mehrere Goals das sind
    private List<Node> goalNodeList = new ArrayList<>(); // Liste mit alle GoalNodes, wenn mehrere vorhanden sind
    private Node startNode; // Der Start
    private List<TeleportationNode> teleportationNodes = new ArrayList<>();

    public Graph(char[][] charArray) {
        createGraphFromCharArray(charArray);
    } //Kreieren aus dem übergebenen Char-Array unseren Graphen

    private void createGraphFromCharArray(char[][] charArray) {
        for (int i = 0; i < charArray.length; i++) {
            for (int j = 0; j < charArray[i].length; j++) { // Gehe einmal vollständig durch das Array
                switch (charArray[i][j]) {
                    case 's':
                        startNode = new StartNode(new Position(j, i)); // Erstelle StartNode
                        nodeList.add(startNode);
                        break;
                    case 'g':
                        goalNode = new GoalNode(new Position(j, i)); // Erstelle GoalNode
                        nodeList.add(goalNode); // Fuege sie der NodeList hinzu
                        goalNodeList.add(goalNode); //Fuege sich der GoalNodeListe hinzu
                        break;
                    case 'x':
                        nodeList.add(new BoundNode(new Position(j, i))); // Erstelle BoundNodes wenn eine Grenze da ist
                        break;
                    case ' ':
                        nodeList.add(new EmptyNode(new Position(j, i))); // Erstelle eine EmptyNode wenn nichts drin ist
                        break;
                    default:
                        TeleportationNode teleportationNode = new TeleportationNode(new Position(j, i), charArray[i][j]); //Erstelle eine TeleportationNode
                        nodeList.add(teleportationNode); // Fuege sie der allg. Nodelist hinzu
                        teleportationNodes.add(teleportationNode); // Fuege sie der teleportationNodeList hinzu
                        break;
                }
            }
        }

        enterNachbarn(); // Erstelle jetzt zu jeder Node die Nachbarn
        setTeleportationBinding(); //Verbinde die TeleportNodes
    }

    private void setTeleportationBinding() {
        List<Node> teleportationNodes = nodeList.stream().filter(node -> node instanceof TeleportationNode).collect(Collectors.toList()); // Erstelle eine Liste mit allen TeleportNodes

        teleportationNodes.forEach(node -> { //Für jede node aus der TeleportNodeList
            // Bekomme eine andere TeleportNode mit dem selben Namen
            Optional<Node> teleportationNode = getTeleportationNodeWithName(((TeleportationNode)node), ((TeleportationNode)node).getTeleportName());
            teleportationNode.ifPresent(node1 -> { // Wenn es solch eine Node gibt dann
                ((TeleportationNode) node).setConnectedTeleportNode(node1); //Setze die bekommene Node mögliche Teleportation
                ((TeleportationNode) node1).setConnectedTeleportNode(node); // Und setze dich selbst als TeleportNode
            });
        });
    }

    /*
     * @return Alle GoalNodes
     */
    public List<Node> getGoalNodeList() {
        return goalNodeList;
    }

    /**
     * Baut aus dem Graphen ein char-Array wieder zusammen (Spielfeld)
     */
    public char[][] getCharArrayFromGraph() {
        char[][] charArray = new char[10][20]; //Wir kennen die Größe vom Array
        for (Node node : nodeList) { //Für jede Node erstelle den dazugehörigen char
            if (node instanceof BoundNode) {
                charArray[node.getPosition().getY()][node.getPosition().getX()] = 'x'; // BoundNode = x
            } else if (node instanceof EmptyNode) {
                if (node.isInPath()) {
                    charArray[node.getPosition().getY()][node.getPosition().getX()] = 'o'; //Besuchte EmptyNode = o
                } else {
                    charArray[node.getPosition().getY()][node.getPosition().getX()] = ' '; //Leere EmptyNode =
                }
            } else if (node instanceof GoalNode) {
                charArray[node.getPosition().getY()][node.getPosition().getX()] = 'g'; //GoalNode = g
            } else if (node instanceof StartNode) {
                charArray[node.getPosition().getY()][node.getPosition().getX()] = 's'; //StartNode = s
            } else if (node instanceof TeleportationNode){
                charArray[node.getPosition().getY()][node.getPosition().getX()] = ((TeleportationNode)node).getTeleportName(); // TeleportNode = TeleportNodeName
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
        Optional<Node> nodeAtPosition = nodeList.stream().filter(node -> node.getPosition().equals(position)).findFirst(); //Gehe durch die gesamte Liste
        return nodeAtPosition.orElse(null);
    }

    private Optional<Node> getTeleportationNodeWithName(TeleportationNode teleportnode, char teleportnodeName) {
        //Erstelle eine Liste mit TeleportNodes, dessen Name der selbe ist wie der übergebene und die Position != der übergebenen TeleportNode ist
        return nodeList.stream().filter(node ->
                node instanceof TeleportationNode && ((TeleportationNode) node).getTeleportName() == teleportnodeName &&
                        !node.getPosition().equals(teleportnode.getPosition())).findFirst();
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

    /**
     * @return Alle TeleportationNodes
     */
    public List<TeleportationNode> getTeleportationNodes(){
        return teleportationNodes;
    }
}
