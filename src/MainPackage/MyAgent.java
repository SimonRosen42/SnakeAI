package MainPackage;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import za.ac.wits.snake.DevelopmentAgent;

public class MyAgent extends DevelopmentAgent {
    
    public static final int NORTH = 0, SOUTH = 1, WEST = 2, EAST = 3, //i.e. up, down, left, right relative to play area
            LEFT = 4, STRAIGHT = 5, RIGHT =6; //relative to head of snake
    
    private Apple apple;
    private MySnake mySnake; //should work?
    private ESnake[] eSnakes;
    private Map map;
    private int move;
    private static Record record;//?
    private String mySnakeInput, appleInput;
    private String eSnakesInput[]; 
    private ArrayList<Node> path;
    private AStar aStar;
    private Node startNode, goalNode; 
    private Point lastAppleLoc; //last location of apple in previous timestep. Used to determine if apple was eaten/dissapeared.
    
    public static void main(String args[]) throws IOException {
        MyAgent agent = new MyAgent();
        MyAgent.start(agent, args);
        //apple1= new Point();
        //apple2= new Point();
        /*
        mySnake = new Snake();
        Snake[] eSnakes = new Snake[3];
        for (int i = 0; i< 3;i++){
            eSnakes[i] = new Snake();
        }
        
        map = new Map(50);
        */
        
        //code for when program shuts down
        //save recording on exit of program
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                // what you want to do
                record.save();
                System.out.println("log Shut Down");
            }
        }));
    }
    
    /*
    input:
        x- and y-coordinates of the first apple
        x- and y-coordinates of the second apple
        your snake number (an integer from 0 to 3)
        description of snake 0
        description of snake 1
        description of snake 2
        description of snake 3
    */

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String initString = br.readLine();
            String[] temp = initString.split(" ");
            int nSnakes = Integer.parseInt(temp[0]);
            
            record = new Record();
            
            //initialize objects
            apple = new Apple();
            lastAppleLoc = new Point();
            
            mySnake = new MySnake();
            eSnakes = new ESnake[3];
            eSnakesInput = new String[3];
            for (int i = 0; i< 3;i++){
                eSnakes[i] = new ESnake();
                eSnakesInput[i] = new String();
            }
            aStar = new AStar();

            map = new Map();
            //System.out.println("log ##");
            while (true) {
                //System.out.println("log ----------------------------");
                String line = br.readLine();
                //end game when game over signal is received
                if (line.contains("Game Over")) {
                    System.out.println("log GAME OVER");
                    break;
                }
                String recordString[] = new String[6];
                //create apple point objects
                String appleInput = line;
                recordString[0]=appleInput;//record power apple
                Scanner sc = new Scanner(appleInput);
                int x, y;
                x = sc.nextInt();
                y = sc.nextInt();
                apple.setPoint(new Point(x,y));
                sc.close();
                
                //do stuff with apples
                int mySnakeNum = Integer.parseInt(br.readLine());
                recordString[1]=mySnakeNum+"";//record my snake no
                
                int count=0;
                String snakeLine;
                for (int i = 0; i < nSnakes; i++) {
                    snakeLine = br.readLine();
                    recordString[i+2]=snakeLine;//record snakes
                    if (i == mySnakeNum) {
                        //my snake
                        //System.out.println("log My Snake Desc: "+ snakeLine);
                        mySnakeInput = snakeLine;
                        mySnake.updateSnake(mySnakeInput);
                        //System.out.println("log mySnake: "+snakeLine);
                        //System.out.println("log direction: "+mySnake.getDirection());
                        //code for snake moves;
                        
                    } else {
                        //not my snake
                        //System.out.println("log Enemy Snake Desc: "+ snakeLine);
                        eSnakesInput[count] = snakeLine;
                        eSnakes[count].updateSnake(eSnakesInput[count]);
                        count++;
                        //System.out.println("log eSnake("+count+"): "+snakeLine);
                    }
                }
                
                count=0;
                /*System.out.println("log eSnakes: ");
                for (int i =0; i<3; i++){
                    eSnakes[i].printAll();
                }*/
                
                //if new apple has spawned
                if (!lastAppleLoc.equals(apple.getPoint())){
                //new apple spawned
                    //System.out.println("log New apple spawned: "+apple.getValue());
                    apple.setValue(3.0);
                } else {
                    apple.incValue(-0.1);
                }
                lastAppleLoc = apple.getPoint();

                //snake logic - move to seperate class
                
//                //populate map with positions of snakes
//                map.updateMap(mySnake,eSnakes);
//                //map.printMap();
//                //get direction of snake
//                
//                //A* stuff
//                startNode = new Node(mySnake.getHead());
//                
//                int mySnakeH = getManDist(mySnake.getHead(), apple);
//                int eSnake1H = getManDist(eSnakes[0].getHead(), apple);
//                int eSnake2H = getManDist(eSnakes[1].getHead(), apple);
//                int eSnake3H = getManDist(eSnakes[2].getHead(), apple);
//                
////                if (mySnakeH<eSnake1H&&mySnakeH<eSnake2H&&mySnakeH<eSnake3H){
////                    goalNode = new Node(new Point(25,25));
////                } else {
//                    goalNode = new Node(apple);
////                }
//                
//                aStar = new AStar();
//                path = new ArrayList(aStar.aStarSearch(startNode, goalNode, map.getTiles()));
//                
//                
//                //get next move 
//                Node nextNode = path.get(path.size()-2);
//                if (nextNode.getY()<startNode.getY()){
//                //NORTH
//                move = NORTH;
//                } else if (nextNode.getY()>startNode.getY()){
//                //SOUTH
//                move = SOUTH;
//                } else if (nextNode.getX()>startNode.getX()){
//                //EAST
//                move = EAST;
//                } else if (nextNode.getX()<startNode.getX()){
//                //WEST
//                move = WEST;
//                }

                System.out.println("log"+apple.getX()+" "+apple.getY()+"/"+mySnake.getPointsAsString()+"/" 
                        + eSnakes[0].getPointsAsString()+"/"+eSnakes[1].getPointsAsString()+"/"
                        + eSnakes[2].getPointsAsString() +"/"
                        + appleInput + "/" + mySnakeInput + "/" + eSnakesInput[0] + "/" + eSnakesInput[1] + "/" 
                        + eSnakesInput[2]);

                move = mySnake.getMove(eSnakes, apple);
                System.out.println(move);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private int getManDist(Point currPoint, Point goalPoint){
        int x0, y0; //coordinates for node
        int x1, y1; //coordinates for goalNode
        x0 = currPoint.x;
        y0 = currPoint.y;
        x1 = goalPoint.x;
        y1 = goalPoint.y;
        return (Math.abs(x0-x1)+Math.abs(y0-y1));
    }
   
}