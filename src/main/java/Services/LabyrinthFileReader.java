package Services;

import objectives.Labyrinth;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * This class can read nats labyrinths
 */
public class LabyrinthFileReader {

    public Labyrinth readLabyrinthFile(File file) {
        char playGround[][];

        try {
            List<String> stringList = Files.readAllLines(file.toPath());
            playGround = new char[stringList.size()][];

            for(int i = 0; i < stringList.size(); i++){
                playGround[i] = stringList.get(i).toCharArray();
            }

            System.out.println("Test");
        } catch (IOException e){
            e.getStackTrace();

        }
        return null;
    }
}
