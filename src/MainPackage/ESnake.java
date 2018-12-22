package MainPackage;

import java.awt.Point;
import java.util.ArrayList;

public class ESnake extends Snake{
    
    Direction direction;
    ArrayList<Point> headNeighbours;
    public ESnake(){
        super();
        headNeighbours = new ArrayList();
    }
    
    @Override
    public void updateSnake(String desc){
        super.updateSnake(desc);
        /*add point in front of head as this is where snake will most likely be in next timestep. 
        Experiment with doing this for the points to it's left and right*/
        //System.out.println("log ESnake: updateSnake called");
        direction = this.getDirection();
        Point point[] = new Point[3];
        switch(direction){
            case NORTH: 
            point[0] = new Point(this.getHead().x-1,this.getHead().y);
            point[1] = new Point(this.getHead().x,this.getHead().y-1);
            point[2] = new Point(this.getHead().x+1,this.getHead().y);
                break;
                
            case SOUTH: 
            point[0] = new Point(this.getHead().x+1,this.getHead().y);
            point[1] = new Point(this.getHead().x,this.getHead().y+1);
            point[2] = new Point(this.getHead().x-1,this.getHead().y);
                break;
                
            case EAST: 
            point[0] = new Point(this.getHead().x,this.getHead().y+1);
            point[1] = new Point(this.getHead().x,this.getHead().y-1);
            point[2] = new Point(this.getHead().x+1,this.getHead().y);
                break;
                
            case WEST: 
            point[0] = new Point(this.getHead().x,this.getHead().y+1);
            point[1] = new Point(this.getHead().x,this.getHead().y-1);
            point[2] = new Point(this.getHead().x-1,this.getHead().y);
                break;
        }
        //only adds point if it is within bounds of map
        for (int i = 0; i< 3; i++){
            if (point[i].x>=0 && point[i].x<=49 && point[i].y>=0 && point[i].y<=49){
                headNeighbours.add(point[i]);
                this.addPoint(point[i]);
                //System.out.println("log point["+i+"]: ("+point[i].x+","+point[i].y+")");
            }
        }
    }
    
    public void addHeadNeighbours(){
        for (Point point: headNeighbours){
            this.addPoint(point);
        }
    }
    
    public void removeHeadNeighbours(){
        for (Point point: headNeighbours){
            this.removePoint(point);
        }
    }
}
