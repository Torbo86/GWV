package Blatt03.objectives.internalRepresentation;

import Blatt03.objectives.Position;

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
