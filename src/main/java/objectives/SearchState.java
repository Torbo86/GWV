package objectives;

public class SearchState {

    private Position goalPosition;
    private Position playerPosition;
    private char[][] playGround;

    public SearchState(final Position goalPosition, final Position playerPosition, final char[][] playGround) {
        this.goalPosition = goalPosition;
        this.playerPosition = playerPosition;
        this.playGround = playGround;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("\n");
        for (int i = 0; i < playGround.length; i++) {
            builder.append(playGround[i]);
            builder.append("\n");
        }

        return new String("(GoalX: " + goalPosition.getX() +
                ", GoalY: " + goalPosition.getY() +
                ", PlayerX: " + playerPosition.getX() +
                ", PlayerY: " + playerPosition.getY()) + ")/n" + builder.toString();
    }
}


//(xG,yG,xS,yS) -> Target: (x,y,x,y)
