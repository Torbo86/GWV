package Services;

import objectives.Labyrinth;
import objectives.Position;
import objectives.SearchState;
import objectives.internalRepresentation.Node;

import java.util.ArrayList;
import java.util.List;

public class SearchSpaceService {

    private final List<SearchState> searchStateList = new ArrayList<>();

//    SearchSpaceService(Labyrinth labyrinth, Position startPosition) {
//        extractSearchStateFromLabyrinth(labyrinth, startPosition); // Das ist unser SearchState-Start
//    }

    /**
     * This method extract from labyrinth the actual state
     */
    public void extractSearchStateFromLabyrinth(final Labyrinth labyrinth, Position playerPosition) {
        searchStateList.add(new SearchState(labyrinth.getGraph().getGoalNode().getPosition(), playerPosition, labyrinth.getGraph().getCharArrayFromGraph()));
    }

    /**
     * This method print all search states which are in the List
     */
    public void printAllSearchStates() {
        final int[] i = {1};
        searchStateList.forEach(searchState -> {
            System.out.println("State " + i[0] + " " + searchState.toString());
            i[0]++;
        });
    }
}
