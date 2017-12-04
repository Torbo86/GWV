package Blatt07;

import Blatt07.construct.Graph;
import Blatt07.service.ArcAlgorithmService;
import Blatt07.service.WordsReader;

import java.util.Set;

public class Main {

    private final static String WORDS_FILE = "src/main/resources/words/words";

    public static void main(String[] args){
        WordsReader wordsReader = new WordsReader();

        Set<String> wordsSet = wordsReader.readWords(WORDS_FILE);

        ArcAlgorithmService algorithmService = new ArcAlgorithmService(wordsSet);

        Graph graph = algorithmService.startArcAlgorithm();

        System.out.println(graph.getNodeMap().get("A1D1").getLetterSet() + "|" + graph.getNodeMap().get("A2D1").getLetterSet() + "|" + graph.getNodeMap().get("A3D1").getLetterSet() + "\n"
        + graph.getNodeMap().get("A1D2").getLetterSet() + "|" + graph.getNodeMap().get("A2D2").getLetterSet() + "|" + graph.getNodeMap().get("A3D2").getLetterSet() + "\n"
        + graph.getNodeMap().get("A1D3").getLetterSet() + "|" + graph.getNodeMap().get("A2D3").getLetterSet() + "|" + graph.getNodeMap().get("A3D3").getLetterSet() + "\n");
    }
}
