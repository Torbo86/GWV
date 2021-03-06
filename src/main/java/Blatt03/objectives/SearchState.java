package Blatt03.objectives;

/**
 * Diese Klasse repräsentiert ein Searchstate
 */
public class SearchState {

    private Position goalPosition;
    private Position playerPosition;
    private char[][] playGround;
    private int frontierSize;

    /**
     * Der Konstruktor für einen Searchstate
     * @param goalPosition Die Zielposition
     * @param playerPosition Wo ist gerade der Roboter/Player
     * @param playGround Das dazugehörige Spielfeld
     */
    public SearchState(final Position goalPosition, final Position playerPosition, final char[][] playGround, final int frontierSize) {
        this.goalPosition = goalPosition;
        this.playerPosition = playerPosition;
        this.playGround = playGround;
        this.frontierSize = frontierSize;
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
                ", PlayerY: " + playerPosition.getY()) + ") Frontiersize: "+ frontierSize +"\n" + builder.toString();
    }
}
