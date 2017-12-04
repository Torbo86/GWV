package Blatt03.objectives.internalRepresentation;

import Blatt03.objectives.Position;

/**
 * Diese Node repräsentiert eine Grenze
 */
public class BoundNode extends Node {
    BoundNode(Position position) {
        super(position);
    }

    @Override
    public boolean isPossible() {
        return false;
    } // Eine Grenze kann natürlich nicht betreten werden
}

