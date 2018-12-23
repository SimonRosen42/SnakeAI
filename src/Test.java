
import java.awt.Point;
import java.util.ArrayList;

public class Test {
    public static void main(String args[]) {
        String snekString = "alive 26 2 10,12 15,12 15,7 5,7 5,2";
        //String snekString = "alive 6 6 14,13 19,13";
        //alive 2 1 12,13 12,14
        Snake snek = new Snake();
        snek.updateSnake(snekString);
        snek.printAll();
        //
        ArrayList<Point> points = snek.getPoints();
        System.out.println("Snek: "+points.size());
        for (int i = 0; i< points.size();i++)
            System.out.print(points.get(i).x+","+points.get(i).y+" ");
        System.out.println();
        Snake mySnake = new Snake();
        Snake[] eSnakes = new Snake[3];
        for (int i = 0; i< 3;i++){
            eSnakes[i] = new Snake();
        }
        Map map = new Map();
        mySnake.updateSnake("alive 5 0 13,47 9,47"); //alive 26 2 10,12 15,12 15,7 5,7 5,2
        eSnakes[0].updateSnake("alive 6 0 14,9 9,9");
        eSnakes[1].updateSnake("alive 5 1 15,5 11,5");
        eSnakes[2].updateSnake("alive 5 0 11,35 7,35");
        map.addSnakesToMap(mySnake, eSnakes);
        //map.isPointOccupied(, )
        System.out.println("log "+mySnake.getPointsAsString()+"/" 
                        + eSnakes[0].getPointsAsString()+"/"+eSnakes[1].getPointsAsString()+"/"
                        + eSnakes[2].getPointsAsString());
        map.printMap();
        
//        mySnake: alive 5 0 13,47 9,47
//        direction: 3 //east
//        eSnake(1): alive 6 0 14,9 9,9
//        eSnake(2): alive 5 1 15,5 11,5
//        eSnake(3): alive 5 0 11,35 7,35
//        (14,47) occupied with value of: 1
//        (13,46) occupied with value of: 1
//        move: 6 //right
//        calculating...
//        ----------------------------
//        ----------------------------
//        mySnake: alive 5 0 13,48 13,47 10,47
//        direction: 1
//        eSnake(1): alive 6 0 15,9 10,9
//        eSnake(2): alive 5 1 16,5 12,5
//        eSnake(3): alive 5 0 12,35 8,35
//        (13,49) occupied with value of: 1
//        (14,48) occupied with value of: 1
//        move: 6
//        calculating...
//        ----------------------------
//        ----------------------------
//        mySnake: alive 5 0 12,48 13,48 13,47 11,47
//        direction: 2
//        eSnake(1): alive 6 0 16,9 11,9
//        eSnake(2): alive 5 1 17,5 13,5
//        eSnake(3): alive 5 0 13,35 9,35
//        (11,48) occupied with value of: 1
//        (12,49) occupied with value of: 1
//        move: 6
//        calculating...
//        ----------------------------
//        ----------------------------
//        mySnake: alive 5 0 0,46 4,46
//        direction: 2
//        eSnake(1): alive 6 0 18,9 13,9
//        eSnake(2): alive 5 1 19,5 15,5
//        eSnake(3): alive 5 0 15,35 11,35
//        Point out of bounds of map
//        (0,47) occupied with value of: 1
//        move: 6
//        calculating...
//        ----------------------------
//        ----------------------------
//        mySnake: alive 5 0 0,45 0,46 3,46
//        direction: 0
//        eSnake(1): alive 6 0 19,9 14,9
//        eSnake(2): alive 5 1 20,5 16,5
//        eSnake(3): alive 5 0 16,35 12,35
//        (0,44) occupied with value of: 1
//        Point out of bounds of map
//        move: 6
    }    
}
