package Blatt07.construct;

import java.util.*;

public class Graph {

    private Set<String> wordSet;
    private Map<String, Node> nodeMap;

    public Graph( Set<String> wordSet){
        this.wordSet = wordSet;
        createGraph();
    }

    public Map<String, Node> getNodeMap(){
        return nodeMap;
    }

    private void createGraph(){

        createNodes(createSetWithLetterAtPosition(0), createSetWithLetterAtPosition(1), createSetWithLetterAtPosition(2));
        addConstraints();
    }

    private void addConstraints() {
        ConstraintHolder constraintHolder = new ConstraintHolder();

        nodeMap.get("A1D1").setConstraintList(constraintHolder.getAllContstraintsForA1D1());
        nodeMap.get("A2D1").setConstraintList(constraintHolder.getAllContstraintsForA2D1());
        nodeMap.get("A3D1").setConstraintList(constraintHolder.getAllContstraintsForA3D1());
        nodeMap.get("A1D2").setConstraintList(constraintHolder.getAllContstraintsForA1D2());
        nodeMap.get("A2D2").setConstraintList(constraintHolder.getAllContstraintsForA2D2());
        nodeMap.get("A3D2").setConstraintList(constraintHolder.getAllContstraintsForA3D2());
        nodeMap.get("A1D3").setConstraintList(constraintHolder.getAllContstraintsForA1D3());
        nodeMap.get("A2D3").setConstraintList(constraintHolder.getAllContstraintsForA2D3());
        nodeMap.get("A3D3").setConstraintList(constraintHolder.getAllContstraintsForA3D3());
    }

    private Set<Character> createSetWithLetterAtPosition(int position) {
        Set<Character> letters = new HashSet<>();
        wordSet.forEach(s -> letters.add(s.charAt(position)));
        return letters;
    }


    private void createNodes(Set<Character> firstLetters, Set<Character> secondLetters, Set<Character> thirdLetters) {
        nodeMap = new HashMap<>();

        Set<Character> firstAndSecondLetters = createSchnitt(firstLetters, secondLetters);

        Set<Character> secondAndThirdLetters = createSchnitt(secondLetters, thirdLetters);

        Set<Character> firstAndThirdLetters = createSchnitt(firstLetters, thirdLetters);

        nodeMap.put("A1D1", new Node("A1D1", firstLetters));
        nodeMap.put("A2D1", new Node("A2D1", firstAndSecondLetters));
        nodeMap.put("A3D1", new Node("A3D1", firstAndThirdLetters));
        nodeMap.put("A1D2", new Node("A1D2", firstAndSecondLetters));
        nodeMap.put("A2D2", new Node("A2D2", secondLetters));
        nodeMap.put("A3D2", new Node("A3D2", secondAndThirdLetters));
        nodeMap.put("A1D3", new Node("A1D3", firstAndThirdLetters));
        nodeMap.put("A2D3", new Node("A2D3", secondAndThirdLetters));
        nodeMap.put("A3D3", new Node("A3D3", thirdLetters));
    }

    private Set<Character> createSchnitt(Set<Character> firstLetters, Set<Character> secondLetters) {
        Set<Character> schnittMenge = new HashSet<>();

        for(Character char1 : firstLetters){
            for(Character char2 : secondLetters){
                if(char1.equals(char2)){
                    schnittMenge.add(char1);
                }
            }
        }

        return schnittMenge;
    }
}
