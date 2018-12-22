package MainPackage;


import java.awt.Point;
import java.util.Scanner;

public class Apple {
    private Point point;
    private double value;
    /*
    Initially, eating the apple is worth 3 points. However, the value of the apple decreases by 0.1 every timestep (rounded up to the nearest integer). 
    When the apple's value is negative, eating it will cause the snake to shrink! Additionally, once the value of the apple worth -4 or less, 
    eating it will immediately kill the snake doing so!
    */
    //will add extra fields later
    
    public Apple(){
        point = new Point();
    }
    
    public void setPoint(Point point){
        this.point = point;
    }
    //
    
    public void setPoint(String input){
        Scanner sc = new Scanner(input);
        point.x= sc.nextInt();
        point.y= sc.nextInt();
    }
    
    public void setValue(double value){
        this.value = value;
    }
    
    public void incValue(double inc){ //increment value by certain amount
        value += inc;
    }
    
    public double getValue(){
        return value;
    }
    
    public Point getPoint(){
        return point;
    }
    
    public int getX(){
        return point.x;
    }
    
    public int getY(){
        return point.y;
    }
    
    public void printAll(){
        System.out.println("Point: ("+point.x+","+point.y+")");
    }
}
