package objectives.internalRepresentation;

import objectives.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Graph {

    List<Node> nodeList = new ArrayList<>();

    public Graph(char[][] charArray) {
        createGraphFromCharArray(charArray);
    }

    private void createGraphFromCharArray(char[][] charArray) {
        for (int i = 0; i < charArray.length; i++) {
            for (int j = 0; j < charArray[i].length; j++) {
                switch (charArray[i][j]) {
                    case ' ':
                        nodeList.add(new EmptyNode(new Position(i,j)));
                        break;
                    case 's':
                        nodeList.add(new EmptyNode(new Position(i,j)));
                        break;
                    case 'g':
                        nodeList.add(new GoalNode(new Position(i,j)));
                        break;
                    case 'x':
                        nodeList.add(new BoundNode(new Position(i,j)));
                        break;
                }
            }
        }

        enterNachbarn();
    }

    private void enterNachbarn() {
        nodeList.stream().forEach(node -> {
            node.setEastNachbar(getNodeAtPosition(new Position(node.getPosition().getX()+1, node.getPosition().getY())));
        });
    }

    public Node getNodeAtPosition(Position position) {
        Optional<Node> nodeAtPosition = nodeList.stream().filter(node -> node.position.equals(position)).findFirst();

        return nodeAtPosition.orElse(null);

    }
}
