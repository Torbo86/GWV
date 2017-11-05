package objectives.internalRepresentation;

import objectives.Position;

public abstract class Node {

    private Node northNachbar;
    private Node eastNachbar;
    private Node southNachbar;
    private Node westNachbar;
    Position position;

    Node(Position position) {
        this.position = position;
    }

    void setEastNachbar(Node eastNachbar) {
        this.eastNachbar = eastNachbar;
    }

    void setNorthNachbar(Node northNachbar) {
        this.northNachbar = northNachbar;
    }

    void setSouthNachbar(Node southNachbar) {
        this.southNachbar = southNachbar;
    }

    void setWestNachbar(Node westNachbar) {
        this.westNachbar = westNachbar;
    }

    public Position getPosition() {
        return position;
    }

    public Node getNorthNachbar() {
        return northNachbar;
    }

    public Node getEastNachbar() {
        return eastNachbar;
    }

    public Node getSouthNachbar() {
        return southNachbar;
    }

    public Node getWestNachbar() {
        return westNachbar;
    }
}
