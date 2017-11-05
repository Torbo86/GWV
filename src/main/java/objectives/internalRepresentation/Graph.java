package objectives.internalRepresentation;

import objectives.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Graph {

    private final List<Node> nodeList = new ArrayList<>();

    public Graph(char[][] charArray) {
        createGraphFromCharArray(charArray);
    }

    private void createGraphFromCharArray(char[][] charArray) {
        for (int i = 0; i < charArray.length; i++) {
            for (int j = 0; j < charArray[i].length; j++) {
                switch (charArray[i][j]) {
                    case 's':
                        nodeList.add(new EmptyNode(new Position(j, i)));
                        break;
                    case 'g':
                        nodeList.add(new GoalNode(new Position(j, i)));
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

    private void enterNachbarn() {
        nodeList.forEach(node -> {
            node.setEastNachbar(getNodeAtPosition(new Position(node.getPosition().getX() + 1, node.getPosition().getY())));
            node.setNorthNachbar(getNodeAtPosition(new Position(node.getPosition().getX(), node.getPosition().getY() - 1)));
            node.setSouthNachbar(getNodeAtPosition(new Position(node.getPosition().getX(), node.getPosition().getY() + 1)));
            node.setWestNachbar(getNodeAtPosition(new Position(node.getPosition().getX() - 1, node.getPosition().getY())));
        });
    }

    public Node getNodeAtPosition(Position position) {
        Optional<Node> nodeAtPosition = nodeList.stream().filter(node -> node.position.equals(position)).findFirst();
        return nodeAtPosition.orElse(null);
    }
}
