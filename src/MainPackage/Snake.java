package MainPackage;


import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Scanner;

public class Snake {
//    public static final int NORTH = 0, SOUTH = 1, WEST = 2, EAST = 3, //i.e. up, down, left, right relative to play area
//            LEFT = 4, STRAIGHT = 5, RIGHT =6; //relative to head of snake
    
    private String state;
    private int length, kills; // timeSteps - for invisible snake therefore not implemented yet
    private Point head, tail;
    private ArrayList<Point> body;
    private ArrayList<Point> points; //all points occupied by snake
    private String desc;
    private Direction dir;
    
    public Snake (){
        head = new Point();
        tail = new Point();
        body = new ArrayList<>();
        points = new ArrayList();
        
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
        
        //update points
        points = new ArrayList();
        points.add(head);
        //CASE 1 - No body
        if (body.isEmpty()){
            //Case 1.1 - Horizontal (x-axis)
                if (head.x<tail.x){ 
                //CASE 1.1.1 - Head before Tail
                    for (int i =head.x+1; i<tail.x;i++){
                        points.add(new Point(i,head.y));
                    }
                } else { 
                //CASE 1.1.2 - Tail before Head
                    for (int i =head.x-1; i>tail.x;i--){
                        points.add(new Point(i,head.y));
                    }
                }
            //Case 1.2 - Vertical
                if (head.y<tail.y){ 
                //CASE 1.2.1 - Head above Tail
                    for (int i =head.y+1; i<tail.y;i++){
                        points.add(new Point(head.x,i));
                    }
                } else { 
                //CASE 1.2.2 - Head below Tail
                    for (int i =head.y-1; i>tail.y;i--){
                        points.add(new Point(head.x,i));
                    }
                }
                //add tail to points
                points.add(tail);
        } else {
        //CASE 2 - has body
            //loop for edge of snake so body.length + 1
            Point point1 = new Point();
            Point point2 = new Point(); // = head;
            //Point point2 = body.get(0);
            for (int vi = 0; vi<=body.size();vi++){
            //iterate body.size + 1 times (no of edges of snake)
                //set point1 and point2 for snake
                if (vi==0){
                //first edge
                    point1= head;
                    point2 = body.get(0);
                } else if (vi<body.size()){
                //middle edges
                    point1=point2;
                    point2=body.get(vi);
                } else {
                //last edge
                    point1 = point2;
                    point2 = tail;
                }
            
                //System.out.println(vi+"("+point1.x+","+point1.y+")"+" "+"("+point2.x+","+point2.y+")");
                if (point1.y==point2.y){
                //CASE 2.1 - Edge horizontal    
                    if (point1.x<point2.x){
                    //CASE 2.1.1 - point1 before point2
                        //System.out.println("CASE 2.1.1: "+vi);
                        for (int i =point1.x+1; i<=point2.x;i++){
                            points.add(new Point(i,point1.y));
                        }
                    } else {
                    //CASE 2.1.1 - point1 after point2
                        for (int i =point1.x-1; i>=point2.x;i--){
                            points.add(new Point(i,point1.y));
                        }
                    }
                } else if (point1.x==point2.x){
                //CASE 2.2 - Edge vertical
                    if (point1.y<point2.y){
                    //CASE 2.2.1 - point1 above point2    
                        for (int i =point1.y+1; i<=point2.y;i++){
                            points.add(new Point(point1.x,i));
                        }
                    } else {
                    //CASE 2.2.2 - point1 below point2    
                        for (int i =point1.y-1; i>=point2.y;i--){
                            points.add(new Point(point1.x,i));
                        }
                    }
                } else {
                //CASE 2.3 - Error as Edge can't be diagonal
                    System.err.println("Snake points not valid");
                }
            }
        }
    }
    
    //print all variables of snake
    public void printAll(){
        System.out.print("\nlog Snake: "+desc+"\nlog State: "+state+" Length: "+length+" kills: "+kills+ " head: "+head.x+","+head.y+" tail: "+tail.x+","+tail.y);
        System.out.print(" body: ");
        for (int i = 0; i<body.size();i++)
            System.out.print(body.get(i).x+","+body.get(i).y+" ");
        System.out.println();
    }
    
    public void printPoints(){
        System.out.print("log Points: ");
        for (int i = 0; i<points.size();i++){
            System.out.print(points.get(i).x+","+points.get(i).y+" ");
        }
        System.out.println();
    }
    
    //very rough aproximate - may easily be wrong //just uses min/max x/y values
    public Rectangle getEnclosedRect(){
        Rectangle rect = new Rectangle();
        int xMin = 49, xMax = 0, yMin = 49, yMax = 0;
        for (Point point: body){
            if (point.x<xMin){
                xMin = point.x;
            }
            if (point.x>xMax){
                xMax = point.x;
            }
            if (point.y<yMin){
                yMin = point.y;
            }
            if (point.y>yMin){
                yMax = point.y;
            }
        }
        rect = new Rectangle(xMin, yMin, xMax-xMin, yMax-yMin);
        return rect;
    }
    
    public boolean isPointEnclosed(Point point){
        boolean enclosed = false;
        Rectangle rect = getEnclosedRect();
        if ((point.x>rect.x&&point.x<rect.width+rect.x)&&(point.y>rect.y&&point.y<rect.height+rect.y)){
            enclosed = true;
        }
        return enclosed;
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
    
    public String getDesc(){
        return desc;
    }
    
    //just for snake recorder
    public void setPoints(String temp){
        Scanner sc = new Scanner(temp);
        points.clear();
        if (temp.equals("x")){
            state= "dead";
        } else {
            while (sc.hasNext()){
                Scanner scPoint = new Scanner(sc.next()).useDelimiter(",");
                points.add(new Point(scPoint.nextInt(),scPoint.nextInt()));
            }
            state = "alive";
        }
    }   
    public void addPoint(Point point){
        if (!points.contains(point)){
            points.add(point);
        } else {
            //System.out.println("log points already contains: "+point.x+","+point.y);
        }
    }
    
    public void removePoint(Point point){
        if (points.contains(point)){
            points.remove(point);
        }
    }
    
    public ArrayList<Point> getPoints(){
        //updatePoints();
        return points;
    }
    
    public String getPointsAsString(){
        String output = "";
        //quick fix to show recordviewer the snake is dead
        if (state.equals("alive")){
            //updatePoints();
            for(int i = 0;i<points.size();i++){
                output+=points.get(i).x+","+points.get(i).y;
                if (i<points.size()-1)
                    output+=" ";
            }
        } else {
            output = "x"; //set this as the output if snake is dead
        }
        return output;
    }
    
    public Direction getDirection(){
        //
        Direction direction = Direction.STRAIGHT; //temp
        
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
                direction = Direction.NORTH;
                //System.out.println("log NORTH");
            } else {
                //snake going down (South)
                direction = Direction.SOUTH;
                //System.out.println("log SOUTH");
            }
        } else if (head.y == nextPoint.y){
            //i.e. horizontal
            if (head.x>nextPoint.x){
                //snake going right (East)
                direction = Direction.EAST;
                //System.out.println("log EAST");
            } else {
                //snake going left (West)
                direction = Direction.WEST;
                //System.out.println("log WEST");
            }
        }
        
        return direction;
    }
}