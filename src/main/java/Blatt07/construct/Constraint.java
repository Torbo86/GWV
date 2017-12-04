package Blatt07.construct;

public class Constraint {

    private String actorOne, actorTwo;
    private int positionOne, positionTwo;

    public Constraint(String actorOne, int positionOne, String actorTwo, int positionTwo){
        this.actorOne = actorOne;
        this.positionOne = positionOne;
        this.actorTwo = actorTwo;
        this.positionTwo = positionTwo;
    }

    public String getActorTwo(){
        return actorTwo;
    }

    public int getPositionOne() {
        return positionOne;
    }

    public int getPositionTwo() {
        return positionTwo;
    }
}
