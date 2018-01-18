package Blatt11.construct;

/**
 * This class represent a place in our matrix
 */
public class Place {

    private String placeId;

    private GameValue currentGameValue; // the current GameValue on this place
    private GameValue solutionGameValueName; // A place knows the GameValue which should be there

    public Place (final String placeId){
        this.placeId = placeId;
    }

    public String getPlaceId(){
        return placeId;
    }

    public GameValue getCurrentGameValue() {
        return currentGameValue;
    }

    public GameValue getSolutionGameValueName() {
        return solutionGameValueName;
    }

    public void setCurrentGameValue(GameValue currentGameValue) {
        this.currentGameValue = currentGameValue;
    }

    public void setSolutionGameValueName(GameValue solutionGameValueName) {
        this.solutionGameValueName = solutionGameValueName;
    }
}
