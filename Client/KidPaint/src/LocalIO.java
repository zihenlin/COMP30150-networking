import java.io.*;
import java.util.*;

public class LocalIO{
    static int row;
    static int col;
    public static void main (String args[]){}

    static void consoleToFile(String path, int[][]data) throws IOException{
        int[][] sketch = data;
        DataOutputStream out = new DataOutputStream(new FileOutputStream(path));


        out.writeInt(sketch.length);
        out.writeInt(sketch[0].length);
        for(int i = 0; i < sketch.length; i++){
            for(int j = 0; j < sketch[i].length; j++){
                out.writeInt(sketch[i][j]);
            }
        }
        out.flush();
        out.close();
    }

    static int[][] filetoConsole(String path) throws IOException{
        DataInputStream in  = new DataInputStream(new FileInputStream(path));
        
        row = in.readInt();
        col = in.readInt();

        int[][] sketch = new int[row][col];
        for(int i = 0; i < sketch.length; i++){
            for(int j = 0; j < sketch[i].length; j++){
                sketch[i][j] = in.readInt();
            }
        }

        in.close();
        return sketch;
    }
}