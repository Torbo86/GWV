package objectives.internalRepresentation;

import objectives.Position;

/**
 * Diese Node repräsentiert das Ziel
 */
public class GoalNode extends Node {
    GoalNode(Position position) {
        super(position);
    }

    @Override
    public boolean isPossible() {
        return true;
    }
}
