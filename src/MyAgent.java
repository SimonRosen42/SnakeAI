import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;
import za.ac.wits.snake.DevelopmentAgent;

public class MyAgent extends DevelopmentAgent {
    
    public static final int NORTH = 0, SOUTH = 1, WEST = 2, EAST = 3, //i.e. up, down, left, right relative to play area
            LEFT = 4, STRAIGHT = 5, RIGHT =6; //relative to head of snake
    
    private Point apple;
    private MySnake mySnake; //should work?
    private Snake[] eSnakes;
    private Map map;
    private int move;
    private static Record record;

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
            apple = new Point();
            
            mySnake = new MySnake();
            Snake[] eSnakes = new Snake[3];
            for (int i = 0; i< 3;i++){
                eSnakes[i] = new Snake();
            }

            map = new Map();
            System.out.println("log ##");
            while (true) {
                //System.out.println("log ----------------------------");
                String line = br.readLine();
                //end game when game over signal is received
                if (line.contains("Game Over")) {
                    break;
                }
                String recordString[] = new String[6];
                //create apple point objects
                String strApple1 = line;
                recordString[0]=strApple1;//record power apple
                Scanner sc = new Scanner(strApple1);
                int x, y;
                x = sc.nextInt();
                y = sc.nextInt();
                apple.x= x;
                apple.y= y;
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
                        mySnake.updateSnake(snakeLine);
                        //System.out.println("log mySnake: "+snakeLine);
                        //System.out.println("log direction: "+mySnake.getDirection());
                        //code for snake moves;
                        
                    } else {
                        //not my snake
                        //System.out.println("log Enemy Snake Desc: "+ snakeLine);
                        eSnakes[count].updateSnake(snakeLine);
                        count++;
                        //System.out.println("log eSnake("+count+"): "+snakeLine);
                    }
                }
                count=0;
                /*System.out.println("log eSnakes: ");
                for (int i =0; i<3; i++){
                    eSnakes[i].printAll();
                }*/
                
                //populate map with positions of snakes
                map.addSnakesToMap(mySnake,eSnakes);
                //map.printMap();
                Point head = mySnake.getHead();
                switch(mySnake.getDirection()){
                    case NORTH: //
                        if (map.isPointOccupied(head.x, head.y-1)==false){
                            move = STRAIGHT;
                        } else{
                            if (map.isPointOccupied(head.x-1, head.y)==false){
                                move = LEFT;
                            } else {
                                move = RIGHT;
                            }
                        }
                        break;
                    case EAST: //
                        if (map.isPointOccupied(head.x+1, head.y)==false){
                            move = STRAIGHT;
                        } else{
                            if (map.isPointOccupied(head.x, head.y-1)==false){
                                move = LEFT;
                            } else {
                                move = RIGHT;
                            }
                        }
                        break;
                    case SOUTH: //
                        if (map.isPointOccupied(head.x, head.y+1)==false){
                            move = STRAIGHT;
                        } else{
                            if (map.isPointOccupied(head.x+1, head.y)==false){
                                move = LEFT;
                            } else {
                                move = RIGHT;
                            }
                        }
                        break;
                    case WEST: //
                        if (map.isPointOccupied(head.x-1, head.y)==false){
                            move = STRAIGHT;
                        } else{
                            if (map.isPointOccupied(head.x, head.y+1)==false){
                                move = LEFT;
                            } else {
                                move = RIGHT;
                            }
                        }
                        break;    
                }
                //System.out.println("log move: "+move);
                
                //finished reading, calculate move:
                
                record.mapUpdate(recordString);
                
                //System.out.println("log calculating...");
//                move = new Random().nextInt(4);
                /* Test to see if get direction was working
                System.out.println("log "+mySnake.getDirection());
                if (mySnake.getDirection()== NORTH){
                    move=EAST;
                } else {
                    move= SOUTH;
                }*/
                System.out.println(move);
                //System.out.println("log ----------------------------");
                System.out.println("log "+apple.x+","+apple.y+"/"+mySnake.getPointsAsString()+"/" 
                        + eSnakes[0].getPointsAsString()+"/"+eSnakes[1].getPointsAsString()+"/"
                        + eSnakes[2].getPointsAsString());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
   
}