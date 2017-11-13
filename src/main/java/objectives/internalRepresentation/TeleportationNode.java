package objectives.internalRepresentation;

import objectives.Position;

/**
 * Diese Node repräsentiert die Teleportationfähigkeit
 */
public class TeleportationNode extends Node {

    private char teleportName; //Das Label der Node
    private TeleportationNode teleportNode; //die andere TeleportNode

    TeleportationNode(Position position, final char teleportName) {
        super(position);
        this.teleportName = teleportName;
    }

    @Override
    public boolean isPossible() {
        return true;
    }

    void setTeleportNode(Node teleportNode) {
        if (teleportNode instanceof TeleportationNode) {
            this.teleportNode = (TeleportationNode) teleportNode;
        }
    }

    @Override
    public Node getNorthNachbar() {
        return teleportNode.getOwnNorthNachbar();
    } //Wir können nur auf die Nachbarn von der TeleportNode zugreifen

    @Override
    public Node getEastNachbar() {
        return teleportNode.getOwnEastNachbar();
    }

    @Override
    public Node getSouthNachbar() {
        return teleportNode.getOwnSouthNachbar();
    }

    @Override
    public Node getWestNachbar() {
        return teleportNode.getOwnWestNachbar();
    }

    private Node getOwnNorthNachbar() {
        return super.getNorthNachbar();
    } // Hier kann man auf die richtigen Nachbarn zugreifen

    private Node getOwnEastNachbar() {
        return super.getEastNachbar();
    }

    private Node getOwnSouthNachbar() {
        return super.getSouthNachbar();
    }

    private Node getOwnWestNachbar() {
        return super.getWestNachbar();
    }

    @Override
    public boolean isInPath() {
        return teleportNode.isInPath() && isInPath;
    } //IsInPath gilt nur wenn beide Nodes drin sind

    @Override
    public boolean isAlreadyVisited() {
        return teleportNode.isAlreadyVisisted && isAlreadyVisisted;
    } // AlreadyVisited wenn beide Nodes gefunden wurden

    public char getTeleportName() {
        return teleportName;
    }
}
