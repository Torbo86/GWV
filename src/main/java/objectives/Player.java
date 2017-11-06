package objectives;

import objectives.internalRepresentation.Node;

public class Player {

    private Node node;

    public Player(Node node) {
        this.node = node;
    }

    public Node getNode() {
        return node;
    }

    public boolean moveUp(){
        if(node.getNorthNachbar() != null && node.getNorthNachbar().isPossible()) {
            node = node.getNorthNachbar();
            return true;
        } else {
            return false;
        }
    }
}
