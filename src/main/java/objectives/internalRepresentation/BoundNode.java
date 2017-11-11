package objectives.internalRepresentation;

import objectives.Position;

public class BoundNode extends Node{
    public BoundNode(Position position) {
        super(position);
    }

    @Override
    public boolean isPossible() {
        return false;
    }
}

