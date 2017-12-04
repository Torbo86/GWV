package Blatt07.service;

import java.util.HashSet;
import java.util.Set;

import static java.lang.Thread.sleep;

public class ComputationHelper {

    public static Set<Character> trimSetOfCharacters(String actorOneName , Set<Character> activeSet, int positionOfCharOne, Set<String> wordSet, Set<Character> passiveCharSet, int positiionOfCharTwo){
        Set<Character> trimmedSet = new HashSet<>();
        trimmedSet.addAll(activeSet); // Speichern die vorherige Menge zwischen

        for(Character letter : activeSet){ // Für jeden Buchstaben in der akitven menge
            boolean combinationFounded = false; // Bisher wurde keine Kombination gefunden
            for(String word : wordSet){ // Für jedes Wort
                for(Character passiveLetter : passiveCharSet){ // und für jeden anderen Buchstaben
                    if(word.charAt(positionOfCharOne) == letter && word.charAt(positiionOfCharTwo) == passiveLetter){
                        combinationFounded = true;
                    }
                }
            }
            if(!combinationFounded){
                System.out.println("Keine Kombination gefunden für "+ actorOneName +": " + letter + " in " + trimmedSet);
                trimmedSet.remove(letter);
            }
        }

        activeSet = trimmedSet;
        return activeSet;
    }
}
