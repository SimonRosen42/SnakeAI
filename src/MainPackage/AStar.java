package MainPackage;

import java.awt.Point;
import java.util.ArrayList;

public class AStar {
    //private Node startNode, goalNode;
    
    ArrayList<Node> closedList, openList;
    
    public AStar(){
        closedList = new ArrayList();
        openList = new ArrayList();
    }
    
    public ArrayList<Node> aStarSearch(Node startNode, Node goalNode, boolean tiles[][]){

//        System.out.println("log aStarSearch ran");
//        closedList.clear();
//        openList.clear();
        closedList = new ArrayList();
        openList = new ArrayList();
        
        //map should be 50x50
        openList.add(startNode);
        Node currentNode;
        while (!openList.isEmpty()){
            
            //temp fix
            //get node with smallest f value
            Node minNode = openList.get(0);
            for (Node node: openList){
                if (node.getF()<minNode.getF()){
                    minNode=node;
                }
            }
            currentNode = minNode;

            //if current node is equal to goal node determine and return path
            if (currentNode.isPointEqual(goalNode)){
                //return path
                Node node = currentNode;
                ArrayList<Node> path = new ArrayList();
                path.add(node);
                while(node.getParent()!=null){
                    path.add(node.getParent());
                    node = node.getParent();
                }
                //printClosedList();
//                System.out.print("log AStar - Path: ");
//                for (Node tempNode: path){
//                    System.out.print(tempNode.getX()+","+tempNode.getY()+" ");
//                }
//                //System.out.print("goalNode: "+goalNode.getX()+","+goalNode.getY());
//                System.out.println();
                return path;
            }
            
            openList.remove(currentNode);
            
            closedList.add(currentNode);
            
            //set neighbours of current node
            //i.e. North, East, South and West cells adjacent to current node
            //Do not set node if it out of bounds of map or if the tile is occupied.
            ArrayList<Node> neighbours = new ArrayList();
            //North Node
            if (isPointValid(currentNode.getX(),currentNode.getY()-1,tiles)){
                neighbours.add(new Node(new Point(currentNode.getX(),currentNode.getY()-1)));
            }
            //South Node
            if (isPointValid(currentNode.getX(),currentNode.getY()+1,tiles)){
                neighbours.add(new Node(new Point(currentNode.getX(),currentNode.getY()+1)));
            }
            //West Node
            if (isPointValid(currentNode.getX()-1,currentNode.getY(),tiles)){
                neighbours.add(new Node(new Point(currentNode.getX()-1,currentNode.getY())));
            }
            //East Node
            if (isPointValid(currentNode.getX()+1,currentNode.getY(),tiles)){
                neighbours.add(new Node(new Point(currentNode.getX()+1,currentNode.getY())));
            }
            //
            //for each neighbour of currentNode
            for (Node neighbour: neighbours){
                //if neighbour is in closed set skip to next iteration
                if (isInClosedList(neighbour)){
                    continue;
                }
                
                if (!isInOpenList(neighbour)){
                    openList.add(neighbour);
                }
                
                //System.out.println("Neighbour: ("+neighbour.getX()+","+neighbour.getY()+") has parent: ("+currentNode.getX()+","+currentNode.getY()+")");
                neighbour.setParent(currentNode);
                neighbour.setG(currentNode.getG()+1);
                neighbour.setH(calcHVal(currentNode, goalNode));
            }
        }
        //printClosedList();
        //why is the destination not found?
        //System.out.println("log Destination not found - startNode: "+startNode.getX()+","+startNode.getY()+" goalNode: "+goalNode.getX()+","+goalNode.getY());
        ArrayList<Node> dummy = new ArrayList();
        dummy.add(new Node(new Point()));
        dummy.add(new Node(new Point()));
        return dummy; //i.e. if destination is not found this is just to stop the process crashing
    }
    
    private void printClosedList(){
        System.out.println("PrintClosedList");
        System.out.println("------------------------------");
        for (Node node: closedList){
            node.printAll();
            if (node==null){
                System.out.println("Node is null");
            }
        }
        System.out.println("------------------------------");
    }
    
    public ArrayList<Node> getClosedList(){
        return closedList;
    }
    
//    public String getPathAsString(){
//        String temp = "";
//        for (Node node: path){
//            //
//        }
//        return temp;
//    }
    
    //add node to open list in way such that OpenList remains sorted by f
    private void addToOpenList(Node node){
        if (openList.isEmpty()){
           openList.add(node);
       } else {
            for (int i = openList.size()-1; i>=0;i--){
                if (i == 0){
                    openList.add(0,node);
                }else if(openList.get(i-1).getF()>node.getF()){
                    closedList.add(i,node);
                    break;
                }
            }
       }
    }

    //add node to closed list in way such that OpenList remains sorted by f    
    public void addToClosedList(Node node){
       if (closedList.isEmpty()){
           closedList.add(node);
       } else {
            for (int i = closedList.size()-1; i>=0;i--){
                if (i == 0){
                    closedList.add(0,node);
                }else if(closedList.get(i-1).getF()>node.getF()){
                    closedList.add(i,node);
                    break;
                }
            }
       }
       
   }
    
    private int calcHVal(Node currNode, Node goalNode){
        int x0, y0; //coordinates for node
        int x1, y1; //coordinates for goalNode
        x0 = currNode.getPoint().x;
        y0 = currNode.getPoint().y;
        x1 = goalNode.getPoint().x;
        y1 = goalNode.getPoint().y;
        return (Math.abs(x0-x1)+Math.abs(y0-y1));
    }
    
    public boolean isPointValid(int x, int y, boolean tiles[][]){
        boolean valid = false;
        if ((x>=0&&x<tiles.length&&y>=0&&y<tiles.length)&&tiles[x][y]==false){
        //
            valid = true;
        }
        return valid;
    }
    
    private boolean isInClosedList(Node node){
        for (int i =closedList.size()-1;i>=0;i--){
            if (closedList.get(i).isPointEqual(node)){
                return true;
            }
        }
        return false;
    }
    
    private boolean isInOpenList(Node node){
        for (int i =openList.size()-1;i>=0;i--){
            if (openList.get(i).isPointEqual(node)){
                return true;
            }
        }
        return false;
    }
    
}


