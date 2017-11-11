package objectives.internalRepresentation;

import objectives.Position;

public class EmptyNode extends Node{
    public EmptyNode(Position position) {
        super(position);
    }

    @Override
    public boolean isPossible() {
        return true;
    }
}
