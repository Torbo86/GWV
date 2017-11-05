import Services.LabyrinthFileReader;
import Services.SearchSpaceService;
import objectives.Labyrinth;

import java.io.File;

/**
 * This is the main class
 */
public class Main {

    private final static String FIRST_LABYRINTH = "src/main/resources/labyrinths/blatt3_environment.txt";
    private final static String SECOND_LABYRINTH = "src/main/resources/labyrinths/blatt4_environment_a.txt";
    private final static String THIRD_LABYRINTH = "src/main/resources/labyrinths/blatt4_environment_b.txt";


    public static void main(String... args) {
        LabyrinthFileReader reader = new LabyrinthFileReader();

        Labyrinth labyrinth = reader.readLabyrinthFile(new File(FIRST_LABYRINTH));

        SearchSpaceService searchSpaceService = new SearchSpaceService(labyrinth);

        searchSpaceService.printAllSearchStates();

    }
}
