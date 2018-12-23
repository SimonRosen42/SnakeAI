
import java.awt.Point;
import java.util.Arrays;

public class MySnake extends Snake{
    
    public static final int NORTH = 0, SOUTH = 1, WEST = 2, EAST = 3, //i.e. up, down, left, right relative to play area
            LEFT = 4, STRAIGHT = 5, RIGHT =6; //relative to head of snake
    private int move;
    
    //Map map;
    
    public MySnake(){
        super();
        //map = new Map();
    }
    
    public int getMove(Map map){
        //this.map = Arrays.copyOf(map,map.size);
        return move;
    }
    
    /*public int getDirection(){
        //
        int direction = 0; //temp
        
        Point head = getPoints().get(0);
        Point nextPoint = getPoints().get(1);
        
        if (head.x== nextPoint.x){
            //i.e. vertical
            if (head.y<nextPoint.y){
                //snake going up (North)
                direction = NORTH;
            } else {
                //snake going down (South)
                direction = SOUTH;
            }
        } else if (head.y == nextPoint.y){
            //i.e. horizontal
            if (head.x>nextPoint.x){
                //snake going right (East)
                direction = EAST;
            } else {
                //snake going left (West)
                direction = WEST;
            }
        }
        
        return direction;
    }*/
}
