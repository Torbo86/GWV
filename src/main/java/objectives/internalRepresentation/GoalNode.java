package objectives.internalRepresentation;

import objectives.Position;

public class GoalNode extends Node {
    public GoalNode(Position position) {
        super(position);
    }

    @Override
    public boolean isPossible() {
        return true;
    }
}
