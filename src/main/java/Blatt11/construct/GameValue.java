package Blatt11.construct;

/**
 * This class represents the values on the places in the matrix
 * There are value like -> 1-15 + Blank
 */
public class GameValue {

    private String name; // The name is also the value

    public GameValue(final String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
