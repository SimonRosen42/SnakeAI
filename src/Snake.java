
import java.awt.Point;
import java.util.ArrayList;
import java.util.Scanner;

public class Snake {
    public static final int NORTH = 0, SOUTH = 1, WEST = 2, EAST = 3, //i.e. up, down, left, right relative to play area
            LEFT = 4, STRAIGHT = 5, RIGHT =6; //relative to head of snake
    
    private String state;
    private int length, kills; // timeSteps - for invisible snake therefore not implemented yet
    private Point head, tail;
    private ArrayList<Point> body;
    private ArrayList<Point> points; //all points occupied by snake
    private String desc;
    public Snake (){
        head = new Point();
        tail = new Point();
        body = new ArrayList<>();
        points = new ArrayList<>();
        
        /*Scanner sc = new Scanner(desc);
        state= sc.next();
        length = sc.nextInt();
        kills = sc.nextInt();
        //head
        String strPoint;
        strPoint = sc.next();
        Scanner subSc = new Scanner(strPoint).useDelimiter(",");
        head.x= subSc.nextInt();
        head.y= subSc.nextInt();
        subSc.close();
        //body
        while(sc.hasNext()){
            strPoint = sc.next();
            subSc = new Scanner(strPoint).useDelimiter(",");
            int x = subSc.nextInt(), y = subSc.nextInt();
            body.add(new Point(x, y));
            subSc.close();
        }
        //pop last point off body to become tail
        tail= body.get(body.size()-1); //not sure if this is correct i.e. is it rather length-1?
        body.remove(body.size()-1);*/
    }
    
    //update snake variables via String input
    //i.e. once per iteration
    public void updateSnake(String desc){
        this.desc= desc;
        Scanner sc = new Scanner(desc);
        state= sc.next();
        length = sc.nextInt();
        kills = sc.nextInt();
        
        //if snake is alive or invisible
        //for now add dummy variables for invisible snake or ignore
        if (state.equals("alive")){
            //head        
            String strPoint;
            //System.out.println("log updateSnake(Snake Class)-"+state+": "+desc);
            strPoint = sc.next();
            Scanner subSc = new Scanner(strPoint).useDelimiter(",");
            //System.out.println("log Working?");
            head.x= subSc.nextInt();
            head.y= subSc.nextInt();//?
            subSc.close();
            //body
            body.clear(); //reset arraylist
            while(sc.hasNext()){
                strPoint = sc.next();
                subSc = new Scanner(strPoint).useDelimiter(",");
                int x = subSc.nextInt(), y = subSc.nextInt();
                body.add(new Point(x, y));
                subSc.close();
            }
            //pop last point off body to become tail
            tail= body.get(body.size()-1); //not sure if this is correct i.e. is it rather length-1?
            body.remove(body.size()-1);
        } else {
            //if snake is dead
            //?
        }
        //
        sc.close();
    }
    
    //print all variables of snake
    public void printAll(){
        System.out.print("\nlog Snake: "+desc+"\nlog State: "+state+" Length: "+length+" kills: "+kills+ " head: "+head.x+","+head.y+" tail: "+tail.x+","+tail.y);
        System.out.print(" body: ");
        for (int i = 0; i<body.size();i++)
            System.out.print(body.get(i).x+","+body.get(i).y+" ");
        System.out.println();
    }
    
    //get methods
    //transform to integer to make it more efficient?
    public String getState(){
        return state;
    }
    
    public int getLength(){
        return length;
    }
    
    public int getKills(){
        return kills;
    }
    
    public Point getHead(){
        return head;
    }
    
    public Point getTail(){
        return tail;
    }
    
    public ArrayList<Point> getBody(){
        return body;
    }
    
    //return all points of snake //comment better for readability
    public ArrayList<Point> getPoints(){
        Point point1, point2;
        points.clear();
        //System.out.println();
        if (body.isEmpty()){
            //if snake has no body
            point1 = head;
            point2 = tail; 
            //if snake is vertical
            if (point1.x == point2.x){
                    //do stuff if the snake segment is vertical
                    int y1, y2;
                    if (point1.y>point2.y){
                        y1= point2.y;
                        y2= point1.y;
                    } else {
                        y1= point1.y;
                        y2= point2.y;
                    }
                    for (int y = y1; y<=y2-1;y++){
                        points.add(new Point(point1.x,y));
                    }
                } else if(point1.y == point2.y){
                    //do stuff if the snake segment is horizontal
                    int x1, x2;
                    if (point1.x>point2.x){
                        x1= point2.x;
                        x2= point1.x;
                    } else {
                        x1= point1.x;
                        x2= point2.x;
                    }
                    for (int x = x1; x<=x2-1;x++){
                        points.add(new Point(x,point1.y));
                    }
                }
        } else {
            //if snake has body
            point1 = head;
            point2= body.get(0);
            for (int i = 1; i<body.size()+2;i++){
                //System.out.println("log Loop ran. Size: "+points.size()+" Points: "+point1.x+","+point1.y+" "+point2.x+","+point2.y);
                if (point1.x == point2.x){
                    //System.out.println("x if statement ran");
                    //do stuff if the snake segment is vertical
                    int y1, y2;
                    if (point1.y>point2.y){
                        y1= point2.y;
                        y2= point1.y;
                    } else {
                        y1= point1.y;
                        y2= point2.y;
                    }
                    for (int y = y1; y<=y2-1;y++){
                        points.add(new Point(point1.x,y));
                        //System.out.println("Loop 2y ran");
                    }
                    if (i==1)
                        points.add(new Point(point1.x,y2));
                } else if(point1.y == point2.y){
                    //System.out.println("y if statement ran");
                    //do stuff if the snake segment is horizontal
                    int x1, x2;
                    if (point1.x>point2.x){
                        x1= point2.x;
                        x2= point1.x;
                    } else {
                        x1= point1.x;
                        x2= point2.x;
                    }
                    for (int x = x1; x<=x2-1;x++){
                        //System.out.println("Loop 2x ran");
                        points.add(new Point(x,point1.y));
                    }
                    if (i==1)
                        points.add(new Point(x2,point1.y));
                }
                if (i<body.size()){
                    point1=point2;
                    point2= body.get(i);
                } else {
                    point1 = point2;
                    point2 = tail;
                }
            }
        }
        //remove tail from points as the point occupied by the tail will not be occupied in the next timestep
        points.remove(tail);
        return points;
    }
    
    public String getPointsAsString(){
        String output = "";
        getPoints();
        for(int i = 0;i<points.size();i++){
            output+=points.get(i).x+","+points.get(i).y;
            if (i<points.size()-1)
                output+=" ";
        }
        return output;
    }
    
    public int getDirection(){
        //
        int direction = 0; //temp
        
        Point nextPoint;
        if (body.isEmpty()){
            nextPoint = tail;
        } else {
            nextPoint = body.get(0);
        }
        
        //System.out.println("log"+head.x+","+head.y+" "+ nextPoint.x+","+nextPoint.y);
        
        if (head.x== nextPoint.x){
            //i.e. vertical
            if (head.y<nextPoint.y){
                //snake going up (North)
                direction = NORTH;
                //System.out.println("log NORTH");
            } else {
                //snake going down (South)
                direction = SOUTH;
                //System.out.println("log SOUTH");
            }
        } else if (head.y == nextPoint.y){
            //i.e. horizontal
            if (head.x>nextPoint.x){
                //snake going right (East)
                direction = EAST;
                //System.out.println("log EAST");
            } else {
                //snake going left (West)
                direction = WEST;
                //System.out.println("log WEST");
            }
        }
        
        return direction;
    }
}