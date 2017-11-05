import Services.LabyrinthFileReader;
import Services.SearchSpaceService;
import objectives.Labyrinth;

import java.io.File;

public class Main {

    public final static String FIRST_LABYRINTH = "src/main/resources/labyrinths/blatt3_environment.txt";

    public static void main(String... args){
        LabyrinthFileReader reader = new LabyrinthFileReader();

        Labyrinth labyrinth = reader.readLabyrinthFile(new File(FIRST_LABYRINTH));

        SearchSpaceService searchSpaceService = new SearchSpaceService(labyrinth);

        searchSpaceService.printAllSearchStates();

    }
}
