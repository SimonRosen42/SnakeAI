package MainPackage;

import java.awt.Point;

public class Node {
    private Node parent;
    private Point point;
    private int f, g, h;
    //g - number of moves to current node
    //h - heuristic - estimated movement cost to goal node
    //f = g + h
    
    public Node(){
        f = 0;
        g = 0;
        h=0;
        parent = null;
    }
    
    public Node(Point p, Node parent, int g, int h){
        point = p;
        this.parent = parent;
        this.g =g;
        this.h = h;
        f = g + h;
    }
    public Node(Point p){
        point = p;
        this.parent = null;
        g = 0;
        h = 0;
        f = 0;
        //all values besides point are zero or null - use for start node and end nodes
    }
    public Node(Point p, Node parent){
        this(p);
        this.parent = parent;
        //all values besides point are zero or null - use for start node and end nodes
    }
    //just for testing purposes
    public Node(Point p, int f){
        this(p);
        this.f = f;
        //all values besides point are zero or null - use for start node and end nodes
    }
    
    public void setParent(Node parent){
        this.parent = parent;
    }
    
    public void setPoint(Point p){
        this.point = p;
    }
    
    public void setG(int g){
        this.g = g;
        f = g + h; //update f
    }
    
    public void setH(int h){
        this.h = h;
        f = g + h;
    }
    
    public Node getParent(){
        return parent;
    }
    
    public Point getPoint(){
        return point;
    }
    
    public int getF(){
        return f;
    }
    
    public int getG(){
        return g;
    }
    
    public int getH(){
        return h;
    }
    
    public int getX(){
        return point.x;
    }
    
    public int getY(){
        return point.y;
    }
    
    //check if points of two nodes are equal. If they are then the two nodes are 'equal'.
    public boolean isPointEqual(Node otherNode){
        boolean equal = false;
        if (point.equals(otherNode.getPoint())){
            equal = true;
        }
        return equal;
    }
    
    //for testing purposes
    public void printAll(){
        boolean hasParent = false;
        if (parent!=null)
            hasParent = true;
        System.out.println("Point: ("+point.x+","+point.y+"), f: "+f+", g: "+g+", h:"+h + ", hasParent: "+hasParent);
    }
    public void printPoint(){
        System.out.println("Point: ("+point.x+","+point.y+")");
    }
}
