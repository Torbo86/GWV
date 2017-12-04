package Blatt03.Services;

import Blatt03.objectives.Labyrinth;
import Blatt03.objectives.Position;
import Blatt03.objectives.SearchState;

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
    public void extractSearchStateFromLabyrinth(final Labyrinth labyrinth, Position playerPosition, final int frontierSize) {
        searchStateList.add(new SearchState(labyrinth.getGraph().getGoalNode().getPosition(), playerPosition, labyrinth.getGraph().getCharArrayFromGraph(), frontierSize));
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
