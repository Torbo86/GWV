import Services.LabyrinthFileReader;

import java.io.File;

public class Main {

    public final static String FIRST_LABYRINTH = "src/main/resources/labyrinths/blatt3_environment.txt";

    public static void main(String... args){
        LabyrinthFileReader reader = new LabyrinthFileReader();

        reader.readLabyrinthFile(new File(FIRST_LABYRINTH));
    }
}
