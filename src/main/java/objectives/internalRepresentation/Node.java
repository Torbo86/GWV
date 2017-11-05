package objectives.internalRepresentation;

import objectives.Position;

public abstract class Node {

    protected Node northNachbar;
    protected Node eastNachbar;
    protected Node southNachbar;
    protected Node westNachbar;
    Position position;

//    public Node(Node eastNachbar, Node northNachbar, Node southNachbar, Node westNachbar){
//        this.northNachbar = northNachbar;
//        this.eastNachbar = eastNachbar;
//        this.southNachbar = southNachbar;
//        this.westNachbar = westNachbar;
//    }
    public Node(Position position){
        this.position = position;
    }

    public void setEastNachbar(Node eastNachbar) {
        this.eastNachbar = eastNachbar;
    }

    public void setNorthNachbar(Node northNachbar) {
        this.northNachbar = northNachbar;
    }

    public void setSouthNachbar(Node southNachbar) {
        this.southNachbar = southNachbar;
    }

    public void setWestNachbar(Node westNachbar) {
        this.westNachbar = westNachbar;
    }

    public Position getPosition(){
        return position;
    }
}
