package Services;

import objectives.Labyrinth;
import objectives.SearchState;

import java.util.ArrayList;
import java.util.List;

public class SearchSpaceService {

    private Labyrinth labyrinth;
    private List<SearchState> searchStateList = new ArrayList<>();


    public SearchSpaceService(Labyrinth labyrinth){
        this.labyrinth = labyrinth;
        searchStateList.add(extractSearchStateFromLabyrinth(labyrinth));
    }

    private SearchState extractSearchStateFromLabyrinth(Labyrinth labyrinth) {
        return new SearchState(labyrinth.getGoal(), labyrinth.getPlayer().getPosition());
    }

    public void printAllSearchState(){
        final int[] i = {1};
        searchStateList.forEach(searchState -> {
            System.out.println("State " + i[0] + " " + searchState.toString());
            i[0]++;
        });
    }


}
