package objectives.internalRepresentation;

import objectives.Position;

public class StartNode extends Node {
    StartNode(Position position) {
        super(position);
        setAlreadyVisisted(true);
    }

    @Override
    public boolean isPossible() {
        return true;
    }
}
