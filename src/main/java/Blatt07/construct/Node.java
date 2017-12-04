package Blatt07.construct;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Node {
    private Set<Character> letterSet = new HashSet<>();
    private String name;
    private List<Constraint> constraintList;

    public Node( String name,  Set<Character> letterSet){
        this.name = name;
        this.letterSet = letterSet;
    }

    public void setConstraintList(List<Constraint> constraintList) {
        this.constraintList = constraintList;
    }

    public List<Constraint> getConstraintList(){
        return constraintList;
    }

    public Set<Character> getLetterSet(){
        return letterSet;
    }

    public void setLetterSet(Set<Character> letterSet){
        this.letterSet = letterSet;
    }

    public String getName(){
        return name;
    }
}
