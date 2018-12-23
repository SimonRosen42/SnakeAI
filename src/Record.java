
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Record {

    private String filePath;
    private int snakeNo;
    private PrintWriter printWriter;
    
    public Record(){
        filePath="C:\\Users\\User\\Documents\\NetBeansProjects\\Snake AI\\record\\record1.txt";
        try {
            printWriter = new PrintWriter(filePath);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Record.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void mapUpdate(String inputLines[]){ 
        for (int i = 0; i<inputLines.length;i++){
            printWriter.println(inputLines[i]);
        }
        printWriter.println("##");
    }
    
    public void save(){
        printWriter.close();
    }
}
