package Blatt07.service;

import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordsReader {


    public Set<String> readWords(String wordsFile) {
        Set<String> wordSet = new HashSet<>();

        try {
            List<String> lineList = Files.readAllLines(new File(wordsFile).toPath());
            lineList.forEach(s -> Arrays.stream(s.split(",")).forEach(s1 -> wordSet.add(s1.trim())));
            wordSet.forEach(s -> s.replace(".",""));
        } catch (Exception e){
            e.printStackTrace();
        }

        return wordSet;
    }

}
