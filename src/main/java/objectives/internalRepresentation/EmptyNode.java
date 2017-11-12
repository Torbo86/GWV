package objectives.internalRepresentation;

import objectives.Position;

/**
 * Diese Node repräsentiert einfach ein leeres Feld
 */
public class EmptyNode extends Node {
    EmptyNode(Position position) {
        super(position);
    }

    @Override
    public boolean isPossible() {
        return true;
    }
}
