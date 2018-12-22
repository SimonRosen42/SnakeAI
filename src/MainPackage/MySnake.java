package MainPackage;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;


public class MySnake extends Snake{
    
//    public static final int NORTH = 0, SOUTH = 1, WEST = 2, EAST = 3, //i.e. up, down, left, right relative to play area
//            LEFT = 4, STRAIGHT = 5, RIGHT =6; //relative to head of snake
    private Point myHead;
    
    
    private int move;
    private Map map;
    private AStar aStar;
    private Node startNode, goalNode;
    private int eSnakeEstDist[];
    private ArrayList<Node> path;
    
    private boolean appleViable; 
    /*I.e. is it viable for the snake to go for the apple. Reasons not to:
        1)If apple is too far away from snake to be reached while its value is still positive.
        2)If another snake is closer to the apple than mySnake.
        3)If going for the apple might kill current snake i.e. if another snake might consume it at the same time
    */
    private boolean appleTooFar; //reason 1
    private boolean eSnakeCloser; //reason 2
    private boolean appleKill; //reason 3
    
    private Point centPoint; //point in centre of map that snakes goes towards
//    private Snake eSnakes[]; //required?
//    private Point apple; //required?
    
    public MySnake(){
        super();
        map = new Map();
        aStar = new AStar();
        startNode = new Node();
        goalNode = new Node();
        eSnakeEstDist = new int[3];
        appleViable = true;
        appleTooFar = false;
        eSnakeCloser = false;
        appleKill = false; 
        centPoint = new Point();
        path = new ArrayList();
    }
    
    public int getMove(ESnake eSnakes[],Apple apple){
        //this.map = Arrays.copyOf(map,map.size);
        //game logic to get move
        //map = new Map(); //maybe this will help?
        //startNode = new Node();
        //goalNode = new Node();
        map.updateMap(this,eSnakes);
        //map.printMap();
        //get direction of snake

        
        //A* stuff
        startNode.setPoint(this.getHead());
        aStar = new AStar();
        myHead = this.getHead();
        
        if (appleViable){

            goalNode.setPoint(apple.getPoint());
            path.clear();
            path.addAll(aStar.aStarSearch(startNode, goalNode, map.getTiles()));
//            path = new ArrayList(aStar.aStarSearch(startNode, goalNode, map.getTiles()));
            ArrayList<Node> tempPath = new ArrayList();
            Node tempNode = new Node();
            for (int i =0; i< 3; i++){
                eSnakeEstDist[i] = getManDist(eSnakes[i].getHead());//+(eSnakes[i].getLength()/5);
                // see if it's feasible to calc AStar for each one
                tempNode.setPoint(eSnakes[i].getHead());
                eSnakes[i].removeHeadNeighbours(); //remove headNeigbours so that they do not appear on map and mess up the eSnake AStar
                map.updateMap(this,eSnakes);//?
                eSnakes[i].addHeadNeighbours(); //add headNeighbours back to eSnake
                tempPath.clear();
                tempPath.addAll(aStar.aStarSearch(tempNode, goalNode, map.getTiles()));
                if (tempPath.get(0).getPoint().equals(new Point(0,0))){
                    eSnakeEstDist[i] = getManDist(eSnakes[i].getHead());
                    //System.out.println("log Man Dist("+i+")");
                } else {
                    eSnakeEstDist[i] = tempPath.size()-1;
                    //System.out.println("log eSnake("+i+") - path size: "+tempPath.size());
                }
                
            }
            
            //if snake can't make it to apple in time before it's value is zero or negative don't go for it, rather head to centre of map
            if (path.size()-1>Math.round(apple.getValue()*10)){
                //calculate new path
                appleTooFar = true;
            }
            
            //if another snake is closer or as close to the apple as the snake, in terms of the manhattan distance
            if (path.size()-1>=eSnakeEstDist[0]||path.size()-1>=eSnakeEstDist[1]||path.size()-1>=eSnakeEstDist[2]){
                eSnakeCloser = true;
                //System.out.println("log eSnake closer");
            }
            
            if (appleTooFar==true || eSnakeCloser == true || appleKill == true){
                appleViable = false;
            }
        } else{ //head to centre of map
            //System.out.println("log Apple too far");
            //head to centre of map
            //redo so that it's less shit - Redone, hopefully it works
            if ((myHead.x-1==centPoint.x && myHead.y == centPoint.y) || (myHead.x+1==centPoint.x && myHead.y == centPoint.y) 
                    || (myHead.x==centPoint.x && myHead.y-1 == centPoint.y) || (myHead.x==centPoint.x && myHead.y+1 == centPoint.y)
                    || map.isPointOccupied(myHead.x, myHead.y)){
                
                int inc = (int)Math.sqrt(this.getLength()*1.1);
                centPoint = map.getNonOccupiedPoint(new Point(25-inc,25-inc), new Point(25+inc,25+inc),new Point(23,23), new Point(26,26));
                if (centPoint!=null && this.isPointEnclosed(centPoint)){
                    //
                    Rectangle rect = this.getEnclosedRect();
                    Point p1 = new Point(rect.x,rect.y);
                    Point p2 = new Point(rect.x+rect.width,rect.y+rect.y);
                    centPoint = map.getNonOccupiedPoint(new Point(25-inc,25-inc), new Point(25+inc,25+inc),p1, p2);
                }
                //System.out.println("log isCentPointEnclosed: "+this.isPointEnclosed(centPoint));
                if (centPoint == null){
                    centPoint = map.getNonOccupiedPoint(new Point(10,10), new Point(35,35),new Point(25-inc,25-inc), new Point(25+inc,25+inc));
                }
                
//                int inc = 10;
//                int mult = 10 + this.getLength()/10;
////                int count = 0;
////                centPoint = myHead;
////                while (map.isPointOccupied(centPoint.x,centPoint.y)){
////                    //
////                    System.out.println("log while loop: ("+centPoint.x+","+centPoint.y+")");
//                    if ((int)(Math.random()*2)==0){
//                        centPoint.x = 25 + (int) (Math.random()*10)+inc;
//                    } else {
//                        centPoint.x = 25 - (int) (Math.random()*10)-inc;
//                    }
//
//                    if ((int)(Math.random()*2)==0){
//                        centPoint.y  =  25 - (int) (Math.random()*10)-inc;
//                    } else {
//                        centPoint.y  = 25 + (int) (Math.random()*10)+inc;
//                    }
//                    count++;
//                    if (count>25){
//                        inc+=10;
//                        count =0;
//                        System.out.println("log count inc");
//                    }
//                }
                
            }
            //recheck how close snake is to apple
            if (eSnakeCloser){
                //
            }
            
            //add apple to map as this will mark it as a non valid point so snake doesn't kill itself by accidentally eating it when it's value is negative
            if (appleTooFar){
                map.addApple(apple.getPoint());
            }
            
            goalNode.setPoint(centPoint);
            path.clear();
            path.addAll(aStar.aStarSearch(startNode, goalNode, map.getTiles()));
//            path = new ArrayList(aStar.aStarSearch(startNode, goalNode, map.getTiles()));
            //maybe this'll work? Re-calculate if values are wrong
//            if (path.get(0).getPoint().equals(new Point(0,0))){
//                map.updateMap(this, eSnakes);
//                path.clear();
//                path.addAll(aStar.aStarSearch(startNode, goalNode, map.getTiles()));
//                System.out.println("log Recalculated");
//            }
            if (apple.getValue()==3.0){ 
            //if apple respawned
                //reset values as these must now be calculted again
                appleViable = true;
                appleTooFar = false;
                eSnakeCloser = false;
                appleKill = false;
                //System.out.println("log New Apple Spawned");
            }
        }
        
        //if path is not found calculate path to random point in centre
        if (path.get(0).getPoint().equals(new Point(0,0))){ //will repeat until valid point is found 
            //centPoint = goalNode.getPoint();
            //System.out.println("log path not found - recalculate");
            centPoint = map.getNonOccupiedPoint(new Point(15,15), new Point(35,35), new Point(20,20), new Point(30,30));
            //System.out.println("log centPoint: "+centPoint.x+","+centPoint.y);
//            if (centPoint == null){
//                centPoint = map.getNonOccupiedPoint(new Point(10,10), new Point(40,40));
//            }
            
            goalNode.setPoint(centPoint);
            path.clear();
            path.addAll(aStar.aStarSearch(startNode, goalNode, map.getTiles()));
        }
        
        
        //get next move 
        //not sure if I should change this in the future?
        if (path.size()>1){ //stops index out of bounds error when path is of size 1. (Path will never be empty)
            Node nextNode = path.get(path.size()-2);
            if (nextNode.getY()<startNode.getY()){
                //NORTH
                move = Direction.NORTH.getValue();
            } else if (nextNode.getY()>startNode.getY()){
                //SOUTH
                move = Direction.SOUTH.getValue();
            } else if (nextNode.getX()>startNode.getX()){
                //EAST
                move = Direction.EAST.getValue();
            } else if (nextNode.getX()<startNode.getX()){
                //WEST
                move = Direction.WEST.getValue();
            }
            
            String strPath = "";
            for (Node node: path){
                strPath+= node.getX() + "," + node.getY()+ " ";
            }
            
//            if (path.get(0).getPoint().equals(new Point(0,0))){
//                System.out.println("log -----------------------");
//                System.out.println("log MySnake: Head: ("+getHead().x+","+getHead().y+"); desc: "+getDesc()+"; points: "+this.getPointsAsString() 
//                        + " goalNode: "+goalNode.getX()+","+goalNode.getY()//);
//                +"; direction: "+this.getDirection()+" path:"+ strPath + "; isGoalOccupied: "+map.isPointOccupied(goalNode.getX(),goalNode.getY()));
//                System.out.println("log -----------------------");
//                //temp fix for bug - Snake going straight up into itself or enemy snake and killing itself
//                //simply avoid all element on map
//            }
            
        } else{
            //quite prone to error...
            move=Direction.STRAIGHT.getValue();
        }
        
//        if (this.getState().equals("dead")){
//            System.out.println("log Move: "+move);
//        }
        
        return move;
    }
    
    //return manhattan distance between two points
    private int getManDist(Point currPoint){
        Point goalPoint = goalNode.getPoint();
        int x0, y0; //coordinates for node
        int x1, y1; //coordinates for goalNode
        x0 = currPoint.x;
        y0 = currPoint.y;
        x1 = goalPoint.x;
        y1 = goalPoint.y;
        return (Math.abs(x0-x1)+Math.abs(y0-y1));
    }
}
