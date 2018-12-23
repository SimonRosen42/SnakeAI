
import java.awt.Point;
import java.util.ArrayList;

public class Map {
    private byte points[][];
    //private Snake snakes[];
    private ArrayList<Point> snakePoints; 
    private int size;
    
    //open map, i.e. no points occupied
    public Map(){
        this.size= 50;
        points = new byte[size][size];
        snakePoints = new ArrayList<>();
        //snakes= new Snake[3];
    }
    //
    public Map(int size, Snake snakes[]){
        //
    }
    
    public void addSnakesToMap(Snake playerSnake, Snake[] eSnakes){
        //NB - Check to see if snake is alive first
        //add player snake to map
        snakePoints.addAll(playerSnake.getPoints());
        for(int i = 0;i<snakePoints.size();i++){
            points[snakePoints.get(i).x][snakePoints.get(i).y]=1;//-1;
        }
        //add enemy snakes to map
        for (int i1 = 0; i1<3;i1++){
            snakePoints.clear();
            snakePoints.addAll(eSnakes[i1].getPoints());
            for(int i = 0;i<snakePoints.size();i++){
                points[snakePoints.get(i).x][snakePoints.get(i).y]=(byte)(i1+2);//*(-1);
            }
        }
    }
    
    public void printMap(){
        System.out.println("------------------------------------------------");
        System.out.print("  ");
        for (int i = 0;i<50;i++){
            if (i<10)
                System.out.print(" ");
            System.out.print(i+" ");
        }
        System.out.println("");
        for (int y = 0; y<50;y++){
            System.out.print(y+" ");
            if (y<10)
                System.out.print(" ");
            for (int x = 0; x<50;x++){
                if (points[x][y]==0){
                    System.out.print("   ");
                } else {
                    System.out.print(points[x][y]+"  ");
                }
            }
            System.out.print(y+" ");
            if (y<10)
                System.out.print(" ");
            System.out.println();
        }
        System.out.print("  ");
        for (int i = 0;i<50;i++){
            if (i<10)
                System.out.print(" ");
            System.out.print(i+" ");
        }
        System.out.println("------------------------------------------------");
    }
    
    public boolean isPointOccupied(int x, int y){
        //quick and dirty way of specifying bounds of map
        boolean output = true;
        if (x<0||x>49||y<0||y>49){
            //if point out of bounds of map
            //System.out.println("log Point out of bounds of map");
        } else {
            if (points[x][y]==0){
                output = false;
            } else {
                //System.out.println("log ("+x+","+y+") occupied with value of: "+points[x][y]);
            }
        }
        return output;
    }
    
//    //add all enemy snakes to map //ignore invisible snakes for now
//    public void addSnakesToMap(Snake[] snakes){
//        //get arraylist of all snake points
//        System.out.println("addSnakeToMap working so far xD");
//        for (int i = 0; i< 3; i++){
//            if (snakes[i].getState().equals("alive"))
//                snakePoints.addAll(snakes[i].getPoints());
//        }
//        //add snake points to map
//        for (int i = 0; i<snakePoints.size();i++){
//            points[snakePoints.get(i).x][snakePoints.get(i).y]=1;
//        }
//    }
//    //add all snakes to map including player snake
//    public void addSnakesToMap(Snake playerSnake, Snake[] eSnakes){
//        //get arraylist of all snake points
//        //add points of player snake to Array List of all snake points
//        snakePoints.addAll(playerSnake.getPoints());
//        //add points of enemy snakes to Array List of all snake points
//        for (int i = 0; i< 3; i++){
//            if (eSnakes[i].getState().equals("alive"))
//                snakePoints.addAll(eSnakes[i].getPoints());
//        }
//        //add snake points to map
//        for (int i = 0; i<snakePoints.size();i++){
//            points[snakePoints.get(i).x][snakePoints.get(i).y]=1;
//        }
//    }
    
    /*public void print(){
        //System.out.println("log Hello");
        String line;
        for (int y = 0; y<50;y++){
            line="";
            for (int x= 0; x<50;x++){
                //System.out.print(points[x][y]);
                line+=points[x][y];
            }
            System.out.println("log "+ line);
        }
    }*/
}
