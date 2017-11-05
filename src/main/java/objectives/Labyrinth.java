package objectives;

import objectives.internalRepresentation.Graph;

public class Labyrinth {

    private char[][] playground;
    private Position goal;
    private Player player;
    private Graph graph;

    public Labyrinth(final char[][] playground, final Position goal, final Position start) {
        this.playground = playground;
        this.goal = goal;
        graph = new Graph(playground);
        this.player = new Player(graph.getNodeAtPosition(start));
    }

    public Position getGoal() {
        return goal;
    }

    public Player getPlayer() {
        return player;
    }

    // v(s -> (x,y), g -> (x,y)) -> aim -> v(g, s)
}
