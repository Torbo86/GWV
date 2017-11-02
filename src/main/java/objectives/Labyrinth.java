package objectives;

public class Labyrinth {

    private char[][] playground;
    private Position goal;
    private Player player;

    public Labyrinth(final char[][] playground, final Position goal, final Player player){
        this.playground = playground;
        this.goal = goal;
        this.player = player;
    }

    public Position getGoal() {
        return goal;
    }

    public Player getPlayer() {
        return player;
    }

    // v(s -> (x,y), g -> (x,y)) -> aim -> v(g, s)
}
