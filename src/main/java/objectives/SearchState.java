package objectives;

public class SearchState {

    private Position goalPosition;
    private Position playerPosition;

    public SearchState(final Position goalPosition, final Position playerPosition){
        this.goalPosition = goalPosition;
        this.playerPosition = playerPosition;
    }

    @Override
    public String toString() {
        return new String("(GoalX: " + goalPosition.getX() +
                            ", GoalY: " + goalPosition.getY() +
                            ", PlayerX: " + playerPosition.getX() +
                            ", PlayerY: " + playerPosition.getY()) + ")";
    }
}
