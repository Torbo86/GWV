package objectives.internalRepresentation;

import objectives.Position;

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
    }
}

