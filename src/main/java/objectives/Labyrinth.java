package objectives;

import objectives.internalRepresentation.Graph;

public class Labyrinth {
    private Graph graph;

    public Labyrinth(final char[][] playground) {
        graph = new Graph(playground);
    }

    public Graph getGraph() {
        return graph;
    }
}
