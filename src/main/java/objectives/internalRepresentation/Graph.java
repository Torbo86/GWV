package objectives.internalRepresentation;

import objectives.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Graph {

    private final List<Node> nodeList = new ArrayList<>();
    private Node goalNode;
    private Node startNode;

    public Graph(char[][] charArray) {
        createGraphFromCharArray(charArray);
    }

    private void createGraphFromCharArray(char[][] charArray) {
        for (int i = 0; i < charArray.length; i++) {
            for (int j = 0; j < charArray[i].length; j++) {
                switch (charArray[i][j]) {
                    case 's':
                        startNode = new StartNode(new Position(j, i));
                        nodeList.add(startNode);
                        break;
                    case 'g':
                        goalNode = new GoalNode(new Position(j, i));
                        nodeList.add(goalNode);
                        break;
                    case 'x':
                        nodeList.add(new BoundNode(new Position(j, i)));
                        break;
                    default:
                        nodeList.add(new EmptyNode(new Position(j, i)));
                        break;
                }
            }
        }

        enterNachbarn();
    }

    public char[][] getCharArrayFromGraph(){
        char[][] charArray = new char[10][20];
        for (Node node : nodeList) {

            if(node instanceof BoundNode){
                charArray[node.getPosition().getY()][node.getPosition().getX()] = 'x';
            } else if (node instanceof EmptyNode) {
                if(node.isInPath()){
                    charArray[node.getPosition().getY()][node.getPosition().getX()] = 'o';
                } else {
                    charArray[node.getPosition().getY()][node.getPosition().getX()] = ' ';
                }
            } else if (node instanceof GoalNode){
                charArray[node.getPosition().getY()][node.getPosition().getX()] = 'g';
            } else if (node instanceof StartNode){
                charArray[node.getPosition().getY()][node.getPosition().getX()] = 's';
            }
        }
        return charArray;
    }

    private void enterNachbarn() {
        nodeList.forEach(node -> {
            node.setEastNachbar(getNodeAtPosition(new Position(node.getPosition().getX() + 1, node.getPosition().getY())));
            node.setNorthNachbar(getNodeAtPosition(new Position(node.getPosition().getX(), node.getPosition().getY() - 1)));
            node.setSouthNachbar(getNodeAtPosition(new Position(node.getPosition().getX(), node.getPosition().getY() + 1)));
            node.setWestNachbar(getNodeAtPosition(new Position(node.getPosition().getX() - 1, node.getPosition().getY())));
        });
    }

    public Node getNodeAtPosition(Position position) {
        Optional<Node> nodeAtPosition = nodeList.stream().filter(node -> node.getPosition().equals(position)).findFirst();
        return nodeAtPosition.orElse(null);
    }

    public Node getGoalNode(){
        return goalNode;
    }

    public Node getStartNode() {
        return startNode;
    }

    public void resetAllNodes(){
        nodeList.forEach(node -> {
            node.setInPath(false);
            node.setAlreadyVisisted(false);
        });
    }
}
