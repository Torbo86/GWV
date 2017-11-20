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
    boolean isAlreadyVisisted = false;
    boolean isInPath = false;

    Node(Position position) {
        this.position = position;
    }

    void setEastNachbar(Node eastNachbar) {
        this.eastNachbar = eastNachbar;
    } //Setzen von Nachbarn

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

    public abstract boolean isPossible(); //Possible bedeutet, ob diese Node besucht werden kann

    public boolean isAlreadyVisited() {
        return isAlreadyVisisted;
    } //isAlreadyVisited bedeutet, dass man diese bereits gefunden hat

    public void setAlreadyVisisted(boolean alreadyVisisted) {
        isAlreadyVisisted = alreadyVisisted;
    }

    @Override
    public String toString() {
        return getPosition().toString();
    }

    boolean isInPath() {
        return isInPath;
    } // InPath bedeutet, dass diese Node für den Weg zum Goal gebraucht wird

    public void setInPath(boolean inPath) {
        isInPath = inPath;
    }
}
