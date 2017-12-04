package Blatt03.objectives;

import Blatt03.objectives.internalRepresentation.Graph;

/**
 * Das Labyrinth selbst (Kann weggeschmissen werden grundsätzlich)
 */
public class Labyrinth {
    private Graph graph;

    public Labyrinth(final char[][] playground) {
        graph = new Graph(playground);
    }

    public Graph getGraph() {
        return graph;
    }
}
