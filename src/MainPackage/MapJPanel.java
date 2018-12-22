package MainPackage;




import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class MapJPanel extends javax.swing.JPanel {

    private int tiles[][];
    private final int size = 50;
    private Map map;
    
    public MapJPanel() {
        initComponents();
        tiles = new int[size][size];
        ArrayList<Point> points = new ArrayList();
        map = new Map();
        
        points.add(new Point(10,45));
        points.add(new Point(11,45));
        points.add(new Point(12,45));
        points.add(new Point(13,45));
        points.add(new Point(10,40));
        updateTiles(points);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(204, 204, 204));
        setMaximumSize(new java.awt.Dimension(520, 520));
        setMinimumSize(new java.awt.Dimension(520, 520));
        setPreferredSize(new java.awt.Dimension(520, 520));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 540, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    public void updateTiles(ArrayList<Point> points){
        for (int i = 0;i< points.size(); i++){
            tiles[points.get(i).x][points.get(i).y]=-1;
        }

//        for (int y=0;y<size;y++){
//            for (int x=0; x<size;x++){
//                //
//            }
//        }
    }
    
    public void updateTiles(int[][] tiles){
        for (int y=0;y<size;y++){
            for (int x=0; x<size;x++){
                this.tiles[x][y]= tiles[x][y];
            }
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //g.drawString("BLAH", 400, 400);
        //g.drawRect(20, 20, 520, 520);
        //draw map cells
        System.out.println("Yo");
        for (int y=0;y<49;y++){
            for(int x=0;x<49;x++){
                //fill rectangle with color based off map array
                if (tiles[x][y]<0){
                    switch(tiles[x][y]){
                        case -1: g.setColor(Color.red);
                        break;
                        case -2: g.setColor(Color.green);
                        break;
                        case -3: g.setColor(Color.blue);
                        break;
                        case -4: g.setColor(Color.yellow);
                        break;
                    }
                    g.fillRect(10*x+10,10*y+10,10,10);
                }
                //draw grid pattern
                g.setColor(Color.BLACK);
                g.drawRect(10*x+10,10*y+10,10,10);
                //System.out.println((10*x+10)+","+(10*y+10)+",10,10");
            }
        }
//        g.setColor(Color.BLUE);
//        g.drawRect(10,10,20,20);
//        g.setColor(Color.WHITE);        
//        g.drawRect(20,10,30,20);
//        g.setColor(Color.YELLOW);        
//        g.drawRect(10,20,20,30);
//        g.setColor(Color.GREEN);        
//        g.drawRect(20,20,30,30);
//        
//        g.drawRect(100,100,150,150);
        g.dispose();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
