package objectives.internalRepresentation;

import objectives.Position;

/**
 * Diese Node ist eine abstrakte Node
 */
public abstract class Node {

    private Node northNachbar;
    private Node eastNachbar;
    private Node southNachbar;
    private Node westNachbar;
    private Position position;
    protected boolean isAlreadyVisisted = false;
    protected boolean isInPath = false;

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

    public abstract boolean isPossible();

    public boolean isAlreadyVisited() {
        return isAlreadyVisisted;
    }

    public void setAlreadyVisisted(boolean alreadyVisisted) {
        isAlreadyVisisted = alreadyVisisted;
    }

    @Override
    public String toString() {
        return getPosition().toString();
    }

    boolean isInPath() {
        return isInPath;
    }

    public void setInPath(boolean inPath) {
        isInPath = inPath;
    }
}