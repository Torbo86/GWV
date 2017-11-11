package Services;

import objectives.Labyrinth;
import objectives.SearchState;

import java.util.ArrayList;
import java.util.List;

public class SearchSpaceService {

    private Labyrinth labyrinth;
    private final List<SearchState> searchStateList = new ArrayList<>();

    public SearchSpaceService(Labyrinth labyrinth) {
        this.labyrinth = labyrinth;
        searchStateList.add(extractSearchStateFromLabyrinth());
    }

    /**
     * This method extract from labyrinth the actual state
     *
     * @return the SearchState from actual labyrinth
     */
    private SearchState extractSearchStateFromLabyrinth() {
        return new SearchState(labyrinth.getGoal(), labyrinth.getPlayer().getNode().getPosition(), labyrinth.getGraph().getCharArrayFromGraph());
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

    // (PlayerY, PlayerX, GoalY, GoalX)

}
