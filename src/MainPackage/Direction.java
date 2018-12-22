package MainPackage;

//class to make it easier to use directions
public enum Direction {
    NORTH(0), //0
    SOUTH(1), //1
    WEST(2), //2
    EAST(3), //3
    //relative to snake
    LEFT(4), //4
    STRAIGHT(5), //5 
    RIGHT(6); //6
    private int value;
    
    private Direction(int val){
        value = val;
    }
    
    public int getValue(){
        return value;
    }
}
