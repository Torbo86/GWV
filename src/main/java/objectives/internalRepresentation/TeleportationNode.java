package objectives.internalRepresentation;

import objectives.Position;

/**
 * Diese Node repräsentiert die Teleportationfähigkeit
 */
public class TeleportationNode extends Node {

    private char teleportName; //Das Label der Node
    private TeleportationNode connectedTeleportNode; //die andere TeleportNode

    TeleportationNode(Position position, final char teleportName) {
        super(position);
        this.teleportName = teleportName;
    }

    @Override
    public boolean isPossible() {
        return true;
    }

    void setConnectedTeleportNode(Node connectedTeleportNode) {
        if (connectedTeleportNode instanceof TeleportationNode) {
            this.connectedTeleportNode = (TeleportationNode) connectedTeleportNode;
        }
    }

    @Override
    public Node getNorthNachbar() {
        return connectedTeleportNode.getOwnNorthNachbar();
    } //Wir können nur auf die Nachbarn von der TeleportNode zugreifen

    @Override
    public Node getEastNachbar() {
        return connectedTeleportNode.getOwnEastNachbar();
    }

    @Override
    public Node getSouthNachbar() {
        return connectedTeleportNode.getOwnSouthNachbar();
    }

    @Override
    public Node getWestNachbar() {
        return connectedTeleportNode.getOwnWestNachbar();
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
        return connectedTeleportNode.isInPath() && isInPath;
    } //IsInPath gilt nur wenn beide Nodes drin sind

    @Override
    public boolean isAlreadyVisited() {
        return connectedTeleportNode.isAlreadyVisisted && isAlreadyVisisted;
    } // AlreadyVisited wenn beide Nodes gefunden wurden

    char getTeleportName() {
        return teleportName;
    }

    /**
     * Gibt die andere verbundene Node zurück
     */
    public TeleportationNode getConnectedTeleportNode() {
        return connectedTeleportNode;
    }
}
