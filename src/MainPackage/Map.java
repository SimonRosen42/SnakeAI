package MainPackage;

import com.sun.javafx.sg.prism.NGCanvas;
import java.awt.Point;
import java.util.ArrayList;

public class Map {
    
    private boolean[][] tiles; //2D array containing values of each tile //change to boolean for better efficiency
    //true means the tile is occupied
    private final int size = 50;
    private MySnake mySnake;
    private ESnake[] eSnakes;
    
    public Map(){
        tiles = new boolean[size][size];
        eSnakes = new ESnake[3];
    }
    
    public void updateMap(MySnake mySnake, ESnake[] eSnakes){
        clearMap();
        updateSnakes(mySnake,eSnakes);
    }
    
//    public void updateMap(MySnake mySnake, ESnake[] eSnakes, Point applePoint){
//        clearMap();
//        updateSnakes(mySnake, eSnakes);
//        updateApple(applePoint);
//    }
    
    public void clearMap(){
        for (int x = 0; x<size;x++){
            for (int y =0; y<size;y++){
                tiles[x][y]=false;
            }
        }
    }
    
    private void updateSnakes(MySnake mySnake, ESnake[] eSnakes){
        this.mySnake = mySnake;
        this.eSnakes = eSnakes.clone();
        ArrayList<Point> points = new ArrayList();
        //null - quick fix, change later
        if (mySnake.getState().equals("alive")){ //i.e. only add snake points to map if snake is alive
            points.addAll(mySnake.getPoints());
            //mySnake.printAll();
            for(int i = 0; i<points.size();i++){
                tiles[points.get(i).x][points.get(i).y]=true; //mySnake is denoted by -2
            }
        }
        for (int i2 =0; i2<3;i2++){
            if (eSnakes[i2].getState().equals("alive")){ //i.e. only add snake points to map if snake is alive
                points.clear();
                points.addAll(eSnakes[i2].getPoints());
                for(int i = 0; i<points.size();i++){
                    tiles[points.get(i).x][points.get(i).y]=true; 
                    //eSnake 1 is denoted by -3
                    //eSnake 2 is denoted by -4
                    //eSnake 3 is denoted by -5
                    //System.out.println("updateSnakes: "+(-1*(i2+3)));
                }
            }
        }
        //for (int)
    }
    
    //used to exclude location of apple if the apple is harmful at that time
    public void addApple(Point point){ //probs easier and more efficient to use point rather than apple as only the point is required
        tiles[point.x][point.y]= true;
    }
    
    //only needed for snake recorder
//    private void updateApple(Point point){
//        //tiles[point.x][point.y]=-1; //apple is denoted by -1 on map
//    }
    
    public boolean isPointOccupied(int x, int y){
        //quick and dirty way of specifying bounds of map
        boolean output = true;
        if (x<0||x>49||y<0||y>49){
            //if point out of bounds of map
            //System.out.println("log Point out of bounds of map");
        } else {
            output = tiles[x][y];
//            if (tiles[x][y]==false){
//                output = false;
//            } else {
//                output = true;
//                //System.out.println("log ("+x+","+y+") occupied with value of: "+points[x][y]);
//            }
        }
        return output;
    }
    
    //return random non occupied point within range of map specified
    public Point getNonOccupiedPoint(Point point1, Point point2){
        Point point = null;
        ArrayList<Point> tempPoints = new ArrayList();
//        if (point1.y > point2.y){
//            //reorder points so point 1's y is always less than or equal to point 2's y
//            Point temp = point1;
//            point1 = point2;
//            point2 = temp;
//        }
        //System.out.println("point1.y: "+point1.y+", point2.y: "+point2.y);
        for (int y = point1.y; y<=point2.y; y++){
            //System.out.println("y loop: "+y);
            if (point1.x<point2.x){
                for (int x = point1.x; x<=point2.x; x++){
                    //System.out.println("x loop");
                    if (!isPointOccupied(x, y)){
                        tempPoints.add(new Point(x,y));
                    }
                }
            } else {
                for (int x = point2.x; x<=point1.x; x++){
                    if (!isPointOccupied(x, y)){
                        //System.out.println("x loop");
                        tempPoints.add(new Point(x,y));
                    }
                }
            }
        }
        if (!tempPoints.isEmpty()){
            int i = (int)(Math.random()*tempPoints.size()); //size() or size()-1?
            point = tempPoints.get(i);
        }
        //i.e. return null if all points in range are occupied
        return point;
    }
    
    public Point getNonOccupiedPoint(Point point1, Point point2, Point point3, Point point4){ 
    //minus rectangle formed by point3 and point4 from rectange formed by point1 and point2. 
    //NB Note: point3 must hava a smaller x and y than point4
        Point point = new Point();
        ArrayList<Point> tempPoints = new ArrayList();
//        if (point1.y > point2.y){
//            //reorder points so point 1's y is always less than or equal to point 2's y
//            Point temp = point1;
//            point1 = point2;
//            point2 = temp;
//        }
        //System.out.println("point1.y: "+point1.y+", point2.y: "+point2.y);
        for (int y = point1.y; y<=point2.y; y++){
            //System.out.println("y loop: "+y);
            if (point1.x<point2.x){
                for (int x = point1.x; x<=point2.x; x++){
                    //System.out.println("x loop");
                    if (!isPointOccupied(x, y) && (x>=point1.x) && (x<=point2.x) && (y>=point1.y) && (y<=point2.y)){
                        tempPoints.add(new Point(x,y));
                    }
                }
            } else {
                for (int x = point2.x; x<=point1.x; x++){
                    if (!isPointOccupied(x, y) && (x>=point1.x) && (x<=point2.x) && (y>=point1.y) && (y<=point2.y)){
                        //System.out.println("x loop");
                        tempPoints.add(new Point(x,y));
                    }
                }
            }
        }
        if (!tempPoints.isEmpty()){
            int i = (int)(Math.random()*tempPoints.size()); //size() or size()-1?
            point = tempPoints.get(i);
        }
        //i.e. return (0,0) if all points in range are occupied
        return point;
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
                if (tiles[x][y]==true){
                    System.out.print("   ");
                } else {
                    System.out.print(tiles[x][y]+"  ");
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
    
    public boolean[][] getTiles(){
        return tiles;
    }
}
