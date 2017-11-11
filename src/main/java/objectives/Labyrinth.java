package objectives;

import objectives.internalRepresentation.Graph;

public class Labyrinth {

    private Position goal;
    private Player player;
    private Graph graph;

    public Labyrinth(final char[][] playground, final Position goal, final Position start) {
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

    public Graph getGraph() { return graph;}

    // v(s -> (x,y), g -> (x,y)) -> aim -> v(g, s)
}
