package Blatt03.objectives.internalRepresentation;

import Blatt03.objectives.Position;

/**
 * Diese Node repräsentiert den Start
 */
public class StartNode extends Node {
    StartNode(Position position) {
        super(position);
        setAlreadyVisisted(true); //StartNode wurde natürlich schon gefunden
    }

    @Override
    public boolean isPossible() {
        return true;
    }
}
