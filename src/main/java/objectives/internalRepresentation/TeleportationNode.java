package objectives.internalRepresentation;

import objectives.Position;

public class TeleportationNode extends Node {

    private char teleportName;
    private TeleportationNode teleportNode;

    TeleportationNode(Position position, final char teleportName) {
        super(position);
        this.teleportName = teleportName;
    }

    @Override
    public boolean isPossible() {
        return true;
    }

    public void setTeleportNode(Node teleportNode){
        if(teleportNode instanceof TeleportationNode) {
            this.teleportNode = (TeleportationNode) teleportNode;
        }
    }

    @Override
    public Node getNorthNachbar(){
        return teleportNode.getOwnNorthNachbar();
    }

    @Override
    public Node getEastNachbar(){
        return teleportNode.getOwnEastNachbar();
    }

    @Override
    public Node getSouthNachbar(){
        return teleportNode.getOwnSouthNachbar();
    }

    @Override
    public Node getWestNachbar(){
        return teleportNode.getOwnWestNachbar();
    }

    public Node getOwnNorthNachbar(){
        return super.getNorthNachbar();
    }

    public Node getOwnEastNachbar(){
        return super.getEastNachbar();
    }


    public Node getOwnSouthNachbar(){
        return super.getSouthNachbar();
    }

    public Node getOwnWestNachbar(){
        return super.getWestNachbar();
    }

    @Override
    public boolean isInPath(){
        return teleportNode.isInPath() && isInPath;
    }

    @Override
    public boolean isAlreadyVisited(){
        return teleportNode.isAlreadyVisisted && isAlreadyVisisted;
    }

    public char getTeleportName(){
        return teleportName;
    }
}
